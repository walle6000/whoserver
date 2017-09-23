package io.swagger.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.common.CacheType;
import io.swagger.common.FileType;
import io.swagger.common.GlobalConstants;
import io.swagger.dao.InfoExplorableDao;
import io.swagger.exception.CommonException;
import io.swagger.model.FileUpload;
import io.swagger.model.InfoExplorable;
import io.swagger.model.User;
import io.swagger.model.UserExploreInfo;
import io.swagger.model.UserFriend;
import io.swagger.model.UserSearchInfo;
import io.swagger.service.common.FileUploadService;
import io.swagger.service.common.RedisService;
import io.swagger.utils.UserUtil;

@Service
public class InfoExploreService {

	private Logger logger = LoggerFactory.getLogger(UserFriendRequestService.class);
	
	@Autowired
	InfoExplorableDao infoExplorableDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFriendService userFriendService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	//*key param:
	//share_friend(0: no requestToShare; 1: requestToShare; 2:agreed)
	//requestToShare (param isRequest= 0 or 1) result: isRequest=0 share_friend=0 ； isRequest=1，share_friend from 0 to 1
	public Boolean requestToShare(Integer isRequest,String friendIds){
		logger.info("InfoExploreService - requestToShare: isRequest\n" + isRequest);
		logger.info("InfoExploreService - requestToShare: friendIds\n" + friendIds);
		
		//Deal the import friend list with the import parameter:isRequest. it's supposed to be 1.
		List<String> friendIdList = Arrays.asList(friendIds.split(","));
		_requestToShare(isRequest,friendIdList);
		
		//Deal other friends with the reverse parameter. if the isRequest is 1,use 0 to erase them.
		List<String> friendIdRevertList = new ArrayList<String>();
		List<UserFriend> myFriends = userFriendService.getAllFriendsOfUser();
		for(UserFriend myFriend : myFriends){
			String myFriendId = myFriend.getFriendId();
			boolean isIn = false;
			for(String friendId : friendIdList){
				if(myFriendId.equals(friendId))
					isIn = true;
			}
			if(isIn == false)
				friendIdRevertList.add(myFriendId);
		}
		Integer revertRequest = 0;
		if(isRequest == 0)
			revertRequest = 1;
		_requestToShare(revertRequest,friendIdRevertList);		
		
		return true;
	}
	
	private void _requestToShare(Integer isRequest,List<String> friendIdList){
		List<String> friendGroup = new ArrayList<String>();
		
		for(String friendId : friendIdList){
			UserFriend revertFriend = userFriendService.getRevertFriendByFriendID(friendId);
			if(revertFriend == null){
				logger.info("InfoExploreService - requestToShare: friendId " + friendId +" can not be found and discard");
				continue;
			}
			Integer shareFriend = revertFriend.getShareFriend();
			if(isRequest==0&&shareFriend!=0){
				friendGroup.add(friendId);
			}else if(isRequest!=0&&shareFriend==0){
				friendGroup.add(friendId);
			}		
		}
		if(friendGroup.size()>0){
			userFriendService.requestUserFriendShareByIdGroup(friendGroup, isRequest);
			userFriendService.clearFriendGroupCache(friendGroup);
		}
	}
	
	//agreeToShare  (param isAgree= 0 or 1) result: isAgree=1 share_friend=2; isAgree=0,share_friend share_friend from 2 to 1
	public Boolean agreeToShare(Integer isAgree,String friendId){
		logger.info("InfoExploreService - requestToShare: isAgree\n" + isAgree);
		logger.info("InfoExploreService - requestToShare: friendId\n" + friendId);

		UserFriend friend = userFriendService.getUserFriendByFriendId(friendId);
		if(friend == null)
			return false;
		
		Integer shareFriend = friend.getShareFriend();
		if(shareFriend == 0){
			return false;
		}
		if(isAgree == 1 && shareFriend == 1){
			shareFriend = 2;
		}else if(isAgree == 0 && shareFriend == 2){
			shareFriend = 1;
		}else{
			return true;
		}
		userFriendService.updateUserFriendShareById(friend.getId(), shareFriend);
		userFriendService.clearUserCache();
		userFriendService.clearSharingFriendsCache();
		return true;
	}
	
/*	public List<UserFriend> getMyRquestedFriends(){
		logger.info("InfoExploreService - getMyRquestedFriends\n");
		
		List<UserFriend> myFriends = userFriendService.getAllFriendsOfUser();
		List<UserFriend> myRevertFriends = userFriendService.getMyRequestedFriends();
		for(UserFriend friend:myFriends){
			friend.setShareFriend(0);
			for(UserFriend revertFriend:myRevertFriends){
				if(friend.getFriendId() == revertFriend.getUserId())
					friend.setShareFriend(revertFriend.getShareFriend());
			}			
		}
		
		return myFriends;
	}*/
	
