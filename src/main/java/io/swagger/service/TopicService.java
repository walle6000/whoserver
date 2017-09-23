package io.swagger.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.springframework.util.StringUtils;
import io.swagger.annotations.ApiParam;
import io.swagger.common.CacheType;
import io.swagger.common.FileType;
import io.swagger.common.GlobalConstants;
import io.swagger.dao.TopicAttitudeDao;
import io.swagger.dao.TopicCategoryDao;
import io.swagger.dao.TopicDao;
import io.swagger.dao.TopicInviterDao;
import io.swagger.dao.TopicTopologyDao;
import io.swagger.exception.CommonException;
import io.swagger.model.FileUpload;
import io.swagger.model.Topic;
import io.swagger.model.TopicAttitude;
import io.swagger.model.TopicCategory;
import io.swagger.model.TopicDetail;
import io.swagger.model.TopicFund;
import io.swagger.model.TopicInviter;
import io.swagger.model.TopicTopology;
import io.swagger.model.TopicTopologyModel;
import io.swagger.model.User;
import io.swagger.model.UserTopicInfo;
import io.swagger.service.common.FileUploadService;
import io.swagger.service.common.RedisService;
import io.swagger.utils.MD5Util;
import io.swagger.utils.StringUtil;
import io.swagger.utils.UserUtil;


@Service
public class TopicService{
	
	private Logger logger = LoggerFactory.getLogger(TopicService.class);
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private TopicDao topicDao;
	
	@Autowired
	private TopicTopologyDao topicTopologyDao;
	
	@Autowired
	private TopicCategoryDao topicCategoryDao;
	
	@Autowired
	private TopicAttitudeDao topicAttitudeDao;
	
	@Autowired
	private TopicInviterDao topicInviterDao;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public void createNewTopic(String topicTitle,
				               String topicContent,
				               Integer rightNum,
				               Integer totalNum,
				               Integer tierNum,
				               Integer totoalAmount,
				               Integer ratio,
				               Integer totalRewardNum,
				               Integer discussType,
				               Integer period,
				               Integer assignType,
				               Integer categoryType,
				               List<MultipartFile> postImages) throws CommonException {
		logger.info("TopicService - createNewTopic topicTitle:" + topicTitle);
		logger.info("TopicService - createNewTopic topicContent:" + topicContent);
		logger.info("TopicService - createNewTopic rightNum:" + rightNum);
		logger.info("TopicService - createNewTopic tierNum:" + tierNum);
		logger.info("TopicService - createNewTopic totalNum:" + totalNum);
		logger.info("TopicService - createNewTopic totoalAmount:" + totoalAmount);
		logger.info("TopicService - createNewTopic ratio:" + ratio);
		logger.info("TopicService - createNewTopic totalRewardNum:" + totalRewardNum);
		logger.info("TopicService - createNewTopic discussType:" + discussType);
		logger.info("TopicService - createNewTopic period:" + period);
		logger.info("TopicService - createNewTopic assignType:" + assignType);
		logger.info("TopicService - createNewTopic assignType:" + categoryType);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("TopicService - createNewTopic currentUser:\n" + currentUser);
		Topic topic = new Topic();
		topic.setCreateUser(currentUser.getUserid());
		TopicDetail topicDetail = new TopicDetail(topicTitle,topicContent);
		topicDetail.setCreateDate(new Date().getTime());
		topicDetail.setEndDate(topicDetail.getCreateDate()+period*24*60*60*1000);
		topicDetail.setRightNum(rightNum);
		topicDetail.setTierNum(tierNum);
		topicDetail.setTotalNum(totalNum);
		topicDetail.setDiscussType(discussType);
		topicDetail.setCategoryType(categoryType);
		List<FileUpload> uploadImgFile = new ArrayList<FileUpload>();
		List<MultipartFile> uploadImages = new ArrayList<MultipartFile>();
		try {
			for(MultipartFile InfoIconFile : postImages){
				if((InfoIconFile.getContentType().toLowerCase().equals("image/jpg")   
	                    || InfoIconFile.getContentType().toLowerCase().equals("image/jpeg")   
	                    || InfoIconFile.getContentType().toLowerCase().equals("image/png"))){
					uploadImages.add(InfoIconFile);
				}else{
					 continue;
				}
			}
			uploadImgFile = fileUploadService.uploadImgFile(currentUser.getUserid(), FileType.TopicImage, uploadImages.toArray(new MultipartFile[0]));
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommonException(111103, "Explore image IO EXception");
		}
		List<String> uploadImagesList = new ArrayList<String>();
		uploadImgFile.stream().forEach(s->{
			uploadImagesList.add(s.getFileUrl()+";"+s.getThumbnailUrl());
		});
		topicDetail.setImgUrls(StringUtils.collectionToDelimitedString(uploadImagesList, "|"));
		topic.setDetail(topicDetail);
		TopicFund topicFund = new TopicFund(totoalAmount, ratio, assignType);
		topicFund.setTotalRewardNum(totalRewardNum);
		topic.setTopicFund(topicFund);
		topicDao.save(topic);
		clearTopicCahce(currentUser.getUserid());
	}
	
