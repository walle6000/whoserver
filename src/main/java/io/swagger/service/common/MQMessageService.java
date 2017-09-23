package io.swagger.service.common;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import io.swagger.model.TopicMessage;
import io.swagger.model.UserFriendRequest;
import io.swagger.utils.JSONUtil;


@Service
public class MQMessageService {
	
	private Logger logger = LoggerFactory.getLogger(MQMessageService.class);

	@Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
   
    @Resource(name="topicMessage")
    private Queue topic_message_queue;
    
    @Resource(name="friendRequest")
    private Queue friend_request_queue;
    
    @Resource(name="friendRequestResult")
    private Queue friend_request_result_queue;
    
    public void sendTopicMessageToQueue(TopicMessage tm){
    	logger.info("MessageService - sendTopicMessageToQueue:" + tm);
    	this.jmsMessagingTemplate.convertAndSend(this.topic_message_queue, JSONUtil.toJson(tm));
    }
    
    public void sendFriendRequestMessageToQueue(UserFriendRequest userFriendRequest){
    	logger.info("MessageService - sendFriendRequestMessageToQueue:" + userFriendRequest);
    	this.jmsMessagingTemplate.convertAndSend(this.friend_request_queue, JSONUtil.toJson(userFriendRequest));
    }
    
    public void sendFriendRequestResultMessageToQueue(UserFriendRequest userFriendRequest){
    	logger.info("MessageService - sendFriendRequestResultMessageToQueue:" + userFriendRequest);
    	this.jmsMessagingTemplate.convertAndSend(this.friend_request_result_queue, JSONUtil.toJson(userFriendRequest));
    }
}
