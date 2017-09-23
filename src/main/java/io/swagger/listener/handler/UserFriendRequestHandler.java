package io.swagger.listener.handler;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swagger.common.WorkerThread;
import io.swagger.dao.UserFriendRequestDao;
import io.swagger.model.UserFriendRequest;
import io.swagger.service.UserFriendRequestService;
import io.swagger.service.common.NConsumerBatchService;

@Component
public class UserFriendRequestHandler extends NConsumerBatchService<UserFriendRequest>{
	
	private Logger logger = LoggerFactory.getLogger(UserFriendRequestHandler.class);
	
	@Autowired
	private UserFriendRequestDao userFriendRequestDao;
	
	@Autowired
	private UserFriendRequestService userFriendRequestService;
	
	@PostConstruct 
	@Override
	public void init() {
		super.init();
		logger.info("UserFriendRequestHandler is started ...");
	}
	
	@Override
	public void addToQueue(UserFriendRequest  ufr) {
		super.addToQueue(ufr);
	}

	@Override
	protected WorkerThread<UserFriendRequest> getHandler(List<UserFriendRequest> tList) {
		return () -> {
			return () -> {
				tList.parallelStream().forEach(s -> handleReceivedMessage(s));
			};
		};
	}
	
	private boolean handleReceivedMessage(UserFriendRequest ufr){
		ufr.setCreateDate(new Date().getTime());
		userFriendRequestDao.save(ufr);
		userFriendRequestService.clearUserCache(ufr);
		/*消息推送待定*/
		return true;
	}
}
