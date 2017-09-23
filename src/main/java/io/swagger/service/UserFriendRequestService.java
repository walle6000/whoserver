package io.swagger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiParam;
import io.swagger.common.CacheType;
import io.swagger.common.GlobalConstants;
import io.swagger.dao.UserFriendDao;
import io.swagger.dao.UserFriendRequestDao;
import io.swagger.exception.NotFoundException;
import io.swagger.model.User;
import io.swagger.model.UserFriend;
import io.swagger.model.UserFriendRequest;
import io.swagger.model.UserSearchInfo;
import io.swagger.service.common.MQMessageService;
import io.swagger.service.common.RedisService;
import io.swagger.utils.PinYinUtil;
import io.swagger.utils.UserUtil;

@Service
public class UserFriendRequestService{
	
	private Logger logger = LoggerFactory.getLogger(UserFriendRequestService.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFriendDao userFriendDao;
	
	@Autowired
	private UserFriendRequestDao userFriendRequestDao;
	
	@Autowired
	private MQMessageService mqMessageService;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private UserFriendService userFriendService;
	
	
	public void createFriendRequestForUser(String friendId,String requestContent) throws NotFoundException{
		logger.info("UserService - createFriendUser: friend:" + friendId);
		logger.info("UserService - createFriendUser: requestContent:" + requestContent);
		User fuser = userService.getUserByUserid(friendId);
		if(fuser == null){
			throw new NotFoundException(400, "Friend's userid is not found.");
		}
		logger.info("UserService - createFriendUser: friend info:" + fuser);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserService - createFriendUser current user info:" + currentUser);
		UserFriend friend = new UserFriend();
		friend.setUserId(currentUser.getUserid());
		friend.setFriendId(fuser.getUserid());
		friend.setNickName(fuser.getUserProfile().getUserName());
		friend.setNickNamePinYin(PinYinUtil.getPinYin(fuser.getUserProfile().getUserName()));
		userFriendDao.saveAndFlush(friend);
		logger.info("UserService - createFriendUser: friend relative id:" + friend.getId());
		logger.info("UserService - createFriendUser: send adding friend request message...");
		UserFriendRequest userFriendRequest = new UserFriendRequest();
		userFriendRequest.setRelativeId(friend.getId());
		userFriendRequest.setDecisionId(fuser.getUserid());
		userFriendRequest.setRequestContent(requestContent);
		userFriendRequest.setRequester(currentUser.getUserProfile().getUserName());
		mqMessageService.sendFriendRequestMessageToQueue(userFriendRequest);
	}
	
	public void createFriendRequestForUser(String friendId,
									       String requestContent,
									       Integer shareFriend,
									       String nickName,
									       String labelRelationship,
									       String labelImpression,
									       String description,
									       Integer friendOrigin) throws NotFoundException{
		logger.info("UserFriendRequestService - createFriendRequestForUser: friend:" + friendId);
		logger.info("UserFriendRequestService - createFriendRequestForUser: requestContent:" + requestContent);
		logger.info("UserFriendRequestService - createFriendRequestForUser: shareFriend:" + shareFriend);
		logger.info("UserFriendRequestService - createFriendRequestForUser: nickName:" + nickName);
		logger.info("UserFriendRequestService - createFriendRequestForUser: labelRelationship:" + labelRelationship);
		logger.info("UserFriendRequestService - createFriendRequestForUser: labelImpression:" + labelImpression);
		logger.info("UserFriendRequestService - createFriendRequestForUser: description:" + description);
		logger.info("UserFriendRequestService - createFriendRequestForUser: friendOrigin:" + friendOrigin);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		if(currentUser.getUserid().equals(friendId)){
			throw new NotFoundException(111014, "Can not apply to youself to your friend.");
		}
		User fuser = userService.getUserByUserid(friendId);
		if(fuser == null){
			throw new NotFoundException(111010, "Friend's userid is not found.");
		}
		UserFriend alreadyFriend = userFriendService.getUserFriendByFriendId(friendId);
		if(alreadyFriend!=null){
			throw new NotFoundException(111011, "He/she is already your friend.");
		}
		UserFriendRequest sentPendingRequest = getOneMySentFriendRequest(fuser.getUserid(),0);
		if(sentPendingRequest!=null){
			throw new NotFoundException(111012, "Already send a request to your friend,please wait a moment.");
		}
		UserFriendRequest myPendingRequest = getOneUserFriendRequest(fuser.getUserid(),0);
		if(myPendingRequest!=null){
			throw new NotFoundException(111013, "Your friend has send request to you,please handle it.");
		}
		logger.info("UserService - createFriendUser: friend info:" + fuser);
		//User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserService - createFriendUser current user info:" + currentUser);
		UserFriend friend = new UserFriend();
		friend.setUserId(currentUser.getUserid());
		friend.setFriendId(fuser.getUserid());
		friend.setNickName(nickName==null?fuser.getUserProfile().getUserName():nickName);
		friend.setNickNamePinYin(PinYinUtil.getPinYin(friend.getNickName()));
		friend.setShareFriend(shareFriend==null?0:shareFriend);
		friend.setLabelRelationship(labelRelationship == null?"":labelRelationship);
		friend.setLabelImpression(labelImpression==null?"":labelImpression);
		friend.setDescription(description==null?"":description);
		friend.setFriendOrigin(friendOrigin==null?0:friendOrigin);
		userFriendDao.saveAndFlush(friend);
		logger.info("UserService - createFriendUser: friend relative id:" + friend.getId());
		logger.info("UserService - createFriendUser: send adding friend request message...");
		UserFriendRequest userFriendRequest = new UserFriendRequest();
		userFriendRequest.setRelativeId(friend.getId());
		userFriendRequest.setDecisionId(fuser.getUserid());
		userFriendRequest.setDecisioner(fuser.getUserProfile().getUserName());
		userFriendRequest.setRequestContent(requestContent);
		userFriendRequest.setRequester(currentUser.getUserProfile().getUserName());
		userFriendRequest.setRequesterId(currentUser.getUserid());
		userFriendRequest.setHujuId(currentUser.getId());
		userFriendRequest.setThumbnailHeadicon(currentUser.getUserProfile().getThumbnailheadIcon());
		userFriendRequest.setFriendOrigin(friendOrigin==null?0:friendOrigin);
		mqMessageService.sendFriendRequestMessageToQueue(userFriendRequest);
	}
	
	public Map<String,List<UserFriendRequest>> getAllUserFriendRequestMap(){
		logger.info("UserService - getAllUserFriendRequestMap...");
		Map<String,List<UserFriendRequest>>  UserFriendRequestMap = new HashMap<String,List<UserFriendRequest>>();
		List<UserFriendRequest> UserFriendRequestList = getAllUserFriendRequest();
		
		if(UserFriendRequestList != null){
			UserFriendRequestMap = UserFriendRequestList.parallelStream().sorted((s1,s2)->s1.getCreateDate().compareTo(s2.getCreateDate()))
					.collect(Collectors.groupingBy(UserFriendRequest::getRequestDate));
		}
		return new TreeMap<String,List<UserFriendRequest>>(UserFriendRequestMap).descendingMap();
		//return UserFriendRequestMap;
	}
	
	public List<UserFriendRequest> getAllUserFriendRequest(){
		logger.info("UserService - getAllUserFriendRequest...");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserService - getAllUserFriendRequest currentUser:\n" + currentUser);
		List<UserFriendRequest> UserFriendRequestList = null;
		String md5key = redisService.getMD5CacheKey(CacheType.friendRequestList, currentUser.getUserid());
		UserFriendRequestList = redisService.getList(md5key, UserFriendRequest.class);
		if(UserFriendRequestList == null){
			UserFriendRequestList = userFriendRequestDao.findAllUserRequest(currentUser.getUserid());
			if(UserFriendRequestList != null){
				redisService.setList(md5key, UserFriendRequestList);
			}else{
				UserFriendRequestList =  new ArrayList<UserFriendRequest>();
				redisService.setList(md5key, UserFriendRequestList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return UserFriendRequestList;
	}
	
    public Optional<UserFriendRequest> getUserFriendRequestByRequestId(Long requestId){
    	logger.info("UserService - getUserFriendRequestByRequestId...");
		List<UserFriendRequest> UserFriendRequestList = getAllUserFriendRequest();
		Optional<UserFriendRequest> UserFriendRequestOptional = UserFriendRequestList.stream().filter(s->s.getId()==requestId).findFirst();
		return UserFriendRequestOptional;
	}
    
    public UserFriendRequest getOneUserFriendRequest(String requesterId,Integer status){
    	logger.info("UserService - getOneUserFriendRequest...");
		List<UserFriendRequest> UserFriendRequestList = getAllUserFriendRequest();
		Optional<UserFriendRequest> UserFriendRequestOptional = UserFriendRequestList.stream().filter(s->s.getRequesterId().equals(requesterId)).filter(s->s.getStatus()==status).findFirst();
		return UserFriendRequestOptional.orElseGet(()->{return null;});
	}
    
    public List<UserFriendRequest> getAllMySentFriendRequest(){
		logger.info("UserFriendRequestService - getAllMySentFriendRequest...");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserFriendRequestService - getAllMySentFriendRequest currentUser:\n" + currentUser);
		List<UserFriendRequest> sentFriendRequestList = null;
		String md5key = redisService.getMD5CacheKey(CacheType.mySentfriendRequestList, currentUser.getUserid());
		sentFriendRequestList = redisService.getList(md5key, UserFriendRequest.class);
		if(sentFriendRequestList == null){
			sentFriendRequestList = userFriendRequestDao.findAllSentRequest(currentUser.getUserid());
			if(sentFriendRequestList != null){
				redisService.setList(md5key, sentFriendRequestList);
			}else{
				sentFriendRequestList =  new ArrayList<UserFriendRequest>();
				redisService.setList(md5key, sentFriendRequestList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return sentFriendRequestList;
	}
	
    public UserFriendRequest getOneMySentFriendRequest(String decisionId,Integer status){
    	logger.info("UserService - getOneMySentFriendRequest...");
		List<UserFriendRequest> sentFriendRequestList = getAllMySentFriendRequest();
		Optional<UserFriendRequest> UserFriendRequestOptional = sentFriendRequestList.stream().filter(s->s.getDecisionId().equals(decisionId)).filter(s->s.getStatus()==status).findFirst();
		return UserFriendRequestOptional.orElseGet(()->{return null;});
	}
	
    @Transactional
	public void updateUserFriendRequestStatus(Long requestId,UserFriendRequest userFriendRequest){
		logger.info("UserService - updateUserFriendRequestStatus...");
		Optional<UserFriendRequest> UserFriendRequestOptional = getUserFriendRequestByRequestId(requestId);
		UserFriendRequestOptional.ifPresent(s->{
			s.setStatus(userFriendRequest.getStatus());
			userFriendRequestDao.updateUserRequestStatusById(s.getId(), s.getStatus());
			if(userFriendRequest.getStatus()==1){//approve
				UserFriend already = userFriendService.getUserFriendByFriendId(s.getRequesterId());
				if(already==null){
				UserFriend friend = new UserFriend();
				friend.setUserId(s.getDecisionId());
				friend.setFriendId(s.getRequesterId());
				friend.setNickName(s.getRequester());
				friend.setNickNamePinYin(PinYinUtil.getPinYin(friend.getNickName()));
				friend.setCreateDate(new Date().getTime());
				friend.setStatus(1);
				friend.setFriendOrigin(s.getFriendOrigin());
				userFriendDao.saveAndFlush(friend);
				}
			}
			clearUserCache();
			mqMessageService.sendFriendRequestResultMessageToQueue(s);
		});
	}
	
	public void clearUserCache(){
		logger.info("UserService - clearUserCache...");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserService - clearUserCache currentUser:\n" + currentUser);
		String md5key_friendRequestList = redisService.getMD5CacheKey(CacheType.friendRequestList, currentUser.getUserid());
		String md5key_friendList = redisService.getMD5CacheKey(CacheType.friendList, currentUser.getUserid());
		redisService.remove(md5key_friendRequestList);
		redisService.remove(md5key_friendList);
	}
	
	public void clearUserCache(UserFriendRequest request){
		logger.info("UserService - clearUserCache request:"+request);
		String md5key_friendRequestList = redisService.getMD5CacheKey(CacheType.friendRequestList, request.getDecisionId());
		String md5key_sentFriendRequestList = redisService.getMD5CacheKey(CacheType.mySentfriendRequestList, request.getRequesterId());
		redisService.remove(md5key_friendRequestList);
		redisService.remove(md5key_sentFriendRequestList);
	}
	
	
}
