package io.swagger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.swagger.common.CacheType;
import io.swagger.common.GlobalConstants;
import io.swagger.dao.UserFriendDao;
import io.swagger.dao.UserFriendRequestDao;
import io.swagger.dao.UserLableDao;
import io.swagger.exception.NotFoundException;
import io.swagger.model.User;
import io.swagger.model.UserFriend;
import io.swagger.model.UserFriendInfo;
import io.swagger.model.UserFriendRequest;
import io.swagger.model.UserLableMap;
import io.swagger.model.UserSearchInfo;
import io.swagger.service.common.MQMessageService;
import io.swagger.service.common.RedisService;
import io.swagger.utils.PinYinUtil;
import io.swagger.utils.UserUtil;
import io.swagger.utils.StringUtil;

@Service
public class UserFriendService{
	
	private Logger logger = LoggerFactory.getLogger(UserFriendService.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFriendDao userFriendDao;
	
	@Autowired
	private UserLableDao userLableDao;
	
	@Autowired
	private RedisService redisService;
	
	@Transactional
	public List<UserLableMap> getLableMaps(Integer lableType){
		logger.info("UserFriendService - getLableMap: labelType\n" + lableType);
		String md5key = redisService.getMD5CacheKey(CacheType.friendLableList, lableType);
		
		List<UserLableMap> userLableList = redisService.getList(md5key, UserLableMap.class);
		if(userLableList == null){
			userLableList = userLableDao.findByLableType(lableType);			
			if(userLableList != null){
				redisService.setList(md5key, userLableList);
			}
			else{
				userLableList = new ArrayList<UserLableMap>();
				redisService.setList(md5key, userLableList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		
		return userLableList;
	}
	
	
	@Transactional
	public void updateFriendInfoForUser(String friendId,UserFriend friend) throws NotFoundException{
		logger.info("UserFriendService - Create New User Friend: friend\n" + friend);
		User fuser = userService.getUserByUserid(friendId);
		if(fuser == null){
			throw new NotFoundException(400, "Friend's userid is not found.");
		}
		UserFriend userFriend = getUserFriendByFriendId(friendId);
		if(userFriend==null){
			throw new NotFoundException(402, "IS not your friend now.");
		}
		if(friend!=null){
			userFriend.setNickName(friend.getNickName()!=null?friend.getNickName():userFriend.getNickName());
			userFriend.setNickNamePinYin(friend.getNickName()!=null?PinYinUtil.getPinYin(friend.getNickName()):userFriend.getNickNamePinYin());
			userFriend.setLabelRelationship(friend.getLabelRelationship()!=null?friend.getLabelRelationship():userFriend.getLabelRelationship());
			userFriend.setLabelImpression(friend.getLabelImpression()!=null?friend.getLabelImpression():userFriend.getLabelImpression());
			userFriend.setIsFavorite(friend.getIsFavorite()!=null?friend.getIsFavorite():userFriend.getIsFavorite());
			userFriend.setFriendOrigin(friend.getFriendOrigin()!=null?friend.getFriendOrigin():userFriend.getFriendOrigin());
			userFriend.setDescription(friend.getDescription()!=null?friend.getDescription():userFriend.getDescription());
		}
		userFriendDao.updateUserFriendInfo(userFriend.getId(),userFriend.getNickName(),userFriend.getNickNamePinYin(),userFriend.getLabelRelationship(),userFriend.getLabelImpression(),userFriend.getIsFavorite(),userFriend.getFriendOrigin(),userFriend.getDescription());
		clearUserCache();
	}
	
	@Transactional
	public void updateUserFriendStatusById(Long id,Integer status){
		logger.info("UserFriendService - updateUserFriendStatusById id:" + id);
		logger.info("UserFriendService - updateUserFriendStatusById status:" + status);
		userFriendDao.updateUserFriendStatusById(id, status, new Date().getTime());
	}
	
	@Transactional
	public void updateUserFriendShareById(Long id,Integer shareFriend){
		logger.info("UserFriendService - updateUserFriendShareById id:" + id);
		logger.info("UserFriendService - updateUserFriendShareById status:" + shareFriend);
		userFriendDao.updateUserFriendShareById(id, shareFriend);
	}
	
	@Transactional
	public void updateUserFriendShareByIdGroup(List<Long> idGroup,Integer shareFriend){
		logger.info("UserFriendService - updateUserFriendShareByIdGroup idGroup size:" + idGroup.size());
		logger.info("UserFriendService - updateUserFriendShareByIdGroup shareFriend:" + shareFriend);
		userFriendDao.updateUserFriendShareByIdGroup(idGroup, shareFriend);
	}
	
	@Transactional
	public void requestUserFriendShareByIdGroup(List<String> friendGroup,Integer shareFriend){
		logger.info("UserFriendService - requestUserFriendShareByIdGroup friendGroup size:" + friendGroup.size());
		logger.info("UserFriendService - requestUserFriendShareByIdGroup shareFriend:" + shareFriend);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		userFriendDao.requestUserFriendShareByIdGroup(friendGroup,currentUser.getUserid(),shareFriend);
	}
	
	@Transactional
	public void clearRequestUserFriendShareByUserId(String userId){
		logger.info("UserFriendService - clearRequestUserFriendShareByUserId userId:" + userId);
		userFriendDao.clearRequestUserFriendShareByUserId(userId);
	}
	
	public List<UserFriend> getAllFriendsOfUser(){
		logger.info("UserFriendService - getAllFriendsOfUser...");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserFriendService - getAllFriendsOfUser currentUser:\n" + currentUser);
		String md5key = redisService.getMD5CacheKey(CacheType.friendList, currentUser.getUserid());
		
		List<UserFriend> friendsList  = redisService.getList(md5key, UserFriend.class);
		if(friendsList == null){
			friendsList = userFriendDao.findAllConfirmedFriendsOfUser(currentUser.getUserid());
			if(friendsList != null){
				redisService.setList(md5key, friendsList);
			}else{
				friendsList =  new ArrayList<UserFriend>();
				redisService.setList(md5key, friendsList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return friendsList;
	}
	
	public List<UserFriend> getMyRequestedFriends(){
		logger.info("UserFriendService - getMyRevertFriends...");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		List<UserFriend> friendsList = userFriendDao.findRequestedFriendsofUser(currentUser.getUserid());
		return 	friendsList;	
	}
	
	public List<UserFriend> getAllFriendsByUserId(String userId){
		logger.info("UserFriendService - getAllFriendsByUserId userId:"+userId);
		String md5key = redisService.getMD5CacheKey(CacheType.friendList, userId);
		List<UserFriend> friendsList  = redisService.getList(md5key, UserFriend.class);
		if(friendsList == null){
			friendsList = userFriendDao.findAllConfirmedFriendsOfUser(userId);
			if(friendsList != null){
				redisService.setList(md5key, friendsList);
			}else{
				friendsList =  new ArrayList<UserFriend>();
				redisService.setList(md5key, friendsList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return friendsList;
	}
	
	public UserFriend getRevertFriendByFriendID(String friendId){
		logger.info("UserFriendService - getRevertFriendByFriendID friendId:"+friendId);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		List<UserFriend> friendsList = getAllFriendsByUserId(friendId);
		Optional<UserFriend> UserFriendOptional = friendsList.stream().filter(s->s.getFriendId().equals(currentUser.getUserid())).findFirst();
		return UserFriendOptional.orElseGet(()->{return null;});
	}
	
	public List<UserFriend> getInfoSharingFriends(String userId){
		logger.info("UserFriendService - getInfoSharingFriends userId:"+userId);
		/*String md5key = redisService.getMD5CacheKey(CacheType.friendList, userId);
		List<UserFriend> friendsList  = redisService.getList(md5key, UserFriend.class);
		if(friendsList == null){
			friendsList = userFriendDao.findAllConfirmedFriendsOfUser(userId);
			if(friendsList != null){
				redisService.setList(md5key, friendsList);
			}else{
				friendsList =  new ArrayList<UserFriend>();
				redisService.setList(md5key, friendsList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}*/
		
		List<UserFriend> friendsList = userFriendDao.findAllInfoSharingFriends(userId);
		return friendsList;
	}
	
	public Long getAgreedFriendsNum(String userId){
		logger.info("UserFriendService - getAgreedFriendsNum userId:"+userId);
		Long agreedFriendsNum = userFriendDao.getAgreedFriendsNum(userId);
		return agreedFriendsNum;
	}
	
	
	public List<UserFriend> getInfoSharingFriendsByUserId(String userId){
		logger.info("UserFriendService - getInfoSharingFriendsByUserId userId:"+userId);
		String md5key = redisService.getMD5CacheKey(CacheType.shareFriendList, userId);
		List<UserFriend> sharefriendsList  = redisService.getList(md5key, UserFriend.class);
		if(sharefriendsList == null){
			sharefriendsList = userFriendDao.findAllSharingFriendsByUserId(userId);
			if(sharefriendsList != null){
				redisService.setList(md5key, sharefriendsList);
			}else{
				sharefriendsList =  new ArrayList<UserFriend>();
				redisService.setList(md5key, sharefriendsList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		
		return sharefriendsList;
	}
	
	public Map<String,Map<String,List<UserSearchInfo>>> getFriendMapOfUser(){
		logger.info("UserFriendService - getFriendMapOfUser...");
		List<UserFriend> userFriendList = getAllFriendsOfUser();
		
		//tz add friend share status for requesting explorable information sharing
		List<UserFriend> myRevertFriends = getMyRequestedFriends();
		List<UserSearchInfo> friendList = userFriendList.parallelStream().map(s->{
			User friendUser = userService.getUserByUserid(s.getFriendId());
			return new UserSearchInfo(friendUser,s);
		}).collect(Collectors.toList());
		
		for(UserSearchInfo myFriend:friendList){
			myFriend.setShareFriend(0);
			for(UserFriend revertFriend:myRevertFriends){
				if(myFriend.getFriendId().equals(revertFriend.getUserId()))
					myFriend.setShareFriend(revertFriend.getShareFriend());
			}			
		}
		
		Map<String,Map<String,List<UserSearchInfo>>> userFriendMap = friendList.parallelStream().sorted((s1,s2)->s1.getUserNamePinYin().compareTo(s2.getUserNamePinYin())).collect(Collectors.groupingBy(UserSearchInfo::getFirstAlphabet,Collectors.groupingBy(UserSearchInfo::getFirstCharacter)));
		return new TreeMap<String,Map<String,List<UserSearchInfo>>>(userFriendMap);
	}
	
	public List<UserSearchInfo> getCommonFriendsForUser(String friendId){
		logger.info("UserService - getCommonFriendsForUser friendId:" + friendId);
		List<UserFriend> hisFriendList = getAllFriendsByUserId(friendId);
		List<UserFriend> myFriendList = getAllFriendsOfUser();
		List<UserFriend> totoalList = new ArrayList<UserFriend>();
		totoalList.addAll(myFriendList);
		totoalList.addAll(hisFriendList);
		Map<String,Integer> compareMap = new HashMap<String,Integer>();
		totoalList.parallelStream().forEach(s->{
			Integer count = compareMap.getOrDefault(s.getFriendId(), 0);
			compareMap.put(s.getFriendId(), count+1);
			});
		Set<String> friendSet = new HashSet<String>();
		compareMap.forEach((k,v)->{if(v==2){
			friendSet.add(k);
		}});
		List<UserSearchInfo> commonList = myFriendList.parallelStream().filter(s->friendSet.contains(s.getFriendId())).map(s->{
			User friendUser = userService.getUserByUserid(s.getFriendId());
			return new UserSearchInfo(friendUser,s);
		}).collect(Collectors.toList());
		return commonList;
	}
	
	public Map<String, Integer> getNumCommonFriendsForUser(String friendId) {
		logger.info("UserService - getCommonFriendsForUser friendId:" + friendId);
		Map<String, Integer> result = new HashMap<String,Integer>();
		List<UserFriend> hisFriendList = getAllFriendsByUserId(friendId);
		List<UserFriend> myFriendList = getAllFriendsOfUser();
		List<UserFriend> totoalList = new ArrayList<UserFriend>();
		totoalList.addAll(myFriendList);
		totoalList.addAll(hisFriendList);
		Map<String,Integer> compareMap = new HashMap<String,Integer>();
		totoalList.parallelStream().forEach(s->{
			Integer count = compareMap.getOrDefault(s.getFriendId(), 0);
			compareMap.put(s.getFriendId(), count+1);
			});
		Set<String> friendSet = new HashSet<String>();
		compareMap.forEach((k,v)->{if(v==2){
			friendSet.add(k);
		}});
		result.put(friendId, friendSet.size());
		return result;
	}
	
	public List<UserSearchInfo> searchFriendsOfUser(String keyWord){
		logger.info("UserService - searchFriendsOfUser keyWord:"+keyWord);
		if(StringUtils.isEmpty(keyWord)){
			return new ArrayList<UserSearchInfo>();
		}
		List<UserFriend> friendList = getAllFriendsOfUser();
		List<UserSearchInfo>  friendInfoList = friendList.parallelStream().map(s->{
			User friendUser = userService.getUserByUserid(s.getFriendId());
			return new UserSearchInfo(friendUser,s);
		}).collect(Collectors.toList());
		if(StringUtil.isContainChinese(keyWord)){
			friendInfoList = friendInfoList.stream().filter(s->(StringUtil.isContainWord(s.getNickName(), keyWord)||StringUtil.isContainWord(s.getUserName(), keyWord))).collect(Collectors.toList());
		}else if(StringUtil.isNumeric(keyWord)){
			friendInfoList = friendInfoList.stream().filter(s->StringUtil.isContainWord(s.getUserid(), keyWord)).collect(Collectors.toList());
		}else if(StringUtil.isAlphabet(keyWord)){
			friendInfoList = friendInfoList.stream().filter(s->(StringUtil.isContainAlphabet(s.getNickNamePinYin(), keyWord.toLowerCase(),"_")||StringUtil.isContainAlphabet(s.getUserNamePinYin(), keyWord.toLowerCase(),"_"))).collect(Collectors.toList());
		}else{
			friendInfoList = friendInfoList.stream().filter(s->(StringUtil.isContainWord(s.getNickNamePinYin(), keyWord.toLowerCase())||StringUtil.isContainWord(s.getUserNamePinYin(), keyWord.toLowerCase()))).collect(Collectors.toList());
		}
		return friendInfoList;
	}
	
	public Map<String,Map<String,List<UserSearchInfo>>> searchFriendMapOfUser(String keyWord){
		logger.info("UserService - searchFriendMapOfUser keyWord:"+keyWord);
		if(StringUtils.isEmpty(keyWord)){
			return new HashMap<String,Map<String,List<UserSearchInfo>>>();
		}
		List<UserSearchInfo> friendList = searchFriendsOfUser(keyWord);
		
		//tz add friend share status for requesting explorable information sharing
		List<UserFriend> myRevertFriends = getMyRequestedFriends();
		for(UserSearchInfo myFriend:friendList){
			myFriend.setShareFriend(0);
			for(UserFriend revertFriend:myRevertFriends){
				if(myFriend.getFriendId().equals(revertFriend.getUserId()))
					myFriend.setShareFriend(revertFriend.getShareFriend());
			}			
		}
		
		Map<String,Map<String,List<UserSearchInfo>>> userFriendMap = friendList.parallelStream().sorted((s1,s2)->s1.getUserNamePinYin().compareTo(s2.getUserNamePinYin())).collect(Collectors.groupingBy(UserSearchInfo::getFirstAlphabet,Collectors.groupingBy(UserSearchInfo::getFirstCharacter)));
		return new TreeMap<String,Map<String,List<UserSearchInfo>>>(userFriendMap);
	}
	
	public UserFriend getUserFriendByFriendId(String friendId){
    	logger.info("UserService - getUserFriendByFriendId friendId:"+friendId);
		List<UserFriend> UserFriendList = getAllFriendsOfUser();
		Optional<UserFriend> UserFriendOptional = UserFriendList.stream().filter(s->s.getFriendId().equals(friendId)).findFirst();
		return UserFriendOptional.orElseGet(()->{return null;});
	}
	
	public UserFriendInfo getUserFriendInfoByFriendId(String friendId){
    	logger.info("UserService - getUserFriendInfoByFriendId friendId:"+friendId);
    	UserFriendInfo userFriendInfo = null;
    	UserFriend userFriend = getUserFriendByFriendId(friendId);
    	if(userFriend!=null){
    		User friendUser = userService.getUserByUserid(userFriend.getFriendId());
    		userFriendInfo = new UserFriendInfo(friendUser, userFriend);
    	}
		return userFriendInfo;
	}
	
	public void clearUserCache(){
		logger.info("UserService - clearUserCache...");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserService - clearUserCache currentUser:\n" + currentUser);
		String md5key_friendList = redisService.getMD5CacheKey(CacheType.friendList, currentUser.getUserid());
		String md5key_myFavoriteList = redisService.getMD5CacheKey(CacheType.myFavoriteList, currentUser.getUserid());
		logger.info("UserService - clearUserCache md5key_friendList:\n" + md5key_friendList);
		logger.info("UserService - clearUserCache md5key_myFavoriteList:\n" + md5key_myFavoriteList);
		redisService.remove(md5key_friendList);
		redisService.remove(md5key_myFavoriteList);
	}
	
	public void clearSharingFriendsCache(String userId){
		logger.info("UserService - clearSharingFriendsCache userId:" + userId);
		String md5key_sharefriendList = redisService.getMD5CacheKey(CacheType.shareFriendList, userId);
		logger.info("UserService - clearSharingFriendsCache md5key_sharefriendList:" + md5key_sharefriendList);
		redisService.remove(md5key_sharefriendList);
	}
	
	public void clearSharingFriendsCache(){
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserService - clearSharingFriendsCache currentUser:\n" + currentUser);
		String md5key_sharefriendList = redisService.getMD5CacheKey(CacheType.shareFriendList, currentUser.getUserid());
		logger.info("UserService - clearSharingFriendsCache md5key_sharefriendList:" + md5key_sharefriendList);
		redisService.remove(md5key_sharefriendList);
	}
	
	public void clearFriendGroupCache(List<String> friendGroup){
		logger.info("UserService - clearFriendGroupCache...");
		for(String friendId : friendGroup){
			String md5key_friendList = redisService.getMD5CacheKey(CacheType.friendList, friendId);
			String md5key_myFavoriteList = redisService.getMD5CacheKey(CacheType.myFavoriteList, friendId);
			String md5key_sharefriendList = redisService.getMD5CacheKey(CacheType.shareFriendList, friendId);
			logger.info("UserService - clearFriendGroupCache md5key_friendList:\n" + md5key_friendList);
			logger.info("UserService - clearFriendGroupCache md5key_myFavoriteList:\n" + md5key_myFavoriteList);
			logger.info("UserService - clearSharingFriendsCache md5key_sharefriendList:" + md5key_sharefriendList);
			redisService.remove(md5key_friendList);
			redisService.remove(md5key_myFavoriteList);
			redisService.remove(md5key_sharefriendList);
		}
	}
	
	public void clearUserFriendGroupCache(List<UserFriend> friendGroup){
		logger.info("UserService - clearUserFriendGroupCache...");
		for(UserFriend friendId : friendGroup){
			String md5key_friendList = redisService.getMD5CacheKey(CacheType.friendList, friendId.getFriendId());
			String md5key_myFavoriteList = redisService.getMD5CacheKey(CacheType.myFavoriteList, friendId.getFriendId());
			String md5key_sharefriendList = redisService.getMD5CacheKey(CacheType.shareFriendList, friendId.getFriendId());
			logger.info("UserService - clearUserFriendGroupCache md5key_friendList:\n" + md5key_friendList);
			logger.info("UserService - clearUserFriendGroupCache md5key_myFavoriteList:\n" + md5key_myFavoriteList);
			logger.info("UserService - clearUserFriendGroupCache md5key_sharefriendList:" + md5key_sharefriendList);
			redisService.remove(md5key_friendList);
			redisService.remove(md5key_myFavoriteList);
			redisService.remove(md5key_sharefriendList);
		}
	}
	
	public List<UserSearchInfo> getMyFavoriteFriends(){
		logger.info("UserFriendService - getMyFavotiteFriends()");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("UserFriendService - getAllFriendsOfUser currentUser:\n" + currentUser);
		String md5key = redisService.getMD5CacheKey(CacheType.myFavoriteList, currentUser.getUserid());
		
		List<UserFriend> friendsList  = redisService.getList(md5key, UserFriend.class);
		if(friendsList == null){
			friendsList = userFriendDao.getFavoriteFriends(currentUser.getUserid());
			if(friendsList != null){
				redisService.setList(md5key, friendsList);
			}else{
				friendsList =  new ArrayList<UserFriend>();
				redisService.setList(md5key, friendsList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return friendsList.parallelStream().map(s->{
			User friendInfo = userService.getUserByUserid(s.getFriendId());
			return new UserSearchInfo(friendInfo,s);
		}).collect(Collectors.toList());
		//return friendsList;		
	}
		
}