	public void addUserForTopicByCode(String inviteCode, String status, Integer source,String comment)  throws CommonException{
		logger.info("TopicService - addUserForTopicByCode inviteCode:"+inviteCode);
		logger.info("TopicService - addUserForTopicByCode status:"+status);
		logger.info("TopicService - addUserForTopicByCode source:"+source);
		logger.info("TopicService - addUserForTopicByCode comment:"+comment);
		String inviteDecode = MD5Util.decryptBASE64(inviteCode);
		logger.info("TopicService - getTopicByInviteCode inviteDecode:"+inviteDecode);
		JsonObject obj = null;
		try {
			obj = new JsonParser().parse(inviteDecode).getAsJsonObject();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new CommonException(121118, "The invite code is invoid.");
		}
		Long topicId = obj.getAsJsonPrimitive("TopicId").getAsLong();
		logger.info("TopicService - getTopicByInviteCode topicId:"+topicId);
		String sender = obj.getAsJsonPrimitive("Inviter").getAsString();
		logger.info("TopicService - getTopicByInviteCode sender:"+sender);
		addUserForTopic(topicId,sender,status==null?"0":status,source,comment);
	}

	public void addUserForTopic(Long topicId,String sender,String status,Integer source,String comment) throws CommonException{
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		if(currentUser.getUserid().endsWith(sender)){
			throw new CommonException(121112, "You can't join your own inviting.");
		}
		Topic topic = getTopicById(topicId);
		if(topic==null){
			throw new CommonException(121110, "Topic is not exist");
		}
		if(topic.getCreateUser().equals(currentUser.getUserid())){
			throw new CommonException(121111, "You can't join your own topic");
		}
		if(topic.getStatus()==1){
			throw new CommonException(121115, "The topic is closed.");
		}
		TopicDetail topicDetail = topic.getDetail();
		
		List<TopicTopology> joinedUserList = getAllJoinedUserOfTopic(topicId);
		if(topicDetail.getTotalNum()!=null&&joinedUserList.size()>=topicDetail.getTotalNum()){
			throw new CommonException(121117, "The joining number of topic is up to limit.");
		}
		//List<TopicTopology> senderJoinedList = getAllJoinedTopicTopology(sender);
		Long senderjoinNum = joinedUserList.parallelStream().filter(s->s.getReciver().equals(sender)).count();
		if(senderjoinNum==0 && !topic.getCreateUser().equals(sender)){
			throw new CommonException(121116, "Your inviter has not joined the topic.");
		}
		
		//List<TopicTopology>  invitedList = getAllInvitedTopicTopology(sender);
		Long sendNum = joinedUserList.parallelStream().filter(s->s.getSender().equals(sender)).count();
		if(sendNum>=topicDetail.getRightNum()){
			throw new CommonException(121113, "The joining number of inviter is up to limit.");
		}
		
		//List<TopicTopology>  joinedList = getAllJoinedTopicTopology(currentUser.getUserid());
		Long joinNum = joinedUserList.parallelStream().filter(s->s.getReciver().equals(currentUser.getUserid())).count();
		if(joinNum>0){
			throw new CommonException(121114, "You have joined the topic.");
		}
		
		TopicTopology topicTopology = new TopicTopology(topicId,sender,currentUser.getUserid(),status);
		topicTopology.setJoinTime(new Date().getTime());
	    topicTopology.setComment(StringUtils.isEmpty(comment)?"None":comment);
		if(!StringUtils.isEmpty(source)){
			topicTopology.setSource(source);
		}
		topicTopologyDao.save(topicTopology);
		clearTopicTopologyCache(topicId,sender,currentUser.getUserid());
	}
	
	public Map<String,List<UserTopicInfo>> getAllTopics(){
		logger.info("TopicService - getAllTopics...");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("TopicService - getAllTopics currentUser:\n" + currentUser);
		List<UserTopicInfo> allCreatedTopics = getAllCreatedTopic(currentUser.getUserid()).parallelStream().map(s->{return new UserTopicInfo(currentUser,s).topicCategory(getUserTopicCategory(s));})
				.peek(s->getParticipateInfo(s))
				.sorted((s1,s2)->s1.getStatus().compareTo(s2.getStatus())==0?s2.getCreateDate().compareTo(s1.getCreateDate()):s1.getStatus().compareTo(s2.getStatus()))
				.collect(Collectors.toList());
		List<UserTopicInfo> allJoinedTopics = getAllJoinedTopicTopology(currentUser.getUserid()).parallelStream().map(s->{
			     User senderUser = userService.getUserByUserid(s.getSender());
			     Topic topic = getTopicById(s.getTopicId());
			     return new UserTopicInfo(senderUser,topic,s).topicCategory(getUserTopicCategory(topic)).sender(currentUser.getUserid());
			     })
				.peek(s->getParticipateInfo(s))
				.sorted((s1,s2)->s1.getStatus().compareTo(s2.getStatus())==0?s2.getCreateDate().compareTo(s1.getCreateDate()):s1.getStatus().compareTo(s2.getStatus()))
				.collect(Collectors.toList());
		List<UserTopicInfo> allInvitedTopics = getAllInvitedTopic(currentUser.getUserid()).parallelStream().map(s->{
		     User senderUser = userService.getUserByUserid(s.getSender());
		     Topic topic = getTopicById(s.getTopicId());
		     return new UserTopicInfo(senderUser,topic,s).topicCategory(getUserTopicCategory(topic)).sender(senderUser.getUserid()).receiver(currentUser.getUserid());
		     })
			.peek(s->getParticipateInfo(s))
			.sorted((s1,s2)->s1.getStatus().compareTo(s2.getStatus())==0?s2.getCreateDate().compareTo(s1.getCreateDate()):s1.getStatus().compareTo(s2.getStatus()))
			.collect(Collectors.toList());	
		List<UserTopicInfo> allInvitingTopics = getAllInvitingTopic(currentUser.getUserid()).parallelStream().map(s->{
		     User reciverUser = userService.getUserByUserid(s.getReciver());
		     Topic topic = getTopicById(s.getTopicId());
		     return new UserTopicInfo(reciverUser,topic).topicCategory(getUserTopicCategory(topic)).sender(currentUser.getUserid()).receiver(reciverUser.getUserid());
		     })
			.peek(s->getParticipateInfo(s))
			.sorted((s1,s2)->s1.getStatus().compareTo(s2.getStatus())==0?s2.getCreateDate().compareTo(s1.getCreateDate()):s1.getStatus().compareTo(s2.getStatus()))
			.collect(Collectors.toList());	
		Map<String,List<UserTopicInfo>> allTopics = new HashMap<String,List<UserTopicInfo>>();
		allTopics.put("created", allCreatedTopics);
		allTopics.put("invited", allInvitedTopics);
		allTopics.put("inviting", allInvitingTopics);
		allTopics.put("joined", allJoinedTopics);
		return allTopics;
	}
	
