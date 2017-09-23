package io.swagger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiParam;
import io.swagger.common.CacheType;
import io.swagger.common.GlobalConstants;
import io.swagger.dao.CommentDao;
import io.swagger.exception.NotFoundException;
import io.swagger.model.User;
import io.swagger.model.UserCommentInfo;
import io.swagger.model.Comment;
import io.swagger.model.UserFriend;
import io.swagger.model.UserSearchInfo;
import io.swagger.service.common.RedisService;
import io.swagger.utils.UserUtil;

@Service
public class CommentService {

	private Logger logger = LoggerFactory.getLogger(UserFriendRequestService.class);
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private UserService userService;
	
	public Boolean createAComment(Comment comment)
	{
		logger.info("CommentService - createAComment comment:" + comment);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		
		comment.setThumbsUp(0L);
		comment.setViews(0L);
		comment.setUserId(currentUser.getUserid());
		comment.setCreatedTime(new Date().getTime());
		comment.setStatus(1);
		commentDao.save(comment);	
		
		Long temp = 0L;
		redisService.set(redisService.getMD5CacheKey(CacheType.commentThumbsup,comment.getId()),temp.toString());
		redisService.set(redisService.getMD5CacheKey(CacheType.commentViews,comment.getId()),temp.toString());
		redisService.incrBy(redisService.getMD5CacheKey(CacheType.infoCommentNum,comment.getInfoId()), 1L);
		return true;		
	}
	
	public void Thumbup(Long commentId)
	{
		logger.info("CommentService - Thumbup commentId:" + commentId);
		redisService.plus1(redisService.getMD5CacheKey(CacheType.commentThumbsup, commentId));
	}
	
	public void View(Long commentId)
	{
		logger.info("CommentService - Thumbup commentId:" + commentId);
		redisService.plus1(redisService.getMD5CacheKey(CacheType.commentViews, commentId));		
	}
	
	public Long getCommentNumByInfoId(Long infoId){
		String md5_key = redisService.getMD5CacheKey(CacheType.infoCommentNum,infoId);
		Long result = redisService.getObject(md5_key, Long.class);
		if(result==null){
			result = commentDao.findCommentNumByInfoId(infoId);
			redisService.setObjct(md5_key, result);
		}
		return result;
	}
	
	public List<UserCommentInfo> getAllComments(Long infoId)
	{
		logger.info("CommentService - createAComment infoId:" + infoId);
		
		List<Comment> comments = commentDao.findByInfoId(infoId);
		for(Comment comment:comments)
		{
			String thumbsUp = redisService.get(redisService.getMD5CacheKey(CacheType.commentThumbsup,comment.getId()));
			Long	lthumbsUp = 0L;
			if(thumbsUp == null)				
				redisService.set(redisService.getMD5CacheKey(CacheType.commentThumbsup,comment.getId()),lthumbsUp.toString());
			else
				lthumbsUp = Long.parseLong(thumbsUp);							
			comment.setThumbsUp(lthumbsUp);
			
			String views = redisService.get(redisService.getMD5CacheKey(CacheType.commentViews,comment.getId()));
			Long lviews = 0L;
			if(views ==null)				
				redisService.set(redisService.getMD5CacheKey(CacheType.commentViews,comment.getId()),lviews.toString());
			else
				lviews = Long.parseLong(views);
			comment.setViews(lviews);
			
			//count the views
			View(comment.getId());
		}
		return comments.parallelStream().map(s->{
			 User user = userService.getUserByUserid(s.getUserId());
			 return new UserCommentInfo(user,s);
		 }).collect(Collectors.toList());
	}
	
	public void deleteRelatedComments(Long infoId)
	{
		logger.info("CommentService - deleteRelatedComments infoId:" + infoId);
		
		List<Comment> comments = commentDao.findByInfoId(infoId);
		for(Comment comment:comments)
		{
			redisService.remove(redisService.getMD5CacheKey(CacheType.commentThumbsup,comment.getId()));
			redisService.remove(redisService.getMD5CacheKey(CacheType.commentViews,comment.getId()));
		}
		commentDao.updateStatusbyInfoId(infoId,0);		
		return;		
	}
	
}
