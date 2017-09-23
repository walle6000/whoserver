package io.swagger.listener.handler;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swagger.common.CacheType;
import io.swagger.common.GlobalConstants;
import io.swagger.common.WorkerThread;
import io.swagger.model.TopicMessage;
import io.swagger.service.common.NConsumerBatchService;
import io.swagger.service.common.RedisService;

@Component
public class TopicMessageHandler extends NConsumerBatchService<TopicMessage> {
	
	private Logger logger = LoggerFactory.getLogger(TopicMessageHandler.class);
	
	@Autowired
	private RedisService redisService;

	@PostConstruct 
	@Override
	public void init() {
		super.init();
		logger.info("TopicMessageHandler is started ...");
	}
	
	@Override
	public void addToQueue(TopicMessage  tm) {
		super.addToQueue(tm);
	}
	
	@Override
	protected WorkerThread<TopicMessage> getHandler(List<TopicMessage> tList) {
		return () -> {
			return () -> {
				tList.parallelStream().forEach(s->handleReceivedMessage(s));
			};
		};
	}
	
	private boolean handleReceivedMessage(TopicMessage tm){
		String md5key = redisService.getMD5CacheKey(CacheType.topicMessage, tm.getTopicId());
		redisService.rpush(md5key, tm);
		if(redisService.listCount(md5key)>GlobalConstants.MAX_MESSAGE_NUM){
			redisService.lpop(md5key);
		}
		return true;
	}

}
