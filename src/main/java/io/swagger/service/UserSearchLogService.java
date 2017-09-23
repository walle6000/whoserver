package io.swagger.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.common.CacheType;
import io.swagger.common.GlobalConstants;
import io.swagger.dao.UserSearchLogDao;
import io.swagger.model.User;
import io.swagger.model.UserFriend;
import io.swagger.model.UserSearchInfo;
import io.swagger.model.UserSearchLog;
import io.swagger.service.common.RedisService;
import io.swagger.utils.PinYinUtil;
import io.swagger.utils.StringUtil;
import io.swagger.utils.UserUtil;

@Service
public class UserSearchLogService{
	
	private Logger logger = LoggerFactory.getLogger(UserSearchLogService.class);
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private UserSearchLogDao userSearchLogDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFriendService userFriendService;
	
	public List<UserSearchLog> getAllSearchLogForUser(String ownId){
		logger.info("UserSearchLogService - getAllSearchLogForUser ownId:" + ownId);
		String md5key = redisService.getMD5CacheKey(CacheType.UserSearchLog, ownId);
		List<UserSearchLog> searchLogList = redisService.getList(md5key, UserSearchLog.class);
		if(searchLogList == null){
			searchLogList = userSearchLogDao.findByOwnId(ownId);
			if(searchLogList != null){
				redisService.setList(md5key, searchLogList);
			}else{
				searchLogList =  new ArrayList<UserSearchLog>();
				redisService.setList(md5key, searchLogList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return searchLogList;
	}
	
	public List<UserSearchLog> getAllSearchLogForCurrentUser(){
		logger.info("UserSearchLogService - getAllSearchLogForCurrentUser...");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserService - getAllFriendsOfUser currentUser:\n" + currentUser);
		String md5key = redisService.getMD5CacheKey(CacheType.UserSearchLog, currentUser.getUserid());
		List<UserSearchLog> searchLogList = redisService.getList(md5key, UserSearchLog.class);
		if(searchLogList == null){
			searchLogList = userSearchLogDao.findByOwnId(currentUser.getUserid());
			if(searchLogList != null){
				redisService.setList(md5key, searchLogList);
			}else{
				searchLogList =  new ArrayList<UserSearchLog>();
				redisService.setList(md5key, searchLogList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return searchLogList;
	}
	
	@Transactional
	public void saveOrUpdateUserSearchLog(List<UserSearchLog> searchLogList){
		Set<String> ownIdSet = new HashSet<String>();
		searchLogList.parallelStream().distinct().filter(s->!getAllSearchLogForUser(s.getOwnId()).contains(s)).map(s->{
			s.setUserNamePinYin(PinYinUtil.getPinYin(s.getUserName()));return s;}
		).peek(s->userSearchLogDao.save(s)).forEach(s->ownIdSet.add(s.getOwnId()));
		ownIdSet.forEach(s->clearCache(s));
	}
	
	public List<UserSearchInfo> searchLogsOfUser(String keyWord,String ownId){
		logger.info("UserSearchLogService - searchLogsOfUser keyWord:"+keyWord);
		logger.info("UserSearchLogService - searchLogsOfUser ownId:"+ownId);
		List<UserSearchLog> userSearchLog = getAllSearchLogForUser(ownId);
		List<UserSearchInfo> userSearchInfoList = userSearchLog.stream().map(s->{
			UserSearchInfo userSearchInfo = null;
			User user = userService.getUserByUserid(s.getUserId());
			if(user!=null){
				UserFriend userFriend = userFriendService.getUserFriendByFriendId(user.getUserid());
				if(userFriend!=null){
					userSearchInfo = new UserSearchInfo(user,userFriend);
				}else{
					userSearchInfo = new UserSearchInfo(user,null);
				}
			}else{
				userSearchInfo = new UserSearchInfo(s);
			}
			return userSearchInfo;
		}).collect(Collectors.toList());
		
		if(StringUtil.isContainChinese(keyWord)){
			return userSearchInfoList.parallelStream().filter(s->StringUtil.isContainWord(s.getUserName(), keyWord)).collect(Collectors.toList());
		}else if(StringUtil.isNumeric(keyWord)){
			return userSearchInfoList.parallelStream().filter(s->StringUtil.isContainWord(s.getUserid(), keyWord)).collect(Collectors.toList());
		}else if(StringUtil.isAlphabet(keyWord)){
			return userSearchInfoList.parallelStream().filter(s->StringUtil.isContainAlphabet(s.getUserNamePinYin(), keyWord.toLowerCase(),"_")).collect(Collectors.toList());
		}else{
			return userSearchInfoList.parallelStream().filter(s->StringUtil.isContainWord(s.getUserNamePinYin(), keyWord.toLowerCase())).collect(Collectors.toList());
		}
	}

	public void clearCache(String ownId){
		logger.info("UserSearchLogService - clearUserCache ownId:"+ownId);
		String md5key = redisService.getMD5CacheKey(CacheType.UserSearchLog, ownId);
		redisService.remove(md5key);
	}
	
}
