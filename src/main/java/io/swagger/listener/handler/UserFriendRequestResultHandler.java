package io.swagger.listener.handler;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swagger.common.CacheType;
import io.swagger.common.WorkerThread;
import io.swagger.model.UserFriendRequest;
import io.swagger.service.UserFriendService;
import io.swagger.service.common.NConsumerBatchService;
import io.swagger.service.common.RedisService;

@Component
public class UserFriendRequestResultHandler extends NConsumerBatchService<UserFriendRequest>{
	
	private Logger logger = LoggerFactory.getLogger(UserFriendRequestResultHandler.class);
	
	@Autowired
	//private UserFriendDao userFriendDao;
	private UserFriendService userFriendService;
	
	@Autowired
	private RedisService redisService;
	
	@PostConstruct 
	@Override
	public void init() {
		super.init();
		logger.info("UserFriendRequestResultHandler is started ...");
	}
	
	@Override
	public void addToQueue(UserFriendRequest  ufr) {
		super.addToQueue(ufr);
	}

	@Override
	protected WorkerThread<UserFriendRequest> getHandler(List<UserFriendRequest> tList) {
		return () -> {
			return () -> {
				tList.stream().forEach(s -> handleReceivedMessage(s));
			};
		};
	}
	
	private boolean handleReceivedMessage(UserFriendRequest ufr){
		userFriendService.updateUserFriendStatusById(ufr.getRelativeId(), ufr.getStatus());
		clearUserCache(ufr.getRequesterId());
		/*消息推送待定*/
		return true;
	}
	
	public void clearUserCache(String userId){
		logger.info("UserFriendRequestResultHandler - clearUserCache userId:" + userId);
		String md5key_friendList = redisService.getMD5CacheKey(CacheType.friendList, userId);
		String md5key_sentfriendList = redisService.getMD5CacheKey(CacheType.mySentfriendRequestList, userId);
		redisService.remove(md5key_friendList);
		redisService.remove(md5key_sentfriendList);
	}
}
