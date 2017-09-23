package io.swagger.api;

import java.io.File;
import io.swagger.model.ResultMsg;
import io.swagger.model.Topic;
import io.swagger.model.TopicAttitude;
import io.swagger.model.TopicCategory;
import io.swagger.model.TopicTopology;
import io.swagger.model.TopicTopologyModel;
import io.swagger.model.UserTopicInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

@Api(value = "topic", description = "the topic API")
public interface TopicApi {
	
	@ApiOperation(value = "create a topic for a user", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic",
    		produces = { "application/json" }, 
            consumes = { "multipart/form-data" },
            method = RequestMethod.POST)
	ResponseEntity<ResultMsg> createNewTopic(@ApiParam(value = "title of topic", required = false) @RequestParam(value = "title", required = false)String topicTitle,
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
	                                         @ApiParam(value = "picture files of topic") @RequestParam(value="postImages",required = false) List<MultipartFile> postImages);
	
	@ApiOperation(value = "create a topic without any images for a user", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/noImgs",
    		produces = { "application/json" }, 
            consumes = { "application/x-www-form-urlencoded" },
            method = RequestMethod.POST)
	ResponseEntity<ResultMsg> createNewTopicWithNoPic(@ApiParam(value = "title of topic", required = false) @RequestParam(value = "title", required = false)String topicTitle,
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
			                                          @NotNull @ApiParam(value = "category Type of topic", required = true) @RequestParam(value = "categoryType", required = true)Integer categoryType);

