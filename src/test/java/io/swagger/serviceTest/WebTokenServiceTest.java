package io.swagger.serviceTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.jsonwebtoken.Claims;
import io.swagger.Swagger2SpringBoot;
import io.swagger.dao.UserDao;
import io.swagger.model.AccessToken;
import io.swagger.model.User;
import io.swagger.model.UserProfile;
import io.swagger.service.common.AudienceService;
import io.swagger.service.common.RedisService;
import io.swagger.service.common.WebTokenService;
import io.swagger.utils.JWTUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Swagger2SpringBoot.class)
@WebAppConfiguration
public class WebTokenServiceTest {

	@Autowired
	WebTokenService webTokenService;
	
	@Autowired
	AudienceService audienceService;
	
	@Test
	public void testGetAccessToken() {
	   User mockUser = new User();
	   mockUser.setUserid("13210900079");
	   mockUser.setPassword("user12");
	   mockUser.setUserStatus(1);
	   mockUser.setId(1001L);
	   
	   AccessToken accessToken = webTokenService.getAccessToken(mockUser);
	   assertNotNull(accessToken);
	   System.out.println("accessToken:" + accessToken.getAccess_token());
	}
	
	@Test
	public void testParseJWTAccessToken() {
	   String test_token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiZW5kVXNlciIsImlkIjoiMTAwMSIsInVzZXJpZCI6IjEzMjEwOTAwMDc5IiwiaXNzIjoid2hvU2VydmVyUmVzdEFwaSIsImF1ZCI6IjA5OGY2YmNkNDYyMWQzNzNjYWRlNGU4MzI2MjdiNGY2IiwiZXhwIjoxNTAyNTI0NzUyLCJuYmYiOjE0OTk5MzI3NTJ9.0XSfqjUqYRit3FgUSEvEW5Bdky8DZ8X5lxgI8tcQlMQ";
	   
	   User user = webTokenService.parseAccessToken(test_token);
	   
	   assertNotNull(user);
	   
	}
	
	
}
