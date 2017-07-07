package io.swagger.daoTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.Swagger2SpringBoot;
import io.swagger.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Swagger2SpringBoot.class)
@WebAppConfiguration
public class UserDaoTest {

	@Autowired
	UserDao dataSource;
	
	@Test
	public void testService() {
	assertNotNull(dataSource);

	}
	
}
