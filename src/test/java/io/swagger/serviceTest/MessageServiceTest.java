package io.swagger.serviceTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.Swagger2SpringBoot;
import io.swagger.model.TopicMessage;
import io.swagger.service.common.MQMessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Swagger2SpringBoot.class)
@WebAppConfiguration
public class MessageServiceTest {

	@Autowired
	MQMessageService messageService;
	
	@Test
	public void testTopicMessageSend() {
	  
		assertNotNull(messageService);
		
		TopicMessage tm = new TopicMessage();
		tm.setContent("Unit testing topic message");
		tm.setTopicId(101L);
		tm.setSender(1321023112l);
		
		messageService.sendTopicMessageToQueue(tm);
		
	}
	
}