	public Map<String,String> getInviteInfoOfTopic(Long topicId) throws CommonException{
		logger.info("TopicService - getInviteInfoOfTopic topicId:"+topicId);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("TopicService - getInviteInfoOfTopic currentUser:\n"+currentUser);
		Topic topic = getTopicById(topicId);
		if(topic==null){
			throw new CommonException(121110, "Topic is not exist");
		}
		
		if(topic.getStatus()==1){
			throw new CommonException(121115, "The topic is closed.");
		}
		
		TopicDetail topicDetail = topic.getDetail();
		
		List<TopicTopology> joinedUserList = getAllJoinedUserOfTopic(topicId);
		if(topicDetail.getTotalNum()!=null&&joinedUserList.size()>=topicDetail.getTotalNum()){
			throw new CommonException(121117, "The joining number of topic is up to limit.");
		}
		Long senderjoinNum = joinedUserList.parallelStream().filter(s->s.getReciver().equals(currentUser.getUserid())).count();
		if(senderjoinNum==0 && !topic.getCreateUser().equals(currentUser.getUserid())){
			throw new CommonException(121116, "You have not joined the topic.");
		}
		
		Long sendNum = joinedUserList.parallelStream().filter(s->s.getSender().equals(currentUser.getUserid())).count();
		if(sendNum>=topicDetail.getRightNum()){
			throw new CommonException(121113, "The number of your inviting is up to limit.");
		}
		
		JsonObject inviteCode = new JsonObject();
		inviteCode.addProperty("TopicId", topicId);
		inviteCode.addProperty("Inviter", currentUser.getUserid());
		logger.info("TopicService - getInviteInfoOfTopic inviteCode:"+inviteCode.toString());
		String inviteCodeEncode = MD5Util.encryptBASE64(inviteCode.toString());
		logger.info("TopicService - getInviteInfoOfTopic inviteCodeEncode:"+inviteCodeEncode);
		Map<String,String> result = new HashMap<String,String>();
		result.put("inviteCode", inviteCodeEncode);
		result.put("topicContent", topic.getDetail().getContent().length()>50?topic.getDetail().getContent().substring(0, 50)+"...":topic.getDetail().getContent());
		if(!StringUtils.isEmpty(topic.getDetail().getImgUrls())){
   		    String[] imgs = topic.getDetail().getImgUrls().split("\\|");
   		    result.put("topicImage", imgs[0]);
   	    }else{
   	    	result.put("topicImage", "");
   	    }
		return result;
	}
	
	public UserTopicInfo getTopicByInviteCode(String inviteCode) throws CommonException{
		logger.info("TopicService - getTopicByInviteCode inviteCode:"+inviteCode);
		String inviteDecode = MD5Util.decryptBASE64(inviteCode);
		logger.info("TopicService - getTopicByInviteCode inviteDecode:"+inviteDecode);
		JsonObject obj = null;
		try {
			obj = new JsonParser().parse(inviteDecode).getAsJsonObject();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new CommonException(121112, "The invite code is invoid.");
		}
		Topic topic = getTopicById(obj.getAsJsonPrimitive("TopicId").getAsLong());
		if(topic==null){
			throw new CommonException(121110, "The topic is not exist.");
		}
		User inviter = userService.getUserByUserid(obj.getAsJsonPrimitive("Inviter").getAsString());
		if(inviter==null){
			throw new CommonException(121113, "The inviter is not exist.");
		}
		UserTopicInfo userTopicInfo = new UserTopicInfo(inviter,topic).topicCategory(getUserTopicCategory(topic));
		getParticipateInfo(userTopicInfo);
		return userTopicInfo;
	}
	
	
	public UserTopicInfo getJoinedTopicByInviteCode(String inviteCode) throws CommonException{
		logger.info("TopicService - getJoinedTopicByInviteCode inviteCode:"+inviteCode);
		String inviteDecode = MD5Util.decryptBASE64(inviteCode);
		logger.info("TopicService - getJoinedTopicByInviteCode inviteDecode:"+inviteDecode);
		JsonObject obj = null;
		try {
			obj = new JsonParser().parse(inviteDecode).getAsJsonObject();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new CommonException(121118, "The invite code is invoid.");
		}
		Topic topic = getTopicById(obj.getAsJsonPrimitive("TopicId").getAsLong());
		if(topic==null){
			throw new CommonException(121110, "The topic is not exist.");
		}
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		if(!topic.getCreateUser().equals(currentUser.getUserid())){
			List<TopicTopology> joinedUserList = getAllJoinedUserOfTopic(topic.getId());
			Long joinNum = joinedUserList.parallelStream().filter(s->s.getReciver().equals(currentUser.getUserid())).count();
			if(joinNum==0){
				throw new CommonException(121119, "You have not joined the topic.");
			}
		}
		UserTopicInfo userTopicInfo = new UserTopicInfo(currentUser,topic).topicCategory(getUserTopicCategory(topic));
		getParticipateInfo(userTopicInfo);
		return userTopicInfo;
	}
	
	
	public List<TopicCategory> getAllUserTopicCategory(){
		logger.info("TopicService - getAllUserTopicCategory...");
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("TopicService - getAllUserTopicCategory currentUser:\n"+currentUser);
		String md5key = redisService.getMD5CacheKey(CacheType.topicCategory, currentUser.getUserid());
		logger.info("TopicService - getAllUserTopicCategory: md5key="+md5key);
		List<TopicCategory> userTopicCategoryList = redisService.getList(md5key, TopicCategory.class);
		if(userTopicCategoryList == null){
			Map<Integer,TopicCategory> mergeMap = new TreeMap<Integer,TopicCategory>();
			userTopicCategoryList = topicCategoryDao.findUserTopicCategory(currentUser.getUserid());
			userTopicCategoryList.parallelStream().forEach(s->{
				TopicCategory tc = mergeMap.get(s.getCategoryType());
				if(tc!=null&&currentUser.getUserid().equals(s.getUserId())){
					mergeMap.put(s.getCategoryType(), s);
				}else if(tc==null){
					mergeMap.put(s.getCategoryType(), s);
				}
			});
			userTopicCategoryList = new ArrayList<TopicCategory>(mergeMap.values());
			redisService.setList(md5key, userTopicCategoryList);
		}
		return userTopicCategoryList;
	}
	
