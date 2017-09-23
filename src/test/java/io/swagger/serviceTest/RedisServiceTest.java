package io.swagger.serviceTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.Swagger2SpringBoot;
import io.swagger.dao.UserDao;
import io.swagger.model.User;
import io.swagger.model.UserProfile;
import io.swagger.service.common.RedisService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Swagger2SpringBoot.class)
@WebAppConfiguration
public class RedisServiceTest {

	@Autowired
	RedisService redisService;
	
	@Test
	public void testSaveUser() {
	   User mockUser = new User();
	   mockUser.setUserid("13210900079");
	   mockUser.setPassword("user12");
	   mockUser.setUserStatus(1);
	   
	   UserProfile userProfile = new UserProfile();
	   userProfile.userName("allen");
	   userProfile.setEmail("allen@email.com");
	   userProfile.setHeadIcon("http://localhost:8080/img/12343123");
	   userProfile.setPhone("13210900079");
	   userProfile.setQq("322323232");
	   userProfile.setSummry("宇宙专家");
	   userProfile.setWeixin("allens");
	   mockUser.setUserProfile(userProfile);
	   
	   redisService.lpush("user1", mockUser);
	}
	
	@Test
	public void testGetUserByKey(){
		String userid = "user1";
		String userStr = redisService.lpop(userid);
		System.out.println("user:" + userStr);
		assertNotNull(userStr);
	}
	
}
