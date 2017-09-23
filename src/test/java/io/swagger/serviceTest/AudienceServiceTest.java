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
import io.swagger.service.common.AudienceService;
import io.swagger.service.common.RedisService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Swagger2SpringBoot.class)
@WebAppConfiguration
public class AudienceServiceTest {

	@Autowired
	AudienceService audienceService;
	
	@Test
	public void testAudienceService() {
	  
		assertNotNull(audienceService);
		
		assertEquals("098f6bcd4621d373cade4e832627b4f6", audienceService.getClientId());
	}
	
}