	public void createExplorationInfo(InfoExplorable infoExplorable) {
		logger.info("InfoExploreService - createExplorationInfo: infoExplorable:\n" + infoExplorable);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		infoExplorable.setUserId(currentUser.getUserid());
		infoExplorable.setCreatedTime(new Date().getTime());
		infoExplorableDao.save(infoExplorable);	
	}
	
	@Transactional
	public void createExplorationInfo(String infoTitle,String infoContent,String infoAddition,List<MultipartFile> postImages) throws CommonException {
		InfoExplorable infoExplorable = new InfoExplorable(infoTitle, infoContent, infoAddition);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		infoExplorable.setUserId(currentUser.getUserid());
		infoExplorable.setCreatedTime(new Date().getTime());
		logger.info("InfoExploreService - createExplorationInfo: infoExplorable:\n" + infoExplorable);
		InfoExplorable existed = getInfoByUserid(currentUser.getUserid());
		if(existed.getId()!=null){
			deleteExplorationInfo(currentUser.getUserid());
		}
		List<FileUpload> uploadImgFile = new ArrayList<FileUpload>();
		List<MultipartFile> uploadImages = new ArrayList<MultipartFile>();
		try {
			for(MultipartFile InfoIconFile : postImages){
				if((InfoIconFile.getContentType().toLowerCase().equals("image/jpg")   
	                    || InfoIconFile.getContentType().toLowerCase().equals("image/jpeg")   
	                    || InfoIconFile.getContentType().toLowerCase().equals("image/png"))){
					uploadImages.add(InfoIconFile);
				}else{
					 continue;
				}
			}
			uploadImgFile = fileUploadService.uploadImgFile(currentUser.getUserid(), FileType.ExplorImage, uploadImages.toArray(new MultipartFile[0]));
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommonException(111103, "Explore image IO EXception");
		}
		List<String> uploadImagesList = new ArrayList<String>();
		uploadImgFile.stream().forEach(s->{
			uploadImagesList.add(s.getFileUrl()+";"+s.getThumbnailUrl());
		});
		infoExplorable.setPostImages(StringUtils.join(uploadImagesList, '|'));
		infoExplorableDao.save(infoExplorable);	
		clearUserInfoCache(currentUser.getUserid());
	}
	
	@Transactional
	public void deleteExplorationInfo() {
		logger.info("InfoExploreService - deleteExlorationInfo\n" );
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		infoExplorableDao.DeleteByUserId(currentUser.getUserid());
		userFriendService.clearRequestUserFriendShareByUserId(currentUser.getUserid());
		userFriendService.clearUserFriendGroupCache(userFriendService.getAllFriendsByUserId(currentUser.getUserid()));
		clearUserInfoCache(currentUser.getUserid());
	}
	
	@Transactional
	public void deleteExplorationInfo(String userId) {
		logger.info("InfoExploreService - deleteExplorationInfo userId:" + userId);
		infoExplorableDao.DeleteByUserId(userId);
		//userFriendService.clearRequestUserFriendShareByUserId(userId);
		userFriendService.clearUserFriendGroupCache(userFriendService.getAllFriendsByUserId(userId));
		clearUserInfoCache(userId);
	}
	
