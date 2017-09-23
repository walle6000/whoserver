package io.swagger.api;

import java.io.File;
import io.swagger.model.ResultMsg;
import io.swagger.model.Topic;
import io.swagger.model.TopicAttitude;
import io.swagger.model.TopicCategory;
import io.swagger.model.TopicTopology;
import io.swagger.model.TopicTopologyModel;
import io.swagger.model.UserTopicInfo;
import io.swagger.service.TopicService;
import io.swagger.annotations.*;
import io.swagger.exception.CommonException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

@Controller
public class TopicApiController implements TopicApi {
	
	private Logger logger = LoggerFactory.getLogger(TopicApiController.class);
	
	@Autowired
	private TopicService topicService;
	
	@Override
	public ResponseEntity<ResultMsg> createNewTopic(@ApiParam(value = "title of topic", required = false) @RequestParam(value = "title", required = false)String topicTitle,
                                                    @NotNull @ApiParam(value = "content of info", required = true) @RequestParam(value = "content", required = true)String topicContent,
                                                    @NotNull @ApiParam(value = "right number of user join", required = true) @RequestParam(value = "rightNum", required = true)Integer rightNum,
                                                    @ApiParam(value = "total number of user join", required = false) @RequestParam(value = "totalNums", required = false)Integer totalNum,
                                                    @ApiParam(value = "total number of tie", required = false) @RequestParam(value = "tierNum", required = false)Integer tierNum,
                                                    @NotNull @ApiParam(value = "totoal Amount of topic", required = true) @RequestParam(value = "totoalAmount", required = true)Integer totoalAmount,
                                                    @ApiParam(value = "Ratio of Amount of", required = false) @RequestParam(value = "ratio", required = false)Integer ratio,
                                                    @ApiParam(value = "total number of reward user", required = false) @RequestParam(value = "totalRewardNum", required = false)Integer totalRewardNum,
                                                    @ApiParam(value = "The discussion type", required = false) @RequestParam(value = "discussType", required = false)Integer discussType,
                                                    @NotNull @ApiParam(value = "The constant time of topic", required = true) @RequestParam(value = "period", required = true)Integer period,
                                                    @ApiParam(value = "assign type of Amount", required = false) @RequestParam(value = "assignType", required = false)Integer assignType,
                                                    @NotNull @ApiParam(value = "category Type of topic", required = true) @RequestParam(value = "categoryType", required = true)Integer categoryType,
                                                    @ApiParam(value = "picture files of topic") @RequestParam(value="postImages",required = false) List<MultipartFile> postImages) {
		 ResultMsg response = null;
		 try {
			  topicService.createNewTopic(topicTitle,topicContent,
					                      rightNum==null?3:rightNum,
					                      totalNum,tierNum,
					                      totoalAmount==null?0:totoalAmount,
					                      ratio==null?50:ratio,
					                      totalRewardNum,
					                      discussType==null?0:discussType,
					                      period==null?7:period,
					                      assignType==null?0:assignType,
					                      categoryType==null?0:categoryType,
					                      postImages==null?new ArrayList<MultipartFile>():postImages);
		 } catch (CommonException e) {
				response = new ResultMsg(e.getCode(),e.getMessage());
				e.printStackTrace();
				return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
		 }
		 response = new ResultMsg(1,	"creating topic successfully.");
		 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResultMsg> createNewTopicWithNoPic(@ApiParam(value = "title of topic", required = false) @RequestParam(value = "title", required = false)String topicTitle,
            @NotNull @ApiParam(value = "content of info", required = true) @RequestParam(value = "content", required = true)String topicContent,
            @NotNull @ApiParam(value = "right number of user join", required = true) @RequestParam(value = "rightNum", required = true)Integer rightNum,
            @ApiParam(value = "total number of user join", required = false) @RequestParam(value = "totalNums", required = false)Integer totalNum,
            @ApiParam(value = "total number of tie", required = false) @RequestParam(value = "tierNum", required = false)Integer tierNum,
            @NotNull @ApiParam(value = "totoal Amount of topic", required = true) @RequestParam(value = "totoalAmount", required = true)Integer totoalAmount,
            @ApiParam(value = "Ratio of Amount of", required = false) @RequestParam(value = "ratio", required = false)Integer ratio,
            @ApiParam(value = "total number of reward user", required = false) @RequestParam(value = "totalRewardNum", required = false)Integer totalRewardNum,
            @ApiParam(value = "The discussion type", required = false) @RequestParam(value = "discussType", required = false)Integer discussType,
            @NotNull @ApiParam(value = "The constant time of topic", required = true) @RequestParam(value = "period", required = true)Integer period,
            @ApiParam(value = "assign type of Amount", required = false) @RequestParam(value = "assignType", required = false)Integer assignType,
            @NotNull @ApiParam(value = "category Type of topic", required = true) @RequestParam(value = "categoryType", required = true)Integer categoryType) {
		ResultMsg response = null;
		 try {
			  topicService.createNewTopic(topicTitle,topicContent,
					                      rightNum==null?3:rightNum,
					                      totalNum,tierNum,
					                      totoalAmount==null?0:totoalAmount,
					                      ratio==null?50:ratio,
					                      totalRewardNum,
					                      discussType==null?0:discussType,
					                      period==null?7:period,
					                      assignType==null?0:assignType,
					                      categoryType==null?0:categoryType,
					                      new ArrayList<MultipartFile>());
		 } catch (CommonException e) {
				response = new ResultMsg(e.getCode(),e.getMessage());
				e.printStackTrace();
				return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
		 }
		 response = new ResultMsg(1,	"creating topic successfully.");
		 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

    public ResponseEntity<ResultMsg> addTopic(@ApiParam(value = "Topic object that needs to be added to the store" ,required=true ) @RequestBody Topic topic) {
        // do some magic!
        return new ResponseEntity<ResultMsg>(HttpStatus.OK);
    }

    public ResponseEntity<ResultMsg> addUserForTopic(@ApiParam(value = "ID of topic to update",required=true ) @PathVariable("topicId") Long topicId,
                                                     @ApiParam(value = "person who send the topic link.", required=true ) @RequestParam(value="sender", required=true)  String sender,
                                                     @ApiParam(value = "attitude for the topic.", required=true ) @RequestParam(value="status", required=true)  String status,
                                                     @ApiParam(value = "source of the joining topic.", required=false ) @RequestParam(value="source", required=false)  Integer source,
                                                     @ApiParam(value = "comment for the topic.", required=false ) @RequestParam(value="comment", required=false)  String comment) {
    	ResultMsg response = null;
		 try {
			 topicService.addUserForTopic(topicId, sender, status==null?"0":status, source, comment);
		 } catch (CommonException e) {
				response = new ResultMsg(e.getCode(),e.getMessage());
				e.printStackTrace();
				return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
		 }
		 response = new ResultMsg(1,"add User For Topic successfully.");
		 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResultMsg> closeTopic(@ApiParam(value = "Topic id to delete",required=true ) @PathVariable("topicId") Long topicId) {
        // do some magic!
        return new ResponseEntity<ResultMsg>(HttpStatus.OK);
    }

    public ResponseEntity<List<Topic>> findTopicsByStatus( @NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "AVAILABLE, PENDING, CLOSE") @RequestParam(value = "status", required = true) List<String> status) {
        // do some magic!
        return new ResponseEntity<List<Topic>>(HttpStatus.OK);
    }

    public ResponseEntity<List<Topic>> findTopicsByTags( @NotNull @ApiParam(value = "Tags to filter by", required = true) @RequestParam(value = "tags", required = true) List<String> tags) {
        // do some magic!
        return new ResponseEntity<List<Topic>>(HttpStatus.OK);
    }

    public ResponseEntity<TopicTopologyModel> getParticipatorByTopicId(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId) {
    	TopicTopologyModel model = null;
		 try {
			 model = topicService.getParticipatorByTopicId(topicId);
		 } catch (CommonException e) {
			 logger.error(e.getMessage());
			 return new ResponseEntity<TopicTopologyModel>(new TopicTopologyModel(),HttpStatus.OK);
		 }
		 return new ResponseEntity<TopicTopologyModel>(model,HttpStatus.OK);
    }

    public ResponseEntity<Topic> getTopicById(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId) {
        // do some magic!
    	Topic topic = topicService.getTopicById(topicId);
        return new ResponseEntity<Topic>(topic,HttpStatus.OK);
    }

    public ResponseEntity<Map<String,List<UserTopicInfo>>> getAllTopics() {
    	Map<String,List<UserTopicInfo>> allTopics = topicService.getAllTopics();
        // do some magic!
        return new ResponseEntity<Map<String,List<UserTopicInfo>>>(allTopics,HttpStatus.OK);
    }

    public ResponseEntity<ResultMsg> sendTopicToUser(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
                                                     @NotNull @ApiParam(value = "person who receive the topic.", required=true ) @RequestParam(value="receiver", required=true)  String receiver,
                                                     @ApiParam(value = "message that will sent to receiver about the topic.", required=false ) @RequestParam(value="message", required=false)  String message) {
    	 ResultMsg response = null;
		 try {
			 topicService.sendTopicToUser(topicId, receiver, message);
		 } catch (CommonException e) {
			 response = new ResultMsg(e.getCode(),e.getMessage());
			 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
		 }
		 response = new ResultMsg(1,"send Topic To User successfully.");
		 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResultMsg> updateTopic(@ApiParam(value = "Topic object that needs to be added to the store" ,required=true ) @RequestBody Topic topic) {
        // do some magic!
        return new ResponseEntity<ResultMsg>(HttpStatus.OK);
    }

    public ResponseEntity<ResultMsg> uploadFile(@ApiParam(value = "ID of topic to update",required=true ) @PathVariable("topicId") Long topicId,
                                                @ApiParam(value = "file detail") @RequestPart("file") MultipartFile img,
                                                @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file) {
        // do some magic!
        return new ResponseEntity<ResultMsg>(HttpStatus.OK);
    }

	@Override
	public ResponseEntity<ResultMsg> getInviteInfoOfTopic(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId) {
		 ResultMsg response = null;
		 Map<String,String> result = null;
		 try {
			  result = topicService.getInviteInfoOfTopic(topicId);
		 } catch (CommonException e) {
			 response = new ResultMsg(e.getCode(),e.getMessage());
			 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
		 }
		 response = new ResultMsg(1,"creating topic successfully.",result);
		 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserTopicInfo> acceptTopicByInviteCode(@NotNull @ApiParam(value = "Inviting encode code", required = true) @RequestParam(value = "inviteCode", required = true) String inviteCode) {
		UserTopicInfo result = null;
		 try {
			  result = topicService.getTopicByInviteCode(inviteCode);
		 } catch (CommonException e) {
			 logger.error(e.getMessage());
			 return new ResponseEntity<UserTopicInfo>(new UserTopicInfo(),HttpStatus.OK);
		 }
		 return new ResponseEntity<UserTopicInfo>(result,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<UserTopicInfo> getJoinedTopicByInviteCode(@NotNull @ApiParam(value = "Inviting encode code", required = true) @RequestParam(value = "inviteCode", required = true) String inviteCode) {
		UserTopicInfo result = null;
		 try {
			  result = topicService.getJoinedTopicByInviteCode(inviteCode);
		 } catch (CommonException e) {
			 logger.error(e.getMessage());
			 return new ResponseEntity<UserTopicInfo>(new UserTopicInfo(),HttpStatus.OK);
		 }
		 return new ResponseEntity<UserTopicInfo>(result,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TopicCategory>> getAllUserTopicCategory() {
		List<TopicCategory> userTopicCategoryList = topicService.getAllUserTopicCategory();
		return new ResponseEntity<List<TopicCategory>>(userTopicCategoryList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResultMsg> createUserTopicCategory(@ApiParam(value = "Topic category object that needs to be added to the store for user" ,required=true ) @RequestBody TopicCategory tc) {
		topicService.createUserTopicCategory(tc);
		ResultMsg response = new ResultMsg(1,"creating topic category successfully.");
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResultMsg> updateTopicInvite(@NotNull @ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
                                                       @ApiParam(value = "person who sender the topic inviting.", required=false ) @RequestParam(value="sender", required=false)  String sender,
                                                       @ApiParam(value = "person who receive the topic inviting.", required=false ) @RequestParam(value="receiver", required=false)  String receiver,
                                                       @ApiParam(value = "status of the topic inviting.", required=true ) @RequestParam(value="status", required=true)  Integer status,
                                                       @ApiParam(value = "topology status of the topic inviting.", required=false ) @RequestParam(value="topologyStatus", required=false) String topologyStatus,
                                                       @ApiParam(value = "comment of the topic inviting.", required=false ) @RequestParam(value="comment", required=false) String comment) {
		 ResultMsg response = null;
		 try {
			 topicService.updateTopicInvite(topicId, sender, receiver, status, topologyStatus, comment);
		 } catch (CommonException e) {
			 response = new ResultMsg(e.getCode(),e.getMessage());
			 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
		 }
		 response = new ResultMsg(1,"update Topic Invite successfully.");
		 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResultMsg> sendTopicRequestToUser(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
                                                            @ApiParam(value = "person who receive the topic.", required=true ) @RequestParam(value="sender", required=true)  String sender) {
		ResultMsg response = null;
		 try {
			 topicService.sendTopicRequestToUser(topicId, sender);
		 } catch (CommonException e) {
			 response = new ResultMsg(e.getCode(),e.getMessage());
			 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
		 }
		 response = new ResultMsg(1,"send Topic Request To User successfully.");
		 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TopicAttitude>> getAllTopicAttitude() {
		List<TopicAttitude> topicAttitudeList = topicService.getAllTopicAttitude();
		return new ResponseEntity<List<TopicAttitude>>(topicAttitudeList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResultMsg> addUserForTopicByCode(@NotNull @ApiParam(value = "The invite code which joiner reponse", required=true ) @RequestParam(value="inviteCode", required=true)  String inviteCode,
                                                           @NotNull @ApiParam(value = "attitude for the topic.", required=true ) @RequestParam(value="status", required=true)  String status,
                                                           @ApiParam(value = "source of the joining topic.", required=false ) @RequestParam(value="source", required=false)  Integer source,
                                                           @ApiParam(value = "comment of the topic.", required=false ) @RequestParam(value="comment", required=false) String comment) {
		ResultMsg response = null;
		 try {
			 topicService.addUserForTopicByCode(inviteCode, status, source, comment);
		 } catch (CommonException e) {
				response = new ResultMsg(e.getCode(),e.getMessage());
				//e.printStackTrace();
				return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
		 }
		 response = new ResultMsg(1,"add User For Topic successfully.");
		 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResultMsg> sendTopicToUserList(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
                                                         @NotNull @ApiParam(value = "person list who receive the topic inviting.", required=true ) @RequestParam(value="receivers", required=true)  String receivers,
                                                         @ApiParam(value = "message that will sent to receiver about the topic.", required=false ) @RequestParam(value="message", required=false)  String message) {
		Map<String,Integer> result = topicService.sendTopicToUserList(topicId,receivers,message);
		return new ResponseEntity<ResultMsg>(new ResultMsg(1,"send Topic To User List successfully.",result),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResultMsg> handOverTopic(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
                                                   @ApiParam(value = "person list who receive the topic inviting.", required=false ) @RequestParam(value="receivers", required=false)  String receivers,
                                                   @NotNull @ApiParam(value = "The inviter who send the topic first.", required=true ) @RequestParam(value="sender", required=true)  String sender,
                                                   @NotNull @ApiParam(value = "Is return a link or not", required=true ) @RequestParam(value="link", required=true)  Integer link) {
		ResultMsg response = null;
		Map<String,Object> result = null;
		 try {
			    result = topicService.handOverTopic(topicId, sender, receivers, link);
		 } catch (CommonException e) {
				response = new ResultMsg(e.getCode(),e.getMessage());
				return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
		 }
		 response = new ResultMsg(1,"hand Over Topic successfully.",result);
		 return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResultMsg> joinTopicAgain(@NotNull @ApiParam(value = "ID of topology the user join.",required=true ) @PathVariable("topologyId") Long topologyId,
                                                    @ApiParam(value = "status of the topic inviting.", required=true ) @RequestParam(value="status", required=true)  String status,
                                                    @ApiParam(value = "comment of the topic inviting.", required=false ) @RequestParam(value="comment", required=false) String comment) {
		status=status==null?"0":status;
		comment=comment==null?"None":comment;
		topicService.joinTopicAgain(topologyId,status,comment);
		ResultMsg response = new ResultMsg(1,"join Topic Again successfully.");
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserTopicInfo>> findTopicUserJoinInfo(@NotNull @ApiParam(value = "Topic ID which the user join.",required=true ) @PathVariable("topicId") Long topicId,
                                                           @ApiParam(value = "userId of joined user in the topic.", required=false ) @RequestParam(value="receiver", required=false) String receiver){
		List<UserTopicInfo> userTopicInfoList = null; 
		try {
			  userTopicInfoList = topicService.findUserJoinInfoByTopicId(topicId, receiver);
		 } catch (CommonException e) {
				logger.error(e.getMessage());
				return new ResponseEntity<List<UserTopicInfo>>(new ArrayList<UserTopicInfo>(),HttpStatus.OK);
		 }
		 return new ResponseEntity<List<UserTopicInfo>>(userTopicInfoList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResultMsg> updateSelectionForJoiner(@NotNull @ApiParam(value = "ID of topology the user join.",required=true ) @PathVariable("topologyId") Long topologyId,
                                                              @NotNull @ApiParam(value = "give the selection choice for the joiner", required=true ) @RequestParam(value="selection", required=true)  Integer selection,
                                                              @NotNull @ApiParam(value = "userId of joined user in the topic.", required=true ) @RequestParam(value="receiver", required=true) String receiver) {
		topicService.updateSelectionForJoiner(topologyId, selection, receiver);
		ResultMsg response = new ResultMsg(1,"join Topic Again successfully.");
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

}
