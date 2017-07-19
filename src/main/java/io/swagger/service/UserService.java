package io.swagger.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.swagger.common.CacheType;
import io.swagger.common.UserStatus;
import io.swagger.dao.UserDao;
import io.swagger.model.User;

@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RedisService redisService;
	
	public void saveUser(User user){
		logger.info("UserService - saveUser: user\n" + user);
		userDao.save(user);
		logger.info("UserService - saveUser done");
	}
	
	public void saveUser(User user,boolean isMD5){
		logger.info("UserService - saveUser: user:\n" + user);
		logger.info("UserService - saveUser: isMD5:" + isMD5);
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		userDao.save(user);
		logger.info("UserService - saveUser done");
	}
	
	public User getUserByUserid(String userid){
		logger.info("UserService - getUserByUserid: userid="+userid);
		String md5key = redisService.getMD5CacheKey(CacheType.userInfo, userid);
		User user = redisService.getObject(md5key, User.class);
		if(user == null){
			user = userDao.findByUserid(userid);
			if(user != null){
				redisService.setObjct(md5key, user);
			}
		}
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
        String cachedCode = redisService.get(redisService.getMD5CacheKey(CacheType.verfifyCode, sessionId));
        if(StringUtils.isEmpty(cachedCode)){
			return UserStatus.identityExpired;
		}
        if(!verifyCode.equalsIgnoreCase(cachedCode)){
        	return UserStatus.identityWrong;
        }
        User existUser = getUserByUserid(userId);
        if(existUser != null){
        	return UserStatus.userIdExist;
        }
		return UserStatus.OK;
	}
	
	public UserStatus checkLoginUser(User user,String sessionId){
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
        String cachedCode = redisService.get(redisService.getMD5CacheKey(CacheType.verfifyCode, sessionId));
        if(StringUtils.isEmpty(cachedCode)){
			return UserStatus.identityExpired;
		}
        if(!verifyCode.equalsIgnoreCase(cachedCode)){
        	return UserStatus.identityWrong;
        }
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
}
