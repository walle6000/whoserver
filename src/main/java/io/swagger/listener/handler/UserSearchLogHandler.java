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
import io.swagger.model.UserSearchLog;
import io.swagger.service.UserSearchLogService;
import io.swagger.service.common.NConsumerBatchService;
import io.swagger.service.common.RedisService;

@Component
public class UserSearchLogHandler extends NConsumerBatchService<UserSearchLog> {
	
	private Logger logger = LoggerFactory.getLogger(UserSearchLogHandler.class);
	
	@Autowired
	private UserSearchLogService userSearchLogService;

	@PostConstruct 
	@Override
	public void init() {
		super.init();
		logger.info("UserSearchLogHandler is started ...");
	}
	
	@Override
	public void addToQueue(UserSearchLog  tm) {
		super.addToQueue(tm);
	}
	
	@Override
	protected WorkerThread<UserSearchLog> getHandler(List<UserSearchLog> tList) {
		return () -> {
			return () -> {
				userSearchLogService.saveOrUpdateUserSearchLog(tList);
			};
		};
	}

}
