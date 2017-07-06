package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.dao.UserDao;
import io.swagger.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void saveUser(User user){
		System.out.println("hyg -- userDao:" + userDao);
	}
	
	public User getUserByUserid(String userid){
		System.out.println("hyg -- userDao:" + userDao);
		return null;
	}
}