	public void clearUserInfoCache(String userId){
		String md5key = redisService.getMD5CacheKey(CacheType.infoExplore, userId);
		redisService.remove(md5key);
	}
	
	
	public InfoExplorable getInfoByUserid(String userid){
		logger.info("InfoExploreService - getInfoByUserid: userid="+userid);
		String md5key = redisService.getMD5CacheKey(CacheType.infoExplore, userid);
		logger.info("UserService - getInfoByUserid: md5key="+md5key);
		InfoExplorable info = redisService.getObject(md5key, InfoExplorable.class);
		if(info == null){
			info = infoExplorableDao.findByUserId(userid);
			if(info != null){
				redisService.setObjct(md5key, info);
			}else{
				info = new InfoExplorable();
				redisService.setObjct(md5key, info, GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return info;
	}
	
	public UserExploreInfo getMyExploreInfo(){
		logger.info("UserFriendService - getMyExploreInfo...");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		InfoExplorable infoExplorable = getInfoByUserid(currentUser.getUserid());
		User user = userService.getUserByUserid(currentUser.getUserid());
		UserExploreInfo userExploreInfo = new UserExploreInfo(user,infoExplorable);
		if(infoExplorable.getId()!=null){
			userExploreInfo.setViews(getViewNumOfExploreInfo(infoExplorable.getId()));
			userExploreInfo.setThumbsUp(getLaudForExploreInfo(infoExplorable.getId()));
			userExploreInfo.setThumbsUpStatus(getLaudStatusForExploreInfo(infoExplorable.getId()).get("exploreStatus"));
			userExploreInfo.setCommentNum(commentService.getCommentNumByInfoId(infoExplorable.getId()));
		}
		return userExploreInfo;
	}
	
	public List<UserExploreInfo> getShareFriendInfoByUserId(String userId,Integer isAll){
		logger.info("UserFriendService - getShareFriendInfoByUserId userId:" + userId);
		logger.info("UserFriendService - getShareFriendInfoByUserId isAll:" + isAll);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserFriendService - getShareFriendInfoByUserId currentUser:\n" + currentUser);
		//List<UserFriend> allSharingFriends = userFriendService.getInfoSharingFriendsByUserId(userId);
		if(userId.equals(currentUser.getUserid())&&isAll==1){
			List<UserFriend> allSharingFriends = userFriendService.getAllFriendsOfUser();
			List<UserExploreInfo> userExploreInfoList = allSharingFriends.stream().map(s->{
				InfoExplorable userinfo = getInfoByUserid(s.getFriendId());
				User user = userService.getUserByUserid(s.getFriendId());
			    return new UserExploreInfo(user,userinfo,s,isAll);
			}).filter(s->s.getInfo().getId()!=null)
			.peek(s->{
					s.setViews(getViewNumOfExploreInfo(s.getInfo().getId()));
					s.setThumbsUp(getLaudForExploreInfo(s.getInfo().getId()));
					s.setThumbsUpStatus(getLaudStatusForExploreInfo(s.getInfo().getId()).get("exploreStatus"));
					s.setCommentNum(commentService.getCommentNumByInfoId(s.getInfo().getId()));
			})
			.sorted((s1,s2)->s1.getInfo().getCreatedTime().compareTo(s2.getInfo().getCreatedTime()))
			.collect(Collectors.toList());
			UserExploreInfo currentUserExploreInfo = getMyExploreInfo();
			if(currentUserExploreInfo.getInfo().getId()!=null){
				userExploreInfoList.add(0, currentUserExploreInfo);
			}
			return userExploreInfoList;
		}else{
			List<UserFriend> allSharingFriends = userFriendService.getInfoSharingFriendsByUserId(userId);
			return allSharingFriends.stream().filter(s->s.getShareFriend()==2)
			    .map(s->{
				    InfoExplorable userinfo = getInfoByUserid(s.getFriendId());
				    User user = userService.getUserByUserid(s.getFriendId());
				    UserFriend currentFriend = userFriendService.getUserFriendByFriendId(s.getFriendId());
				    if(currentFriend!=null){
				    	return new UserExploreInfo(user,userinfo,s).friendStatus(1);
				    }else{
				    	return new UserExploreInfo(user,userinfo,s);
				    }
			}).filter(s->s.getInfo().getId()!=null)
			.peek(s->{
					s.setViews(getViewNumOfExploreInfo(s.getInfo().getId()));
					s.setThumbsUp(getLaudForExploreInfo(s.getInfo().getId()));
					s.setThumbsUpStatus(getLaudStatusForExploreInfo(s.getInfo().getId()).get("exploreStatus"));
					s.setCommentNum(commentService.getCommentNumByInfoId(s.getInfo().getId()));
			})
			.sorted((s1,s2)->s1.getInfo().getCreatedTime().compareTo(s2.getInfo().getCreatedTime()))
			.collect(Collectors.toList());
		}
	}
	
	public List<UserExploreInfo> exploreUserFreindInfos(String userId,Integer isAll){
		logger.info("InfoExploreService - exploreUserFreindInfos userId:" +userId);
		logger.info("InfoExploreService - exploreUserFreindInfos isAll:" + isAll);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserFriendService - exploreUserFreindInfos currentUser:\n" + currentUser);
		
		if(userId.equals(currentUser.getUserid())&&isAll==1){
			List<UserFriend> allSharingFriends = userFriendService.getAllFriendsOfUser();
			return allSharingFriends.parallelStream().map(s->{
				User user = userService.getUserByUserid(s.getFriendId());
				Long agreedNum = userFriendService.getInfoSharingFriendsByUserId(s.getFriendId()).stream().filter(v->v.getShareFriend()==2).count();
				return new UserExploreInfo(user,agreedNum);
			})
			.sorted((s1,s2)->s2.getAgreedNum().compareTo(s1.getAgreedNum()))
			.collect(Collectors.toList());
		}else{
			List<UserFriend> allSharingFriends = userFriendService.getInfoSharingFriendsByUserId(userId);
			return allSharingFriends.parallelStream().filter(s->s.getShareFriend()==2)
			    .map(s->{
			    	User user = userService.getUserByUserid(s.getFriendId());
					Long agreedNum = userFriendService.getInfoSharingFriendsByUserId(s.getFriendId()).stream().filter(v->v.getShareFriend()==2).count();
					return new UserExploreInfo(user,agreedNum);
			})
			.sorted((s1,s2)->s2.getAgreedNum().compareTo(s1.getAgreedNum()))
			.collect(Collectors.toList());
		}
	}
	
	public Long getViewNumOfExploreInfo(Long infoId){
		String md5key = redisService.getMD5CacheKey(CacheType.infoViews, infoId);
		Long result = redisService.incrBy(md5key, 1l);
		return result;
	}
	
	public boolean giveLaudForExploreInfo(Long infoId) {
		 logger.info("InfoExploreService - giveLaudForExploreInfo for infoId:" + infoId);
		 User currentUser = UserUtil.getCurrentLoginUserInfo();
		 String md5key=redisService.getMD5CacheKey(CacheType.infoThumbsup, infoId);
		 logger.info("InfoExploreService - giveLaudForExploreInfo md5key:" + md5key);
		 String currentUserId = currentUser.getUserid();
		 Long result = redisService.sadd(md5key, currentUserId);
		 logger.info("InfoExploreService - giveLaudForExploreInfo result:" + result);
		 if(result==0){
			 return false;
		 }else{
			 return true;
		 }
	}
	
	public Map<String, Integer> getLaudStatusForExploreInfo(Long infoId) {
		 Map<String, Integer> status = new HashMap<String,Integer>();
		 logger.info("InfoExploreService - getLaudStatusForExploreInfo for infoId:" + infoId);
		 User currentUser = UserUtil.getCurrentLoginUserInfo();
		 String md5key=redisService.getMD5CacheKey(CacheType.infoThumbsup, infoId);
		 logger.info("InfoExploreService - getLaudStatusForExploreInfo md5key:" + md5key);
		 String currentUserId = currentUser.getUserid();
		 Boolean result = redisService.setContain(md5key, currentUserId);
		 logger.info("UserService - InfoExploreService result:" + result);
		 if(result){
			 status.put("exploreStatus", 1);
		 }else{
			 status.put("exploreStatus", 0);
		 }
		 return status;
	}
	
	public boolean removeLaudForExploreInfo(Long infoId) {
		 logger.info("InfoExploreService - removeLaudForExploreInfo for infoId:" + infoId);
		 User currentUser = UserUtil.getCurrentLoginUserInfo();
		 String md5key=redisService.getMD5CacheKey(CacheType.infoThumbsup, infoId);
		 logger.info("InfoExploreService - removeLaudForExploreInfo md5key:" + md5key);
		 String currentUserId = currentUser.getUserid();
		 Long result = redisService.sRem(md5key, currentUserId);
		 logger.info("InfoExploreService - removeLaudForExploreInfo result:" + result);
		 if(result==0){
			 return false;
		 }else{
			 return true;
		 }
	}
	
	public List<UserSearchInfo> getAllLaudUsersForExploreInfo(Long infoId) {
		 logger.info("InfoExploreService - getAllLaudUsersForExploreInfo for infoId:" + infoId);
		 String md5key=redisService.getMD5CacheKey(CacheType.infoThumbsup, infoId);
		 logger.info("InfoExploreService - getAllLaudUsersForExploreInfo md5key:" + md5key);
		 List<String> result = redisService.getObjectFromSet(md5key, String.class);
		 logger.info("InfoExploreService - getAllLaudUsersForExploreInfo result size:" + result.size());
		 return result.parallelStream().map(s->{
			 User user = userService.getUserByUserid(s);
			 return new UserSearchInfo(user,null);
		 }).collect(Collectors.toList());
	}

	public Long getLaudForExploreInfo(Long infoId) {
		logger.info("InfoExploreService - getLaudForExploreInfo for infoId:" + infoId);
		String md5key=redisService.getMD5CacheKey(CacheType.infoThumbsup, infoId);
		logger.info("InfoExploreService - getLaudForExploreInfo md5key:" + md5key);
		return redisService.setCount(md5key);
	}
	
	/* -----------------------------------------------------------------------------------------------*/
	/*public List<Map<String,Object>> getMyFriend_Infos() {
		logger.info("InfoExploreService - getMyFriend_Infos\n" );
		//get all friend of this user.
		List<UserFriend> myFriends = userFriendService.getAllFriendsOfUser();
		
		List<Map<String,Object>> sortedList = myFriends.parallelStream()
				.filter(s->s.getShareFriend()!=0)
				.map(s->{
					Map<String,Object> richInfo = new HashMap<String,Object>();
					InfoExplorable info = getInfoByUserid(s.getFriendId());
					richInfo.put("user_friend", s);
					richInfo.put("info_explorable", info);
					return richInfo;
				})
				.sorted((s1,s2)->((InfoExplorable)s1.get("info_explorable")).getcreatedTime().compareTo(((InfoExplorable)s2.get("info_explorable")).getcreatedTime()))
				.collect(Collectors.toList());
		return sortedList;

	}
	
	public List<Map<String,Object>> getMyFriends() {
		logger.info("InfoExploreService - getMyFriends\n" );
		
		List<Map<String,Object>> richFriends = new ArrayList<Map<String,Object>>();
		
		//get all friend of this user.
		List<UserFriend> myFriends = userFriendService.getAllFriendsOfUser();	
		
		//get the sharing informations of these subordinate friends. 
		Iterator<UserFriend> it = myFriends.iterator();
		while(it.hasNext()){
			UserFriend us = it.next();
			Long agreedFriendsNum = userFriendService.getAgreedFriendsNum(us.getFriendId());
			Map<String,Object> richFriend = new HashMap<String,Object>();
			richFriend.put("user_friend", us);
			richFriend.put("agreed_number", agreedFriendsNum);
			
			richFriends.add(richFriend);
		}
		
		List<Map<String,Object>> friendsResult = richFriends.parallelStream().sorted((s1,s2)->((Long)s1.get("agreed_number")).compareTo(((Long)s2.get("agreed_number")))).collect(Collectors.toList());
		
		return friendsResult;
	}
	
	public List<Map<String,Object>> exploreAUser_Infos(String userId) {
		logger.info("InfoExploreService - exploreAUser_Infos\n" +userId);
		
		List<Map<String,Object>> infoResult = new ArrayList<Map<String,Object>>();
		
		//get all friend of this user in condition that they agreed with an info sharing.
		List<UserFriend> friendsWithAgreement = userFriendService.getInfoSharingFriends(userId);
		
		List<UserFriend> sortedList = friendsWithAgreement.parallelStream().sorted((s1,s2)->s1.getCreateDate().compareTo(s2.getCreateDate())).collect(Collectors.toList());
		
		//get the sharing informations of these subordinate friends. 
		Iterator<UserFriend> it = sortedList.iterator();
		while(it.hasNext()){
			UserFriend us = it.next();
			InfoExplorable info = infoExplorableDao.findByUserId(us.getUserId());
			Map<String,Object> richInfo = new HashMap<String,Object>();
			richInfo.put("user_friend", us);
			richInfo.put("info_explorable", info);
			
			infoResult.add(richInfo);
		}
		
		return infoResult;

	}
	
	public List<Map<String,Object>> exploreAUser_Friends(String userId) {
		logger.info("InfoExploreService - exploreAUser_Friends\n" +userId);
		
		List<Map<String,Object>> richFriends = new ArrayList<Map<String,Object>>();
		
		//get all friend of this user in condition that they agreed with an info sharing.
		List<UserFriend> friendsWithAgreement = userFriendService.getInfoSharingFriends(userId);		
		
		//get the sharing informations of these subordinate friends. 
		Iterator<UserFriend> it = friendsWithAgreement.iterator();
		while(it.hasNext()){
			UserFriend us = it.next();
			Long agreedFriendsNum = userFriendService.getAgreedFriendsNum(us.getUserId());
			Map<String,Object> richFriend = new HashMap<String,Object>();
			richFriend.put("user_friend", us);
			richFriend.put("agreed_number", agreedFriendsNum);
			
			richFriends.add(richFriend);
		}
		
		List<Map<String,Object>> friendsResult = richFriends.parallelStream().sorted((s1,s2)->((Long)s1.get("agreed_number")).compareTo(((Long)s2.get("agreed_number")))).collect(Collectors.toList());
		
		return friendsResult;

	}	*/
	/* -----------------------------------------------------------------------------------------------*/
	
	
}
