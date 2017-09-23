package io.swagger.configuration;

import javax.jms.Queue;

//import javax.jms.Topic;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.common.MessageQueue;

@Configuration
public class ActiveMessageConfig {

	@Bean(name="topicMessage")
    public Queue topicMessageQueue() {
       return new ActiveMQQueue(MessageQueue.TOPIC_MESSAGE_QUEUE);
    }
   
	@Bean(name="friendRequest")
    public Queue friendRequestQueue() {
       return new ActiveMQQueue(MessageQueue.FRIEND_REQUEST_QUEUE);
    }
	
	@Bean(name="friendRequestResult")
	public Queue friendRequestResultQueue(){
		return new ActiveMQQueue(MessageQueue.FRIEND_REQUEST_RESULT_QUEUE);
	}
}