	public List<TopicCategory> getAllUserTopicCategory(String userId){
		logger.info("TopicService - getAllUserTopicCategory userId:"+userId);
		String md5key = redisService.getMD5CacheKey(CacheType.topicCategory, userId);
		logger.info("TopicService - getAllUserTopicCategory: md5key="+md5key);
		List<TopicCategory> userTopicCategoryList = redisService.getList(md5key, TopicCategory.class);
		if(userTopicCategoryList == null){
			Map<Integer,TopicCategory> mergeMap = new TreeMap<Integer,TopicCategory>();
			userTopicCategoryList = topicCategoryDao.findUserTopicCategory(userId);
			userTopicCategoryList.parallelStream().forEach(s->{
				TopicCategory tc = mergeMap.get(s.getCategoryType());
				if(tc!=null&&userId.equals(s.getUserId())){
					mergeMap.put(s.getCategoryType(), s);
				}else if(tc==null){
					mergeMap.put(s.getCategoryType(), s);
				}
			});
			userTopicCategoryList = new ArrayList<TopicCategory>(mergeMap.values());
			redisService.setList(md5key, userTopicCategoryList);
		}
		return userTopicCategoryList;
	}
	
	public List<TopicAttitude> getAllTopicAttitude(){
		logger.info("TopicService - getAllTopicAttitude...");
		String md5key = redisService.getMD5CacheKey(CacheType.topicAttitudeList);
		logger.info("TopicService - getAllTopicAttitude: md5key="+md5key);
		List<TopicAttitude> topicAttitudeList = redisService.getList(md5key, TopicAttitude.class);
		if(topicAttitudeList == null){
			topicAttitudeList = topicAttitudeDao.findAll();
			if(topicAttitudeList!=null){
				redisService.setList(md5key, topicAttitudeList);
			}
		}
		return topicAttitudeList;
	}
	
	@Transactional
	public void createUserTopicCategory(TopicCategory tc){
		logger.info("TopicService - createUserTopicCategory tc:\n" + tc);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("TopicService - createUserTopicCategory currentUser:\n"+currentUser);
		tc.setUserId(currentUser.getUserid());
		topicCategoryDao.updateTopicCategory(currentUser.getUserid(), tc.getCategoryType(), 1);
		topicCategoryDao.save(tc);
		clearTopicCategory(currentUser.getUserid());
	}
	
	
	
