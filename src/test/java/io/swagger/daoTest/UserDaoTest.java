package io.swagger.daoTest;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Swagger2SpringBoot.class)
@WebAppConfiguration
public class UserDaoTest {

	@Autowired
	UserDao userDao;
	
	//@Test
	public void testSaveUser() {
	   User mockUser1 = new User();
	   mockUser1.setUserid("13210908879");
	   mockUser1.setPassword("admin12");
	   mockUser1.setUserStatus(0);
	   userDao.saveAndFlush(mockUser1);
	   
	   User mockUser2 = new User();
	   mockUser2.setUserid("13210900079");
	   mockUser2.setPassword("user12");
	   mockUser2.setUserStatus(1);
	   
	   UserProfile userProfile = new UserProfile();
	   userProfile.userName("allen");
	   userProfile.setEmail("allen@email.com");
	   userProfile.setHeadIcon("http://localhost:8080/img/12343123");
	   userProfile.setPhone("13210900079");
	   userProfile.setQq("322323232");
	   userProfile.setSummry("宇宙专家");
	   userProfile.setWeixin("allens");
	   mockUser2.setUserProfile(userProfile);
	   userDao.save(mockUser2);
	}
	
	@Test
	public void testGetUserByUserid(){
		String userid = "13210900079";
		User mockUser = userDao.findByUserid(userid);
		assertNotNull(mockUser);
		assertEquals("13210900079",mockUser.getUserid());
		assertNotNull(mockUser.getUserProfile());
	}
	
}
