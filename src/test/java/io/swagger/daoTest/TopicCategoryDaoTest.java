package io.swagger.daoTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.Swagger2SpringBoot;
import io.swagger.dao.TopicCategoryDao;
import io.swagger.dao.UserDao;
import io.swagger.model.TopicCategory;
import io.swagger.model.User;
import io.swagger.model.UserProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Swagger2SpringBoot.class)
@WebAppConfiguration
public class TopicCategoryDaoTest {

	@Autowired
	TopicCategoryDao topicCategoryDao;
	
	@Test
	public void testSaveUser() {
	   /*TopicCategory mockUser1 = new TopicCategory();
	   mockUser1.setName("八卦娱乐");
	   mockUser1.setPeriod(7);
	   mockUser1.setRatio(50);
	   mockUser1.setRightNum(3);
	   mockUser1.setStatus(0);
	   mockUser1.setTotalRewardNum(10);
	   mockUser1.setUserId("commonUser");
	   mockUser1.setCategoryType(0);
	   mockUser1.setDiscussType(0);
	   topicCategoryDao.saveAndFlush(mockUser1);*/
	   
		TopicCategory mockUser1 = new TopicCategory();
		   mockUser1.setName("精准目标");
		   mockUser1.setPeriod(5);
		   mockUser1.setRatio(30);
		   mockUser1.setRightNum(3);
		   mockUser1.setStatus(0);
		   mockUser1.setTotalRewardNum(9);
		   mockUser1.setUserId("commonUser");
		   mockUser1.setCategoryType(1);
		   mockUser1.setDiscussType(1);
		   topicCategoryDao.saveAndFlush(mockUser1);
		   
		   TopicCategory mockUser2 = new TopicCategory();
		   mockUser2.setName("广而告之");
		   mockUser2.setPeriod(5);
		   mockUser2.setRatio(90);
		   mockUser2.setRightNum(5);
		   mockUser2.setStatus(0);
		   mockUser2.setTotalRewardNum(20);
		   mockUser2.setUserId("commonUser");
		   mockUser2.setCategoryType(2);
		   mockUser2.setDiscussType(2);
		   topicCategoryDao.saveAndFlush(mockUser2);
		   
		   TopicCategory mockUser3 = new TopicCategory();
		   mockUser3.setName("付费咨询");
		   mockUser3.setPeriod(3);
		   mockUser3.setRatio(30);
		   mockUser3.setRightNum(6);
		   mockUser3.setStatus(0);
		   mockUser3.setTotalRewardNum(5);
		   mockUser3.setUserId("commonUser");
		   mockUser3.setCategoryType(3);
		   mockUser3.setDiscussType(1);
		   topicCategoryDao.saveAndFlush(mockUser3);
		   
		   TopicCategory mockUser4 = new TopicCategory();
		   mockUser4.setName("自定义");
		   mockUser4.setPeriod(10);
		   mockUser4.setRatio(50);
		   mockUser4.setRightNum(3);
		   mockUser4.setStatus(0);
		   mockUser4.setTotalRewardNum(10);
		   mockUser4.setUserId("commonUser");
		   mockUser4.setCategoryType(4);
		   mockUser4.setDiscussType(0);
		   topicCategoryDao.saveAndFlush(mockUser4);
	}
	
	
	
}
