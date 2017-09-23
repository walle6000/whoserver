package io.swagger.daoTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.Swagger2SpringBoot;
import io.swagger.dao.TopicAttitudeDao;
import io.swagger.dao.TopicCategoryDao;
import io.swagger.dao.UserDao;
import io.swagger.model.TopicAttitude;
import io.swagger.model.TopicCategory;
import io.swagger.model.User;
import io.swagger.model.UserProfile;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes=Swagger2SpringBoot.class)
//@WebAppConfiguration
public class TopicAttitudeDaoTest {

	//@Autowired
	//TopicAttitudeDao topicAttitudeDao;
	
	//@Test
	/*public void testSaveUser() {
	   
		   TopicAttitude mockUser1 = new TopicAttitude();
		   mockUser1.setName("包在我身上");
		   mockUser1.setStatus(0);
		   topicAttitudeDao.saveAndFlush(mockUser1);
		   
		   TopicAttitude mockUser2 = new TopicAttitude();
		   mockUser2.setName("基本可以");
		   mockUser2.setStatus(1);
		   topicAttitudeDao.saveAndFlush(mockUser2);
		   
		   TopicAttitude mockUser3 = new TopicAttitude();
		   mockUser3.setName("试一试");
		   mockUser3.setStatus(2);
		   topicAttitudeDao.saveAndFlush(mockUser3);
		   
		   TopicAttitude mockUser4 = new TopicAttitude();
		   mockUser4.setName("考虑一下");
		   mockUser4.setStatus(3);
		   topicAttitudeDao.saveAndFlush(mockUser4);
		   
		   TopicAttitude mockUser5 = new TopicAttitude();
		   mockUser5.setName("把握不大");
		   mockUser5.setStatus(4);
		   topicAttitudeDao.saveAndFlush(mockUser5);
		   
		   TopicAttitude mockUser6 = new TopicAttitude();
		   mockUser6.setName("无能为力");
		   mockUser6.setStatus(5);
		   topicAttitudeDao.saveAndFlush(mockUser6);
	}*/
	
	@Test
	public void testLittleCase() {
		   String status = "1-0-2-3-9";
		   status = status==null?"0":status;
		   status = status.lastIndexOf("-")!=-1?status.substring(status.lastIndexOf("-")+1):status;
		   System.out.println(status);
	}
	
}