    @ApiOperation(value = "Add a new topic.", notes = "", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Invalid input", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/add",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> addTopic(@ApiParam(value = "Topic object that needs to be added to the store" ,required=true ) @RequestBody Topic topic);


    @ApiOperation(value = "add a user for a topic", notes = "", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/{topicId}/addUser",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> addUserForTopic(@ApiParam(value = "ID of topic to update",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "person who send the topic.", required=true ) @RequestParam(value="sender", required=true)  String sender,
        @ApiParam(value = "attitude for the topic.", required=true ) @RequestParam(value="status", required=true)  String status,
        @ApiParam(value = "source of the joining topic.", required=false ) @RequestParam(value="source", required=false)  Integer source,
        @ApiParam(value = "comment of the topic.", required=false ) @RequestParam(value="comment", required=false) String comment);
    
    
    @ApiOperation(value = "add a user for a topic by inviteCode", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/join",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> addUserForTopicByCode(@NotNull @ApiParam(value = "The invite code which joiner reponse", required=true ) @RequestParam(value="inviteCode", required=true)  String inviteCode,
    		                                        @NotNull @ApiParam(value = "attitude for the topic.", required=true ) @RequestParam(value="status", required=true)  String status,
                                                    @ApiParam(value = "source of the joining topic.", required=false ) @RequestParam(value="source", required=false)  Integer source,
                                                    @ApiParam(value = "comment of the topic.", required=false ) @RequestParam(value="comment", required=false) String comment);

    @ApiOperation(value = "Close a topic", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/{topicId}/close",
        produces = {"application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<ResultMsg> closeTopic(@ApiParam(value = "Topic id to close",required=true ) @PathVariable("topicId") Long topicId);


    @ApiOperation(value = "Finds topics by status", notes = "Multiple status values can be provided with comma separated strings", response = Topic.class, responseContainer = "List", tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Topic.class),
        @ApiResponse(code = 400, message = "Invalid status value", response = Topic.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Topic.class),
        @ApiResponse(code = 404, message = "Topic not found", response = Topic.class),
        @ApiResponse(code = 405, message = "Validation exception", response = Topic.class) })
    @RequestMapping(value = "/topic/findByStatus",
        produces = {"application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Topic>> findTopicsByStatus( @NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "AVAILABLE, PENDING, CLOSE") @RequestParam(value = "status", required = true) List<String> status);


    @ApiOperation(value = "Finds Topics by tags", notes = "Muliple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.", response = Topic.class, responseContainer = "List", tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Topic.class),
        @ApiResponse(code = 400, message = "Invalid tag value", response = Topic.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Topic.class),
        @ApiResponse(code = 404, message = "Topic not found", response = Topic.class),
        @ApiResponse(code = 405, message = "Validation exception", response = Topic.class) })
    @RequestMapping(value = "/topic/findByTags",
        produces = {"application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Topic>> findTopicsByTags(@NotNull @ApiParam(value = "Tags to filter by", required = true) @RequestParam(value = "tags", required = true) List<String> tags);


    @ApiOperation(value = "Find topic's participator by ID", notes = "Returns a relative chart of users", response = TopicTopologyModel.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = TopicTopologyModel.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = TopicTopologyModel.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = TopicTopologyModel.class),
        @ApiResponse(code = 404, message = "Topic not found", response = TopicTopologyModel.class),
        @ApiResponse(code = 405, message = "Validation exception", response = TopicTopologyModel.class) })
    @RequestMapping(value = "/topic/{topicId}/participator",
        produces = {"application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<TopicTopologyModel> getParticipatorByTopicId(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId);


    @ApiOperation(value = "Find topic by ID", notes = "Returns a single topic", response = Topic.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Topic.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Topic.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Topic.class),
        @ApiResponse(code = 404, message = "Topic not found", response = Topic.class),
        @ApiResponse(code = 405, message = "Validation exception", response = Topic.class) })
    @RequestMapping(value = "/topic/{topicId}",
        produces = {"application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Topic> getTopicById(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId);
    
    @ApiOperation(value = "Find topic by invite code", notes = "Returns a brief topic", response = UserTopicInfo.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = UserTopicInfo.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = UserTopicInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserTopicInfo.class),
        @ApiResponse(code = 404, message = "Topic not found", response = UserTopicInfo.class),
        @ApiResponse(code = 405, message = "Validation exception", response = UserTopicInfo.class) })
    @RequestMapping(value = "/topic/invite/accept",
        produces = {"application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<UserTopicInfo> acceptTopicByInviteCode(@NotNull @ApiParam(value = "Inviting encode code", required = true) @RequestParam(value = "inviteCode", required = true) String inviteCode);

    @ApiOperation(value = "Get joined topic by invite code", notes = "Returns a brief topic", response = UserTopicInfo.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = UserTopicInfo.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = UserTopicInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserTopicInfo.class),
        @ApiResponse(code = 404, message = "Topic not found", response = UserTopicInfo.class),
        @ApiResponse(code = 405, message = "Validation exception", response = UserTopicInfo.class) })
    @RequestMapping(value = "/topic/invite/getJoined",
        produces = {"application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<UserTopicInfo> getJoinedTopicByInviteCode(@NotNull @ApiParam(value = "Inviting encode code", required = true) @RequestParam(value = "inviteCode", required = true) String inviteCode);

    @ApiOperation(value = "Find all topic of login user.", notes = "Returns a login user's topics", response = Topic.class, responseContainer = "Map", tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Topic.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Topic.class),
        @ApiResponse(code = 405, message = "Validation exception", response = Topic.class) })
    @RequestMapping(value = "/topic",
        produces = {"application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,List<UserTopicInfo>>> getAllTopics();


    @ApiOperation(value = "send a topic to other person.", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/{topicId}/invite",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> sendTopicToUser(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
                                              @NotNull @ApiParam(value = "person who receive the topic.", required=true ) @RequestParam(value="receiver", required=true)  String receiver,
                                              @ApiParam(value = "message that will sent to receiver about the topic.", required=false ) @RequestParam(value="message", required=false)  String message);
    
    
    @ApiOperation(value = "join and hand over a topic to other person list.", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/{topicId}/handerOver",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> handOverTopic(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
    		                                @ApiParam(value = "person list who receive the topic inviting.", required=false ) @RequestParam(value="receivers", required=false)  String receivers,
                                            @NotNull @ApiParam(value = "The inviter who send the topic first.", required=true ) @RequestParam(value="sender", required=true)  String sender,
                                            @NotNull @ApiParam(value = "Is return a link or not", required=true ) @RequestParam(value="link", required=true)  Integer link);
    
    @ApiOperation(value = "send a topic to other person list.", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/{topicId}/invite/list",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> sendTopicToUserList(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
                                                  @NotNull @ApiParam(value = "person list who receive the topic inviting.", required=true ) @RequestParam(value="receivers", required=true)  String receivers,
                                                  @ApiParam(value = "message that will sent to receiver about the topic.", required=false ) @RequestParam(value="message", required=false)  String message);
    
    @ApiOperation(value = "send a topic request to other person.", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/{topicId}/request",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> sendTopicRequestToUser(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
                                                     @ApiParam(value = "person who receive the topic.", required=true ) @RequestParam(value="sender", required=true)  String sender);

    
    @ApiOperation(value = "update a topic invite status.", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/{topicId}/invite",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> updateTopicInvite(@NotNull @ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
    		                                    @ApiParam(value = "person who sender the topic inviting.", required=false ) @RequestParam(value="sender", required=false)  String sender,
                                                @ApiParam(value = "person who receive the topic inviting.", required=false ) @RequestParam(value="receiver", required=false)  String receiver,
                                                @ApiParam(value = "status of the topic inviting.", required=true ) @RequestParam(value="status", required=true)  Integer status,
                                                @ApiParam(value = "topology status of the topic inviting.", required=false ) @RequestParam(value="topologyStatus", required=false) String topologyStatus,
                                                @ApiParam(value = "comment of the topic inviting.", required=false ) @RequestParam(value="comment", required=false) String comment);
    
    @ApiOperation(value = "Join a topic again", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/{topologyId}/joinAgain",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> joinTopicAgain(@NotNull @ApiParam(value = "ID of topology the user join.",required=true ) @PathVariable("topologyId") Long topologyId,
                                             @ApiParam(value = "status of the topic inviting.", required=true ) @RequestParam(value="status", required=true)  String status,
                                             @ApiParam(value = "comment of the topic inviting.", required=false ) @RequestParam(value="comment", required=false) String comment);
    

    @ApiOperation(value = "create an inviting info for a topic.", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/{topicId}/invite",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ResultMsg> getInviteInfoOfTopic(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId);
    
    
    @ApiOperation(value = "Update an existing topic", notes = "", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> updateTopic(@ApiParam(value = "Topic object that needs to be added to the store" ,required=true ) @RequestBody Topic topic);


    @ApiOperation(value = "uploads a file", notes = "", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/{topicId}/uploadfile",
        produces = { "application/json" }, 
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> uploadFile(@ApiParam(value = "ID of topic to update",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "file detail") @RequestPart("file") MultipartFile img,
        @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file);
    
    
    @ApiOperation(value = "Get all the topic category of login user.", notes = "This can only be done by the logged in user.", response = TopicCategory.class, responseContainer = "List", tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = TopicCategory.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = TopicCategory.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = TopicCategory.class),
        @ApiResponse(code = 404, message = "Topic not found", response = TopicCategory.class),
        @ApiResponse(code = 405, message = "Validation exception", response = TopicCategory.class) })
    @RequestMapping(value = "/topic/category",
        produces = {"application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<TopicCategory>> getAllUserTopicCategory();
    
    @ApiOperation(value = "update user's topic category", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/category",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> createUserTopicCategory(@ApiParam(value = "Topic category object that needs to be added to the store for user" ,required=true ) @RequestBody TopicCategory tc);

    
    
    @ApiOperation(value = "Get all the topic attitudes.", notes = "This can only be done by the logged in user.", response = TopicAttitude.class, responseContainer = "List", tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = TopicAttitude.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = TopicAttitude.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = TopicAttitude.class),
        @ApiResponse(code = 404, message = "Topic not found", response = TopicAttitude.class),
        @ApiResponse(code = 405, message = "Validation exception", response = TopicAttitude.class) })
    @RequestMapping(value = "/topic/attitudes",
        produces = {"application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<TopicAttitude>> getAllTopicAttitude();
    
    
    @ApiOperation(value = "User Join info of a topic.", notes = "This can only be done by the logged in user.", response = UserTopicInfo.class, responseContainer = "List", tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = UserTopicInfo.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = UserTopicInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserTopicInfo.class),
        @ApiResponse(code = 404, message = "Topic not found", response = UserTopicInfo.class),
        @ApiResponse(code = 405, message = "Validation exception", response = UserTopicInfo.class) })
    @RequestMapping(value = "/topic/{topicId}/joinInfo",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.GET)
    ResponseEntity<List<UserTopicInfo>> findTopicUserJoinInfo(@NotNull @ApiParam(value = "Topic ID which the user join.",required=true ) @PathVariable("topicId") Long topicId,
                                                              @ApiParam(value = "userId of joined user in the topic.", required=false ) @RequestParam(value="receiver", required=false) String receiver);
    
    @ApiOperation(value = "Join a topic again", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/topic/{topologyId}/selection",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> updateSelectionForJoiner(@NotNull @ApiParam(value = "ID of topology the user join.",required=true ) @PathVariable("topologyId") Long topologyId,
    		                                           @NotNull @ApiParam(value = "give the selection choice for the joiner", required=true ) @RequestParam(value="selection", required=true)  Integer selection,
    		                                           @NotNull @ApiParam(value = "userId of joined user in the topic.", required=true ) @RequestParam(value="receiver", required=true) String receiver);
}
