package io.swagger.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.dao.UserDao;
import io.swagger.model.User;

@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	public void saveUser(User user){
		logger.info("UserService - saveUser: user\n" + user);
		userDao.save(user);
		logger.info("UserService - saveUser done");
	}
	
	public User getUserByUserid(String userid){
		logger.info("UserService - getUserByUserid: userid="+userid);
		User user = userDao.findByUserid(userid);
		return user;
	}
}
