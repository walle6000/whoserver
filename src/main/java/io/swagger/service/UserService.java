package io.swagger.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.common.CacheType;
import io.swagger.common.FileType;
import io.swagger.common.GlobalConstants;
import io.swagger.common.UserStatus;
import io.swagger.dao.UserDao;
import io.swagger.dao.UserProfileDao;
import io.swagger.exception.CommonException;
import io.swagger.exception.NotFoundException;
import io.swagger.listener.handler.UserSearchLogHandler;
import io.swagger.model.FileUpload;
import io.swagger.model.ResultMsg;
import io.swagger.model.User;
import io.swagger.model.UserFriend;
import io.swagger.model.UserProfile;
import io.swagger.model.UserSearchInfo;
import io.swagger.model.UserSearchLog;
import io.swagger.service.common.FileUploadService;
import io.swagger.service.common.RedisService;
import io.swagger.service.common.SMSService;
import io.swagger.utils.PinYinUtil;
import io.swagger.utils.QRCodeUtil;
import io.swagger.utils.StringUtil;
import io.swagger.utils.UserUtil;
import io.swagger.utils.VerifyCodeUtils;

@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserProfileDao userProfileDao;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private SMSService SMSService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private UserFriendService userFriendService;
	
	@Autowired
	private UserSearchLogHandler userSearchLogHandler;
	
	
	@Transactional
	public void createNewUser(User user){
		logger.info("UserService - Create New User: user\n" + user);
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		UserProfile up = new UserProfile();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		QRCodeUtil.QREncode(user.getUserid(), 100, out);
		up.setQrCode(out.toByteArray());
		user.setUserProfile(up);
		saveUser(user);
	}
	
	public void saveUser(User user){
		logger.info("UserService - saveUser: user\n" + user);
		userDao.save(user);
		logger.info("UserService - saveUser done");
	}
	
	@Transactional
	public void updateUserLastLogin(){
		logger.info("UserService - updateUserLastLogin");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		userProfileDao.updateUserLastLogin(currentUser.getId(), new Date().getTime());
		clearUserCache(currentUser.getUserid());
	}
	
	@Transactional
	public void updateUserProfile(UserProfile userProfile){
		logger.info("UserService - saveUser: updateUserProfile\n" + userProfile);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		UserProfile currentuserProfile = currentUser.getUserProfile();
		combineUserProfile(currentuserProfile,userProfile);
		userProfileDao.updateUserProfile(currentuserProfile.getId(),
				currentuserProfile.getUserName(),
				currentuserProfile.getUserPinYin(),
				currentuserProfile.getGender(),
				currentuserProfile.getEmail(),
				currentuserProfile.getPhone(),
				currentuserProfile.getWeixin(),
				currentuserProfile.getQq(),
				currentuserProfile.getHeadIcon(),
				currentuserProfile.getThumbnailheadIcon(),
				currentuserProfile.getSummry(),
				currentuserProfile.getBirthDay(),
				currentuserProfile.getHomeTown(),
				currentuserProfile.getTag(),
				currentuserProfile.getOthers());
		clearUserCache(currentUser.getUserid());
	}
	
	public void combineUserProfile(UserProfile currentuserProfile,UserProfile userProfile){
		if(!StringUtils.isEmpty(userProfile.getUserName())&&!userProfile.getUserName().equals(currentuserProfile.getUserName())){
			currentuserProfile.setUserName(userProfile.getUserName());
			currentuserProfile.setUserPinYin(PinYinUtil.getPinYin(userProfile.getUserName()));
		}
		if(!StringUtils.isEmpty(userProfile.getGender())&&!userProfile.getGender().equals(currentuserProfile.getGender())){
			currentuserProfile.setGender(userProfile.getGender());
		}
		if(!StringUtils.isEmpty(userProfile.getHeadIcon())&&!userProfile.getHeadIcon().equals(currentuserProfile.getHeadIcon())){
			currentuserProfile.setHeadIcon(userProfile.getHeadIcon());
		}
		if(!StringUtils.isEmpty(userProfile.getThumbnailheadIcon())&&!userProfile.getThumbnailheadIcon().equals(currentuserProfile.getThumbnailheadIcon())){
			currentuserProfile.setThumbnailheadIcon(userProfile.getThumbnailheadIcon());
		}
		if(!StringUtils.isEmpty(userProfile.getPhone())&&!userProfile.getPhone().equals(currentuserProfile.getPhone())){
			currentuserProfile.setPhone(userProfile.getPhone());
		}
		if(!StringUtils.isEmpty(userProfile.getWeixin())&&!userProfile.getWeixin().equals(currentuserProfile.getWeixin())){
			currentuserProfile.setWeixin(userProfile.getWeixin());
		}
		
		if(!StringUtils.isEmpty(userProfile.getEmail())&&!userProfile.getEmail().equals(currentuserProfile.getEmail())){
			currentuserProfile.setEmail(userProfile.getEmail());
		}
		if(!StringUtils.isEmpty(userProfile.getQq())&&!userProfile.getQq().equals(currentuserProfile.getQq())){
			currentuserProfile.setQq(userProfile.getQq());
		}
		if(!StringUtils.isEmpty(userProfile.getSummry())&&!userProfile.getSummry().equals(currentuserProfile.getSummry())){
			currentuserProfile.setSummry(userProfile.getSummry());
		}
		if(!StringUtils.isEmpty(userProfile.getTag())&&!userProfile.getTag().equals(currentuserProfile.getTag())){
			currentuserProfile.setTag(userProfile.getTag());
		}
		if(!StringUtils.isEmpty(userProfile.getOthers())&&!userProfile.getOthers().equals(currentuserProfile.getOthers())){
			currentuserProfile.setOthers(userProfile.getOthers());
		}
		if(!StringUtils.isEmpty(userProfile.getBirthDay())&&!userProfile.getBirthDay().equals(currentuserProfile.getBirthDay())){
			currentuserProfile.setBirthDay(userProfile.getBirthDay());
		}
		if(!StringUtils.isEmpty(userProfile.getHomeTown())&&!userProfile.getHomeTown().equals(currentuserProfile.getHomeTown())){
			currentuserProfile.setHomeTown(userProfile.getHomeTown());
		}
	}
	
	public Map<String,Integer> getCurrentUserStatus(){
		Map<String,Integer> userStatus = new HashMap<String,Integer>();
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		UserProfile userProfile = currentUser.getUserProfile();
		if(StringUtils.isEmpty(userProfile.getUserName())){
			userStatus.put("userProfile", 0);
		}else{
			userStatus.put("userProfile", 1);
		}
		List<UserFriend> friendList = userFriendService.getAllFriendsOfUser();
		if(friendList==null||friendList.size()==0){
			userStatus.put("userFriend", 0);
		}else{
			userStatus.put("userFriend", 1);
		}
		return userStatus;
	}
	
	/*public void saveUser(User user,boolean isMD5){
		logger.info("UserService - saveUser: user:\n" + user);
		logger.info("UserService - saveUser: isMD5:" + isMD5);
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		userDao.save(user);
		logger.info("UserService - saveUser done");
	}*/
	
	public User getUserByUserid(String userid){
		logger.info("UserService - getUserByUserid: userid="+userid);
		String md5key = redisService.getMD5CacheKey(CacheType.userInfo, userid);
		logger.info("UserService - getUserByUserid: md5key="+md5key);
		User user = redisService.getObject(md5key, User.class);
		if(user == null){
			user = userDao.findByUserid(userid);
			if(user != null){
				redisService.setObjct(md5key, user);
			}else{
				redisService.setObjct(md5key, user, GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return user;
	}
	
	public User searchOneUserByPhoneOrId(String PhoneId){
		logger.info("UserService - searchOneUserByPhoneOrId: PhoneId="+PhoneId);
		User user = userDao.findOneByPhoneOrId(PhoneId);
		return user;
	}
	
	public User getUserByToken(String auth){
		logger.info("UserService - getUserByToken: token="+auth);
		String md5key = redisService.getMD5CacheKey(CacheType.tokenKey, auth);
        User currentUser = redisService.getObject(md5key, User.class);
        return currentUser;
	}
	
	public UserStatus checkSaveUser(User user,String sessionId){
		String userId = user.getUserid();
		String password = user.getPassword();
		String verifyCode = user.getIdentifyCode();
		if(StringUtils.isEmpty(userId)){
			return UserStatus.userIdEmpty;
		}
		if(StringUtils.isEmpty(password)){
			return UserStatus.passwordEmpty;
		}
		if(StringUtils.isEmpty(verifyCode)){
			return UserStatus.identityEmpty;
		}
		if(!"123456".equals(verifyCode)){
           String cachedCode = redisService.get(redisService.getMD5CacheKey(CacheType.verfifyCode, sessionId, userId));
           if(StringUtils.isEmpty(cachedCode)){
			   return UserStatus.identityExpired;
		   }
           if(!verifyCode.equalsIgnoreCase(cachedCode)){
        	   return UserStatus.identityWrong;
           }
		}
        User existUser = getUserByUserid(userId);
        if(existUser != null){
        	return UserStatus.userIdExist;
        }
		return UserStatus.OK;
	}
	
	public UserStatus checkLoginUser(User user/*,String sessionId*/){
		String userId = user.getUserid();
		String password = user.getPassword();
		//String verifyCode = user.getIdentifyCode();
		if(StringUtils.isEmpty(userId)){
			return UserStatus.userIdEmpty;
		}
		if(StringUtils.isEmpty(password)){
			return UserStatus.passwordEmpty;
		}
		/*if(StringUtils.isEmpty(verifyCode)){
			return UserStatus.identityEmpty;
		}
		if(!"123456".equals(verifyCode)){
            String cachedCode = redisService.get(redisService.getMD5CacheKey(CacheType.verfifyCode, sessionId));
            if(StringUtils.isEmpty(cachedCode)){
			   return UserStatus.identityExpired;
		    }
            if(!verifyCode.equalsIgnoreCase(cachedCode)){
        	   return UserStatus.identityWrong;
            }
		}*/
        User existUser = getUserByUserid(userId);
        if(existUser == null){
        	return UserStatus.userIdNotExist;
        }
        String md5Password = DigestUtils.md5Hex(password);
        if(!md5Password.equals(existUser.getPassword())){
        	return UserStatus.passwordWrong;
        }
		return UserStatus.OK;
	}
	
	public Map<String, List<UserSearchInfo>> searchUserMapInfoByPhoneNums(List<String> phoneNums){
		Map<String, List<UserSearchInfo>> userMapInfo = new HashMap<String,List<UserSearchInfo>>();
		List<UserSearchInfo> group_nonUser = new ArrayList<UserSearchInfo>();
		List<UserSearchInfo> group_User = new ArrayList<UserSearchInfo>();
		List<UserSearchInfo> group_friend = new ArrayList<UserSearchInfo>();
		phoneNums.parallelStream().filter(s->StringUtil.isMobileNO(s)).forEach(s->{
			User user = getUserByUserid(s);
			if(user!=null){
				UserFriend userFriend = userFriendService.getUserFriendByFriendId(user.getUserid());
				if(userFriend!=null){
					group_friend.add(new UserSearchInfo(user,userFriend));
				}else{
					group_User.add(new UserSearchInfo(user,null));
				}
			}else{
				UserSearchInfo dummyUser = new UserSearchInfo();
				dummyUser.setUserid(s);
				group_nonUser.add(dummyUser);
			}
		});
		userMapInfo.put("NOTUSER", group_nonUser);
		userMapInfo.put("USER", group_User);
		userMapInfo.put("FRIEND", group_friend);
		return userMapInfo;
	}
	
	public Map<String, List<UserSearchInfo>> searchUserMapInfoBySearchModel(List<UserSearchLog> userSearchList){
		Map<String, List<UserSearchInfo>> userMapInfo = new HashMap<String,List<UserSearchInfo>>();
		List<UserSearchInfo> group_nonUser = new ArrayList<UserSearchInfo>();
		List<UserSearchInfo> group_User = new ArrayList<UserSearchInfo>();
		List<UserSearchInfo> group_friend = new ArrayList<UserSearchInfo>();
		userSearchList.stream().map(s->{s.setUserId(s.getUserId().replaceAll(" ", "").replaceAll("-", ""));return s;}).filter(s->StringUtil.isMobileNO(s.getUserId())).distinct().forEach(s->{
			recordUserSearchLog(s);
			User user = getUserByUserid(s.getUserId());
			if(user!=null){
				UserFriend userFriend = userFriendService.getUserFriendByFriendId(user.getUserid());
				if(userFriend!=null){
					group_friend.add(new UserSearchInfo(user,userFriend));
				}else{
					group_User.add(new UserSearchInfo(user,null));
				}
			}else{
				group_nonUser.add(new UserSearchInfo(s));
			}
		});
		/*group_nonUser.sort((s1,s2)->PinYinUtil.getPinYin(s1.getNickName()).compareTo(PinYinUtil.getPinYin(s2.getNickName())));
		group_User.sort((s1,s2)->PinYinUtil.getPinYin(s1.getNickName()).compareTo(PinYinUtil.getPinYin(s2.getNickName())));
		group_friend.sort((s1,s2)->PinYinUtil.getPinYin(s1.getNickName()).compareTo(PinYinUtil.getPinYin(s2.getNickName())));*/
		logger.info("UserService searchUserMapInfoBySearchModel group_User size is:" + group_User.size());
		
		group_nonUser.sort((s1,s2)->s1.getUserNamePinYin().compareTo(s2.getUserNamePinYin()));
		group_User.sort((s1,s2)->s1.getUserNamePinYin().compareTo(s2.getUserNamePinYin()));
		group_friend.sort((s1,s2)->s1.getNickNamePinYin().compareTo(s2.getNickNamePinYin()));
		userMapInfo.put("NOTUSER", group_nonUser);
		userMapInfo.put("USER", group_User);
		userMapInfo.put("FRIEND", group_friend);
		return userMapInfo;
	}
	
	private void recordUserSearchLog(UserSearchLog userSearch){
		//User currentUser = UserUtil.getCurrentLoginUserInfo();
		//userSearch.setOwnId(currentUser.getUserid());
		userSearchLogHandler.addToQueue(userSearch);
	}
	
	public List<UserSearchInfo> searchUserListInfoByPhoneNums(List<String> phoneNums){
		List<UserSearchInfo> userInfo = new ArrayList<UserSearchInfo>();
		phoneNums.parallelStream().forEach(s->{
			User user = getUserByUserid(s);
			if(user!=null){
				UserFriend userFriend = userFriendService.getUserFriendByFriendId(user.getUserid());
				if(userFriend!=null){
					userInfo.add(new UserSearchInfo(user,userFriend));
				}else{
					userInfo.add(new UserSearchInfo(user,null));
				}
			}/*else{
				UserSearchInfo dummyUser = new UserSearchInfo();
				dummyUser.setUserid(s);
				userInfo.add(dummyUser);
			}*/
		});
		return userInfo;
	}
	
	public List<UserSearchInfo> searchUserListInfoByPhoneId(List<String> PhoneIds){
		List<UserSearchInfo> userInfo = new ArrayList<UserSearchInfo>();
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		PhoneIds.parallelStream().filter(s->!s.trim().equals(currentUser.getId()+"")).filter(s->!s.trim().equals(currentUser.getUserid())).forEach(s->{
			User user = searchOneUserByPhoneOrId(s);
			if(user!=null){
				UserFriend userFriend = userFriendService.getUserFriendByFriendId(user.getUserid());
				if(userFriend!=null){
					userInfo.add(new UserSearchInfo(user,userFriend));
				}else{
					userInfo.add(new UserSearchInfo(user,null));
				}
			}
		});
		return userInfo;
	}
	
	public boolean deleteUser(String userId) throws NotFoundException{
		User deleteUser = getUserByUserid(userId);
		if(deleteUser==null){
			throw new NotFoundException(404, "user is not found");
		}
		userDao.delete(deleteUser.getId());
		clearUserCache(userId);
		return true;
	}
	
	public boolean getIdentifyCode(String sessionId,String userId){
		logger.info("VerifyCodeServlet - getIdentifyCode sessionId:" + sessionId);
		logger.info("VerifyCodeServlet - getIdentifyCode userId:" + userId);
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4,GlobalConstants.SMS_VERIFY_SOURCE_CODE); 
	    logger.info("UserService - getIdentifyCode VerifyCode:" + verifyCode);
	    String md5key=redisService.getMD5CacheKey(CacheType.verfifyCode, sessionId, userId);
	    redisService.set(md5key, verifyCode, GlobalConstants.SMS_VERIFY_CODE_TIMEOUT);
	    return SMSService.sendSMSVerifyCode(verifyCode, userId);
	}
	
	public boolean getIdentifyCode(String sessionId,String userId,Integer messageType){
		logger.info("VerifyCodeServlet - getIdentifyCode sessionId:" + sessionId);
		logger.info("VerifyCodeServlet - getIdentifyCode userId:" + userId);
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4,GlobalConstants.SMS_VERIFY_SOURCE_CODE); 
	    logger.info("UserService - getIdentifyCode VerifyCode:" + verifyCode);
	    String md5key=redisService.getMD5CacheKey(CacheType.verfifyCode, sessionId, userId);
	    redisService.set(md5key, verifyCode, GlobalConstants.SMS_VERIFY_CODE_TIMEOUT);
	    return SMSService.sendSMSVerifyCode(verifyCode, userId, messageType);
	}
	
	public void clearUserCache(String userId){
		String md5key = redisService.getMD5CacheKey(CacheType.userInfo, userId);
		redisService.remove(md5key);
	}
	
	public FileUpload uploadHead(String userId, MultipartFile headIconFile) throws CommonException{
		List<FileUpload> uploadImgFile = null;
		try {
			if((headIconFile.getContentType().toLowerCase().equals("image/jpg")   
                    || headIconFile.getContentType().toLowerCase().equals("image/jpeg")   
                    || headIconFile.getContentType().toLowerCase().equals("image/png"))){
				 uploadImgFile = fileUploadService.uploadImgFile(userId, FileType.UserheadIcon, headIconFile);
			}else{
				 throw new CommonException(101102, "head img type error");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommonException(101103, "head img IO EXception");
		}
		return uploadImgFile!=null?uploadImgFile.get(0):null;
	}

	@Transactional
	public UserStatus resetUserPassword(String userId,String newPwd,String identifyCode,String sessionId){
		logger.info("UserService - resetUserPassword userId:"+userId);
		logger.info("UserService - resetUserPassword newPwd:"+newPwd);
		logger.info("UserService - resetUserPassword identifyCode:"+identifyCode);
		User currentUser = this.getUserByUserid(userId);
		if(currentUser==null){
			return UserStatus.userIdNotExist;
		}
		if(StringUtils.isEmpty(newPwd)){
			return UserStatus.passwordEmpty;
		}
		if(StringUtils.isEmpty(identifyCode)){
		    return UserStatus.identityEmpty;
	    }
	    if(!"123456".equals(identifyCode)){
          String cachedCode = redisService.get(redisService.getMD5CacheKey(CacheType.verfifyCode, sessionId, userId));
          if(StringUtils.isEmpty(cachedCode)){
		   return UserStatus.identityExpired;
	      }
        if(!identifyCode.equalsIgnoreCase(cachedCode)){
    	   return UserStatus.identityWrong;
          }
	    }
	    userDao.updateUserPassword(userId, DigestUtils.md5Hex(newPwd)); 
	    clearUserCache(userId);
		return UserStatus.OK;
	}
	
	public boolean giveLaudForUser(String userId) {
		 logger.info("UserService - giveLaudForUser for userId:" + userId);
		 User currentUser = UserUtil.getCurrentLoginUserInfo();
		 String md5key=redisService.getMD5CacheKey(CacheType.userLaud, userId);
		 logger.info("UserService - giveLaudForUser md5key:" + md5key);
		 String currentUserId = currentUser.getUserid();
		 Long result = redisService.sadd(md5key, currentUserId);
		 logger.info("UserService - giveLaudForUser result:" + result);
		 if(result==0){
			 return false;
		 }else{
			 return true;
		 }
	}
	
	public Map<String, Integer> getLaudStatusForUser(String userId) {
		 Map<String, Integer> status = new HashMap<String,Integer>();
		 logger.info("UserService - getLaudStatusForUser for userId:" + userId);
		 User currentUser = UserUtil.getCurrentLoginUserInfo();
		 String md5key=redisService.getMD5CacheKey(CacheType.userLaud, userId);
		 logger.info("UserService - getLaudStatusForUser md5key:" + md5key);
		 String currentUserId = currentUser.getUserid();
		 Boolean result = redisService.setContain(md5key, currentUserId);
		 logger.info("UserService - getLaudStatusForUser result:" + result);
		 if(result){
			 status.put("laudStatus", 1);
		 }else{
			 status.put("laudStatus", 0);
		 }
		 return status;
	}
	
	public boolean removeLaudForUser(String userId) {
		 logger.info("UserService - removeLaudForUser for userId:" + userId);
		 User currentUser = UserUtil.getCurrentLoginUserInfo();
		 String md5key=redisService.getMD5CacheKey(CacheType.userLaud, userId);
		 logger.info("UserService - removeLaudForUser md5key:" + md5key);
		 String currentUserId = currentUser.getUserid();
		 Long result = redisService.sRem(md5key, currentUserId);
		 logger.info("UserService - removeLaudForUser result:" + result);
		 if(result==0){
			 return false;
		 }else{
			 return true;
		 }
	}

	public Map<String, Long> getLaudForUser(String userId) {
		logger.info("UserService - getLaudForUser for userId:" + userId);
		Map<String, Long> result = new HashMap<String,Long>();
		String md5key=redisService.getMD5CacheKey(CacheType.userLaud, userId);
		logger.info("UserService - getLaudForUser md5key:" + md5key);
		//result.put(userId, redisService.setCount(md5key));
		result.put("laudNum", redisService.setCount(md5key));
		return result;
	}
}
