package io.swagger.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import io.swagger.common.MessageQueue;
import io.swagger.listener.handler.TopicMessageHandler;
import io.swagger.listener.handler.UserFriendRequestHandler;
import io.swagger.listener.handler.UserFriendRequestResultHandler;
import io.swagger.model.TopicMessage;
import io.swagger.model.UserFriendRequest;
import io.swagger.service.TopicMessageService;
import io.swagger.service.UserFriendService;
import io.swagger.utils.JSONUtil;

@Component
public class MQMessageListener {
    
	private Logger logger = LoggerFactory.getLogger(MQMessageListener.class);
	
	@Autowired
	private TopicMessageHandler topicMessageHandler;
	
	@Autowired
	private UserFriendRequestHandler userFriendRequestHandler;
	
	@Autowired
	private UserFriendRequestResultHandler userFriendRequestResultHandler;
	
    @JmsListener(destination=MessageQueue.TOPIC_MESSAGE_QUEUE)
    public void consumerTopicQueueMessage(String msg){
    	logger.info("MessageListener - consumerTopicQueueMessage queueName:" + MessageQueue.TOPIC_MESSAGE_QUEUE);
    	logger.info("MessageListener - consumerTopicQueueMessage received msg:" + msg + " from " + MessageQueue.TOPIC_MESSAGE_QUEUE);
    	TopicMessage tm = JSONUtil.toBean(msg, TopicMessage.class);
    	logger.info("MessageListener - consumerTopicQueueMessage received TopicMessage:\n" + tm);
    	topicMessageHandler.addToQueue(tm);
    }  
    
    @JmsListener(destination=MessageQueue.FRIEND_REQUEST_QUEUE)
    public void consumerFriendRequestQueueMessage(String msg){
    	logger.info("MessageListener - consumerFriendRequestQueueMessage queueName:" + MessageQueue.FRIEND_REQUEST_QUEUE);
    	logger.info("MessageListener - consumerFriendRequestQueueMessage received msg:" + msg + " from " + MessageQueue.FRIEND_REQUEST_QUEUE);
    	UserFriendRequest ufr = JSONUtil.toBean(msg, UserFriendRequest.class);
    	logger.info("MessageListener - consumerFriendRequestQueueMessage received UserFriendRequest:\n" + ufr);
    	userFriendRequestHandler.addToQueue(ufr);
    }
    
    @JmsListener(destination=MessageQueue.FRIEND_REQUEST_RESULT_QUEUE)
    public void consumerFriendRequestResultQueueMessage(String msg){
    	logger.info("MessageListener - consumerFriendRequestResultQueueMessage queueName:" + MessageQueue.FRIEND_REQUEST_QUEUE);
    	logger.info("MessageListener - consumerFriendRequestResultQueueMessage received msg:" + msg + " from " + MessageQueue.FRIEND_REQUEST_QUEUE);
    	UserFriendRequest ufr = JSONUtil.toBean(msg, UserFriendRequest.class);
    	logger.info("MessageListener - consumerFriendRequestResultQueueMessage received UserFriendRequest:\n" + ufr);
    	userFriendRequestResultHandler.addToQueue(ufr);
    }
}