	public List<TopicTopology> getAllJoinedTopicTopology(String userId){
		logger.info("TopicService - getAllJoinedTopicTopology: userId="+userId);
		String md5key = redisService.getMD5CacheKey(CacheType.joinedTopicTopology, userId);
		logger.info("TopicService - getAllJoinedTopicTopology: md5key="+md5key);
		List<TopicTopology> joinedTopicTopologyList = redisService.getList(md5key, TopicTopology.class);
		if(joinedTopicTopologyList == null){
			joinedTopicTopologyList = topicTopologyDao.findByReciver(userId);
			if(joinedTopicTopologyList != null){
				redisService.setList(md5key, joinedTopicTopologyList);
			}else{
				joinedTopicTopologyList = new ArrayList<TopicTopology>();
				redisService.setList(md5key, joinedTopicTopologyList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return joinedTopicTopologyList;
	}
	
	public List<TopicTopology> getAllJoinedUserOfTopic(Long topicId){
		logger.info("TopicService - getAllJoinedUserOfTopic: topicId="+topicId);
		String md5key = redisService.getMD5CacheKey(CacheType.joinedUser, topicId);
		logger.info("TopicService - getAllJoinedUserOfTopic: md5key="+md5key);
		List<TopicTopology> joinedUserList = redisService.getList(md5key, TopicTopology.class);
		if(joinedUserList == null){
			joinedUserList = topicTopologyDao.findByTopicId(topicId);
			if(joinedUserList != null){
				redisService.setList(md5key, joinedUserList);
			}else{
				joinedUserList = new ArrayList<TopicTopology>();
				redisService.setList(md5key, joinedUserList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return joinedUserList;
	}
	
	public List<TopicTopology> getAllInvitedTopicTopology(String userId){
		logger.info("TopicService - getAllInvitedTopicTopology: userId="+userId);
		String md5key = redisService.getMD5CacheKey(CacheType.invitedTopicTopology, userId);
		logger.info("TopicService - getAllInvitedTopicTopology: md5key="+md5key);
		List<TopicTopology> invitedTopicTopologyList = redisService.getList(md5key, TopicTopology.class);
		if(invitedTopicTopologyList == null){
			invitedTopicTopologyList = topicTopologyDao.findBySender(userId);
			if(invitedTopicTopologyList != null){
				redisService.setList(md5key, invitedTopicTopologyList);
			}else{
				invitedTopicTopologyList = new ArrayList<TopicTopology>();
				redisService.setList(md5key, invitedTopicTopologyList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return invitedTopicTopologyList;
	}
	
	public List<TopicInviter> getAllInvitingTopic(String userId){
		logger.info("TopicService - getAllInvitingTopic: userId="+userId);
		String md5key = redisService.getMD5CacheKey(CacheType.invitingTopicList, userId);
		logger.info("TopicService - getAllInvitingTopic: md5key="+md5key);
		List<TopicInviter> invitingTopicList = redisService.getList(md5key, TopicInviter.class);
		if(invitingTopicList == null){
			invitingTopicList = topicInviterDao.findAllTopicRequest(userId);
			if(invitingTopicList != null){
				redisService.setList(md5key, invitingTopicList);
			}else{
				invitingTopicList = new ArrayList<TopicInviter>();
				redisService.setList(md5key, invitingTopicList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return invitingTopicList;
	}
	
	
	public List<Topic> getAllJoinedTopic(String userId){
		logger.info("TopicService - getAllJoinedTopic: userId="+userId);
		List<TopicTopology> joinedTopicTopologyList = getAllJoinedTopicTopology(userId);
		return joinedTopicTopologyList.parallelStream().map(s->{return getTopicById(s.getTopicId());}).collect(Collectors.toList());
	}
	
	public List<Topic> getAllCreatedTopic(String userId){
		logger.info("TopicService - getAllCreatedTopic: userId="+userId);
		String md5key = redisService.getMD5CacheKey(CacheType.createdTopicList, userId);
		logger.info("TopicService - getAllCreatedTopic: md5key="+md5key);
		List<Topic> createdTopicList = redisService.getList(md5key, Topic.class);
		if(createdTopicList == null){
			createdTopicList = topicDao.findAllCreatedTopics(userId);
			if(createdTopicList != null){
				redisService.setList(md5key, createdTopicList);
			}else{
				createdTopicList = new ArrayList<Topic>();
				redisService.setList(md5key, createdTopicList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return createdTopicList;
	}
	
	public TopicCategory getUserTopicCategory(Topic topic){
		if(topic!=null&&topic.getDetail()!=null){
			Integer categoryType = topic.getDetail().getCategoryType()==null?0:topic.getDetail().getCategoryType();
			List<TopicCategory> userTopicCategoryList = getAllUserTopicCategory(topic.getCreateUser());
			return userTopicCategoryList.parallelStream().filter(s->s.getCategoryType().equals(categoryType)).findFirst().orElseGet(null);
		}else{
			return null;
		}
	}
	
	public List<TopicInviter> getAllInvitedTopic(String userId){
		logger.info("TopicService - getAllInvitedTopic: userId="+userId);
		String md5key = redisService.getMD5CacheKey(CacheType.invitedTopicList, userId);
		logger.info("TopicService - getAllInvitedTopic: md5key="+md5key);
		List<TopicInviter> inviteTopicList = redisService.getList(md5key, TopicInviter.class);
		if(inviteTopicList == null){
			inviteTopicList = topicInviterDao.findAllTopicInvite(userId);
			if(inviteTopicList != null){
				redisService.setList(md5key, inviteTopicList);
			}else{
				inviteTopicList = new ArrayList<TopicInviter>();
				redisService.setList(md5key, inviteTopicList,GlobalConstants.CACHE_SHORT_TIMEOUT);
			}
		}
		return inviteTopicList;
	}
	
	public Topic getTopicById(Long topicId){
		logger.info("TopicService - getTopicById: topicId="+topicId);
		String md5key = redisService.getMD5CacheKey(CacheType.topicInfo, topicId);
		logger.info("TopicService - getTopicById: md5key="+md5key);
		Topic topic = redisService.getObject(md5key, Topic.class);
		if(topic == null){
			topic = topicDao.findOne(topicId);
			if(topic != null){
				redisService.setObjct(md5key, topic);
			}
		}
		return topic;
	}
	
	public TopicTopologyModel getParticipatorByTopicId(Long topicId) throws CommonException{
		logger.info("TopicService - getParticipatorByTopicId: topicId="+topicId);
		Topic topic = getTopicById(topicId);
		if(topic==null){
			throw new CommonException(121110, "Topic is not exist");
		}
		List<TopicTopology> joinedUserList = getAllJoinedUserOfTopic(topicId);
		/*if(joinedUserList.size()==0){
			throw new CommonException(121116, "Topic has no participator.");
		}*/
		String createUserId = topic.getCreateUser();
		logger.info("TopicService - getParticipatorByTopicId: createUserId="+createUserId);
		User createUser = userService.getUserByUserid(createUserId);
		TopicTopologyModel model = new TopicTopologyModel(createUserId,createUser.getUserProfile().getThumbnailheadIcon());
		extractTopicTopology(createUserId,joinedUserList,model);
		return model;
	}
	
	
	public void extractTopicTopology(String root,List<TopicTopology> joinedUserList,TopicTopologyModel model){
		joinedUserList.parallelStream().filter(s->s.getSender().equals(root)).forEach(s->{
			User participator = userService.getUserByUserid(s.getReciver());
			model.addNode(new TopicTopologyModel.Node(s.getReciver(),participator.getUserProfile().getThumbnailheadIcon()));
			model.addLink(new TopicTopologyModel.Link(s.getReciver(), root, String.valueOf(s.getStatus())));
			extractTopicTopology(s.getReciver(),joinedUserList,model);
		});
	}
	
	public Map<String,Integer> sendTopicToUserList(Long topicId, String receivers, String message){
		logger.info("TopicService - sendTopicToUserList topicId:"+topicId);
		logger.info("TopicService - sendTopicToUserList receivers:"+receivers);
		logger.info("TopicService - sendTopicToUserList message:"+message);
		Map<String,Integer> result = new HashMap<String,Integer>();
		String[] receiverArray = StringUtils.commaDelimitedListToStringArray(receivers);
		for(String receiver : receiverArray){
			try {
				sendTopicToUser(topicId, receiver, message);
				result.put(receiver, 1);
			} catch (CommonException e) {
				logger.info("TopicService - sendTopicToUserList receiver:"+receiver+" code:" + e.getCode());
				logger.info("TopicService - sendTopicToUserList receiver:"+receiver+" message:" + e.getMessage());
				result.put(receiver, e.getCode());
			}
		}
		return result;
	}
	
	public void sendTopicToUser(Long topicId,String receiver,String message) throws CommonException{
		logger.info("TopicService - sendTopicToUser topicId:"+topicId);
		logger.info("TopicService - sendTopicToUser receiver:"+receiver);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("TopicService - sendTopicToUser currentUser:\n"+currentUser);
		if(currentUser.getUserid().equals(receiver)){
			throw new CommonException(121111, "You can't invite your self.");
		}
		
		Topic topic = getTopicById(topicId);
		if(topic==null){
			throw new CommonException(121110, "Topic is not exist");
		}
		
		if(topic.getStatus()==1){
			throw new CommonException(121115, "The topic is closed.");
		}
		
		User receiverUser = userService.getUserByUserid(receiver);
		if(receiverUser == null){
			throw new CommonException(121117, "The receiver is not a subscriber.");
		}
		
		if(topic.getCreateUser().equals(receiver)){
			throw new CommonException(121111, "You can't invite the topic owner.");
		}
		
		TopicDetail topicDetail = topic.getDetail();
		
		List<TopicTopology> joinedUserList = getAllJoinedUserOfTopic(topicId);
		if(topicDetail.getTotalNum()!=null&&joinedUserList.size()>=topicDetail.getTotalNum()){
			throw new CommonException(121117, "The joining number of topic is up to limit.");
		}
		Long senderjoinNum = joinedUserList.parallelStream().filter(s->s.getReciver().equals(currentUser.getUserid())).count();
		if(senderjoinNum==0 && !topic.getCreateUser().equals(currentUser.getUserid())){
			throw new CommonException(121116, "You have not joined the topic.");
		}
		
		Long sendNum = joinedUserList.parallelStream().filter(s->s.getSender().equals(currentUser.getUserid())).count();
		if(sendNum>=topicDetail.getRightNum()){
			throw new CommonException(121113, "The number of your inviting is up to limit.");
		}
		
		//用户已发邀请
		List<TopicInviter> inviteTopicList = getAllInvitedTopic(receiver);
		Long inviteNum = inviteTopicList.parallelStream().filter(s->s.getTopicId()==topicId&&s.getSender().equals(currentUser.getUserid())&&s.getReciver().equals(receiver)).count();
		if(inviteNum>0){
			throw new CommonException(121115, "Yon have already invited your friend to the topic.");
		}
		//已在话题中
		Long joinNum = joinedUserList.parallelStream().filter(s->s.getReciver().equals(receiver)).count();
		if(joinNum>0){
			throw new CommonException(121114, "Your friend have joined the topic.");
		}
		TopicInviter topicInviter = new TopicInviter(topicId, currentUser.getUserid(), receiver, 1);
		topicInviter.setInviteTime(new Date().getTime());
		if(!StringUtils.isEmpty(message)){
			topicInviter.setMessage(message);
		}
		topicInviterDao.save(topicInviter);
		clearInvitedTopicList(receiver);
	}
	
	
	public void sendTopicRequestToUser(Long topicId,String sender) throws CommonException{
		logger.info("TopicService - sendTopicToUser topicId:"+topicId);
		logger.info("TopicService - sendTopicToUser sender:"+sender);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("TopicService - sendTopicToUser currentUser:\n"+currentUser);
		Topic topic = getTopicById(topicId);
		if(topic==null){
			throw new CommonException(121110, "Topic is not exist");
		}
		
		if(topic.getStatus()==1){
			throw new CommonException(121115, "The topic is closed.");
		}
		
		User senderUser = userService.getUserByUserid(sender);
		if(senderUser == null){
			throw new CommonException(121117, "The sender is not a subscriber.");
		}
		
		TopicDetail topicDetail = topic.getDetail();
		
		List<TopicTopology> joinedUserList = getAllJoinedUserOfTopic(topicId);
		if(topicDetail.getTotalNum()!=null&&joinedUserList.size()>=topicDetail.getTotalNum()){
			throw new CommonException(121117, "The joining number of topic is up to limit.");
		}
		Long senderjoinNum = joinedUserList.parallelStream().filter(s->s.getReciver().equals(sender)).count();
		if(senderjoinNum==0 && !topic.getCreateUser().equals(currentUser.getUserid())){
			throw new CommonException(121116, "The sender have not joined the topic.");
		}
		
		Long sendNum = joinedUserList.parallelStream().filter(s->s.getSender().equals(sender)).count();
		if(sendNum>=topicDetail.getRightNum()){
			throw new CommonException(121113, "The number of inviting is up to limit.");
		}
		
		//用户已发邀请
		List<TopicInviter> invitingTopicList = getAllInvitingTopic(sender);
		Long inviteNum = invitingTopicList.parallelStream().filter(s->s.getTopicId()==topicId&&s.getSender().equals(sender)&&s.getReciver().equals(currentUser.getUserid())).count();
		if(inviteNum>0){
			throw new CommonException(121115, "Yon have already been invited by your friend to the topic.");
		}
		//已在话题中
		Long joinNum = joinedUserList.parallelStream().filter(s->s.getReciver().equals(currentUser.getUserid())).count();
		if(joinNum>0){
			throw new CommonException(121114, "You have joined the topic.");
		}
		TopicInviter topicInviter = new TopicInviter(topicId, sender,currentUser.getUserid(), 0);
		topicInviterDao.save(topicInviter);
		clearInvitingTopicList(sender);
	}
	
	@Transactional
	public Map<String,Object> handOverTopic(Long topicId, String sender, String receivers,Integer link) throws CommonException{
		logger.info("TopicService - handOverTopic topicId:"+topicId);
		logger.info("TopicService - handOverTopic sender:"+sender);
		logger.info("TopicService - handOverTopic receivers:"+receivers);
		updateTopicInvite(topicId,sender,null,2,"0","转发");
		Map<String,Integer> handOver = sendTopicToUserList(topicId, receivers, "转发");
		Map<String,Object> result = new HashMap<String,Object>();
		if(!handOver.isEmpty()){
			result.put("handOver", handOver);
		}
		if(link==1){
			Map<String,String> linkInfo = getInviteInfoOfTopic(topicId);
			result.put("linkInfo", linkInfo);
		}
		return result;
	}
	
	@Transactional
	public void joinTopicAgain(Long topologyId, String status, String comment){
		logger.info("TopicService - joinTopicAgain topologyId:"+topologyId);
		logger.info("TopicService - joinTopicAgain status:"+status);
		logger.info("TopicService - joinTopicAgain comment:"+comment);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		List<TopicTopology> joinedTopicTopologyList = getAllJoinedTopicTopology(currentUser.getUserid());
		Optional<TopicTopology> joinedTopology = joinedTopicTopologyList.stream().filter(s->s.getId().equals(topologyId)).findFirst();
		joinedTopology.ifPresent(s->{
			logger.info("TopicService - joinTopicAgain have found TopicTopology and updating...");
			String newStatus = StringUtils.isEmpty(s.getStatus())?status:s.getStatus()+"-"+status;
			String newComment = StringUtils.isEmpty(s.getComment())?comment:s.getComment()+"<|>"+comment;
			topicTopologyDao.updateTopologyById(topologyId, newStatus, newComment);
			clearTopicTopologyCache(s.getTopicId(),s.getSender(),s.getReciver());
		});
	}
	
	@Transactional
	public void updateSelectionForJoiner(Long topologyId, Integer selection, String receiver){
		logger.info("TopicService - updateSelectionForJoiner topologyId:"+topologyId);
		logger.info("TopicService - updateSelectionForJoiner selection:"+selection);
		logger.info("TopicService - updateSelectionForJoiner receiver:"+receiver);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("TopicService - updateSelectionForJoiner currentUser:"+currentUser);
		List<TopicTopology> joinedTopicTopologyList = getAllJoinedTopicTopology(receiver);
		Optional<TopicTopology> joinedTopology = joinedTopicTopologyList.stream().filter(s->s.getId().equals(topologyId)).findFirst();
		joinedTopology.ifPresent(s->{
			logger.info("TopicService - updateSelectionForJoiner have found TopicTopology.");
			Topic topic = this.getTopicById(s.getTopicId());
			logger.info("TopicService - updateSelectionForJoiner currentUser.getUserid():" + currentUser.getUserid());
			logger.info("TopicService - updateSelectionForJoiner topic.getCreateUser():" + topic.getCreateUser());
			if(topic.getCreateUser().equals(currentUser.getUserid())){
				logger.info("TopicService - updateSelectionForJoiner current user is the creating user of topic and updating.");
				topicTopologyDao.updateSelectionById(topologyId, selection);
				clearTopicTopologyCache(s.getTopicId(),s.getSender(),s.getReciver());
			}else{
				logger.info("TopicService - updateSelectionForJoiner current user is not the creating user of topic and can not update.");
			}
		});
	}
	
	@Transactional
	public void updateTopicInvite(Long topicId, String sender, String receiver, Integer status, String topologyStatus, String comment) throws CommonException{
		logger.info("TopicService - updateTopicInvite topicId:"+topicId);
		logger.info("TopicService - updateTopicInvite sender:"+sender);
		logger.info("TopicService - updateTopicInvite receiver:"+receiver);
		logger.info("TopicService - updateTopicInvite status:"+status);
		logger.info("TopicService - updateTopicInvite topologyStatus:"+topologyStatus);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		logger.info("TopicService - sendTopicToUser currentUser:\n"+currentUser);
		if(StringUtils.isEmpty(receiver)&&StringUtils.isEmpty(sender)){
			throw new CommonException(121210, "Parameter is not right.");
		}else if(StringUtils.isEmpty(receiver)&&!StringUtils.isEmpty(sender)){
			List<TopicInviter> invitedTopicList = this.getAllInvitedTopic(currentUser.getUserid());
			if(invitedTopicList.parallelStream().filter(s->s.getSender().equals(sender)).count()==0){
				throw new CommonException(121213, "You have not been invited by your friend.");
			}
			if(status==2){
				addUserForTopic(topicId, sender, topologyStatus==null?"0":topologyStatus,0,comment);
			}
			topicInviterDao.updateInviteStatus(sender,currentUser.getUserid(),topicId,status);
			clearInvitedTopicList(currentUser.getUserid());
		}else if(!StringUtils.isEmpty(receiver)&&StringUtils.isEmpty(sender)){
			topicInviterDao.updateInviteStatus(currentUser.getUserid(),receiver,topicId,status);
			clearInvitedTopicList(receiver);
			clearInvitingTopicList(currentUser.getUserid());
		}
	}
	
	public void getParticipateInfo(UserTopicInfo userTopicInfo){
		List<TopicTopology> joinedUserList = this.getAllJoinedUserOfTopic(userTopicInfo.getTopicId());
		userTopicInfo.setCurrentTotal(joinedUserList.size());
		joinedUserList.stream().limit(5).forEach(s->{
			User joinedUser = userService.getUserByUserid(s.getReciver());
			userTopicInfo.addJoinedUserInfo(joinedUser);
		});
		userTopicInfo.setCurrentUserNum((int) joinedUserList.parallelStream().filter(s->s.getSender().equals(userTopicInfo.getSender())).count());
	}
	
	public List<UserTopicInfo> findUserJoinInfoByTopicId(Long topicId,String receiver) throws CommonException{
		Topic topic = getTopicById(topicId);
		if(topic==null){
			throw new CommonException(121110, "Topic is not exist");
		}
		List<TopicTopology> joinedUserList = getAllJoinedUserOfTopic(topicId);
		if(StringUtils.isEmpty(receiver)){
			return joinedUserList.stream().filter(s->{
				String status = s.getStatus()==null?"0":s.getStatus();
				status = status.lastIndexOf("-")!=-1?status.substring(status.lastIndexOf("-")+1):status;
				return !status.equals("0")&&!status.equals("5")&&!status.equals("6");}).map(s->{
			    User receiverUser = userService.getUserByUserid(s.getReciver());
			    return new UserTopicInfo(receiverUser,null,s);
				})
			.sorted((s1,s2)->s1.getTopicTopologyStatus().compareTo(s2.getTopicTopologyStatus()))
			.collect(Collectors.toList());
		}else{
			List<UserTopicInfo> userTopicInfoList = new ArrayList<UserTopicInfo>();
			String createUser = topic.getCreateUser();
			String tempReciver = receiver;
			while(true){
				//Optional<TopicTopology> receiverTopicTopology = joinedUserList.stream().filter(s->s.getReciver().equals(tempReciver)).findFirst();
				TopicTopology receiverTopicTopology = null;
				for(TopicTopology tt : joinedUserList){
					if(tt.getReciver().equals(tempReciver)){
						receiverTopicTopology = tt;
						break;
					}
				}
				
				if(receiverTopicTopology!=null){
					User receiverUser = userService.getUserByUserid(tempReciver);
					userTopicInfoList.add(0, new UserTopicInfo(receiverUser,null,receiverTopicTopology));
					if(createUser.equals(receiverTopicTopology.getSender())){
						break;
					}else{
						tempReciver = receiverTopicTopology.getSender();
					}
				}else{
					break;
				}
			}
			return userTopicInfoList;
		}
		
	}
	
	public void clearTopicTopologyCache(Long topicId,String sender,String reciver){
		logger.info("TopicService - clearTopicTopologyCache: sender="+sender);
		logger.info("TopicService - clearTopicTopologyCache: reciver="+reciver);
		logger.info("TopicService - clearTopicTopologyCache: topicId="+topicId);
		String md5key_join_user = redisService.getMD5CacheKey(CacheType.joinedUser, topicId);
		String md5key_join = redisService.getMD5CacheKey(CacheType.joinedTopicTopology, reciver);
		String md5key_invite = redisService.getMD5CacheKey(CacheType.invitedTopicTopology, sender);
		logger.info("TopicService - clearTopicTopologyCache: md5key_join_user="+md5key_join_user);
		logger.info("TopicService - clearTopicTopologyCache: md5key_join="+md5key_join);
		logger.info("TopicService - clearTopicTopologyCache: md5key_invite="+md5key_invite);
		redisService.remove(md5key_join_user);
		redisService.remove(md5key_join);
		redisService.remove(md5key_invite);
	}
	
	public void clearTopicCahce(String userId){
		logger.info("TopicService - clearTopicCahce: userId="+userId);
		String md5key_join = redisService.getMD5CacheKey(CacheType.createdTopicList, userId);
		logger.info("TopicService - clearTopicTopologyCache: md5key_join="+md5key_join);
		redisService.remove(md5key_join);
	}
	
	public void clearTopicCategory(String userId){
		logger.info("TopicService - clearTopicCategory: userId="+userId);
		String md5key_category = redisService.getMD5CacheKey(CacheType.topicCategory, userId);
		logger.info("TopicService - clearTopicCategory: md5key_category="+md5key_category);
		redisService.remove(md5key_category);
	}
	
	public void clearInvitedTopicList(String userId){
		logger.info("TopicService - clearInviteList: userId="+userId);
		String md5key_invite = redisService.getMD5CacheKey(CacheType.invitedTopicList, userId);
		logger.info("TopicService - clearInviteList: md5key_category="+md5key_invite);
		redisService.remove(md5key_invite);
	}
	
	public void clearInvitingTopicList(String userId){
		logger.info("TopicService - clearInvitingTopicList: userId="+userId);
		String md5key_inviting = redisService.getMD5CacheKey(CacheType.invitingTopicList, userId);
		logger.info("TopicService - clearInvitingTopicList: md5key_inviting="+md5key_inviting);
		redisService.remove(md5key_inviting);
	}
}
