package io.swagger.daoTest;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.Swagger2SpringBoot;
import io.swagger.api.TopicApi;
import io.swagger.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
classes=Swagger2SpringBoot.class)
@WebAppConfiguration
public class UserDaoTest {

	@Autowired
	UserDao dataSource;
	
	@Test
	public void testService() {
	assertNotNull(dataSource);

	}
	
}
