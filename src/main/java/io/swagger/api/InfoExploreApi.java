package io.swagger.api;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.InfoExplorable;
import io.swagger.model.ResultMsg;
import io.swagger.model.UserExploreInfo;
import io.swagger.model.UserSearchInfo;
import io.swagger.model.UserFriend;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-29T03:12:51.178Z")

//*key param:
//share_friend(0: no requestToShare; 1: requestToShare; 2:agreed)

public interface InfoExploreApi {	
	
	//requestToShare (param isRequest= 0 or 1) result: isRequest=0 share_friend=0 ； isRequest=1，share_friend from 0 to 1
	@ApiOperation(value = "request to friends for sharing my infomation", notes = "This is the request for friend exploration.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/share/{isRequest}/request",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
	ResponseEntity<ResultMsg> requestToShare(@ApiParam(value = "isRequest= 0 or 1",required=true ) @PathVariable("isRequest") Integer isRequest,@ApiParam(value = "friends I request for.." ,required=true ) @RequestParam(value = "friendIds", required = true) String friendIds);
	
	//agreeToShare  (param isAgree= 0 or 1) result: isAgree=1 share_friend=2; isAgree=0,share_friend share_friend from 2 to 1
	@ApiOperation(value = "agree to a friend for sharing his/her infomation", notes = "This is the agreement for friend exploration.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/share/{isAgree}/agree",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
	ResponseEntity<ResultMsg> agreeToShare(@ApiParam(value = "isAgree= 0 or 1",required=true ) @PathVariable("isAgree") Integer isAgree,@NotNull @ApiParam(value = "the friend I'm going to agree with", required = true) @RequestParam(value = "friendId", required = true)String friendId);
	
	//create user info for exploration(param: InfoExplorable)
	@ApiOperation(value = "create user info for exploration", notes = "This is for the base infomation for friend exploration.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/info/create",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
	ResponseEntity<ResultMsg> createExplorableInfo(@ApiParam(value = "user infomation for exploration object" ,required=true ) @RequestBody InfoExplorable infoExplorable);
	
	@ApiOperation(value = "create user info for exploration", notes = "This is for the base infomation for friend exploration.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/paraInfo/create",
    		produces = { "application/json" }, 
            consumes = { "multipart/form-data" },
            method = RequestMethod.POST)
	ResponseEntity<ResultMsg> createExplorableInfoByParam(@ApiParam(value = "title of info", required = false) @RequestParam(value = "infoTitle", required = false)String infoTitle,
			                                              @NotNull @ApiParam(value = "content of info", required = true) @RequestParam(value = "infoContent", required = true)String infoContent,
			                                              @ApiParam(value = "addition of info", required = false) @RequestParam(value = "infoAddition", required = false)String infoAddition,
	                                                      @ApiParam(value = "picture files of info") @RequestParam("postImages") List<MultipartFile> postImages);
	
	@ApiOperation(value = "create user info with no pics for exploration", notes = "This is for the base infomation for friend exploration.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/paraInfo/create/noPic",
    		produces = { "application/json" }, 
            consumes = { "application/x-www-form-urlencoded" },
            method = RequestMethod.POST)
	ResponseEntity<ResultMsg> createExplorableInfoWithNoPicByParam(@ApiParam(value = "title of info", required = false) @RequestParam(value = "infoTitle", required = false)String infoTitle,
			                                                       @NotNull @ApiParam(value = "content of info", required = true) @RequestParam(value = "infoContent", required = true)String infoContent,
			                                                       @ApiParam(value = "addition of info", required = false) @RequestParam(value = "infoAddition", required = false)String infoAddition);
	
	//delete user info for exploration(param: userid)
	@ApiOperation(value = "delete user info for exploration", notes = "This is for the base infomation for friend exploration.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/info/delete",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
	ResponseEntity<ResultMsg> deleteExplorableInfo();
	

	/*@ApiOperation(value = "get my friends and informations for exploration,return list of informations", notes = "This is for the base infomation for friend exploration.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/getMyFriendInfos",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
	ResponseEntity<List<Map<String,Object>>> getMyFriend_Infos();
	
	@ApiOperation(value = "get my friends for exploration,return list of friends", notes = "This is for the base infomation for friend exploration.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/getMyFriends",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
	ResponseEntity<List<Map<String,Object>>> getMyFriends();
	
	//Explore sharing friends--get sharing informations  (param: userid) (return: list of info)
	@ApiOperation(value = "Explore sharing friends,return list of sharing informations", notes = "This is for the base infomation for friend exploration.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/{userId}/getInfos",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
	ResponseEntity<List<Map<String,Object>>> exploreAUser_Infos(@NotNull @ApiParam(value = "userId,the user to be explored",required=true ) @PathVariable("userId") String userId);
	
	//Explore sharing friends--get agreed friends  (param: userid) (return: list of friends)
	@ApiOperation(value = "Explore sharing friends,return list of agreed friends", notes = "This is for the base infomation for friend exploration.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/{userId}/getUsers",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
	ResponseEntity<List<Map<String,Object>>> exploreAUser_Friends(@NotNull @ApiParam(value = "userId,the user to be explored",required=true ) @PathVariable("userId") String userId);*/
	
	@ApiOperation(value = "Explore sharing friends,return list of agreed friends", notes = "This is for the base infomation for friend exploration.", response = UserExploreInfo.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = UserExploreInfo.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = UserExploreInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserExploreInfo.class),
        @ApiResponse(code = 405, message = "invalid exception", response = UserExploreInfo.class) })
    @RequestMapping(value = "/explore/{userId}/briefList",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
	ResponseEntity<List<UserExploreInfo>> exploreUserFreindInfos(@NotNull @ApiParam(value = "userId,the user to be explored",required=true ) @PathVariable("userId") String userId,@RequestParam(value = "isAll", required = false,defaultValue = "0")Integer isAll);
	
	@ApiOperation(value = "get my friends and informations for exploration,return list of informations", notes = "This is for the base infomation for friend exploration.", response = UserExploreInfo.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = UserExploreInfo.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = UserExploreInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserExploreInfo.class),
        @ApiResponse(code = 405, message = "invalid exception", response = UserExploreInfo.class) })
    @RequestMapping(value = "/explore/{userId}/infoList",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
	ResponseEntity<List<UserExploreInfo>> getShareFriendInfo(@NotNull @ApiParam(value = "userId,the user to be explored",required=true ) @PathVariable("userId") String userId,@RequestParam(value = "isAll", required = false,defaultValue = "0")Integer isAll);
	
	@ApiOperation(value = "give a Laud For a User's explore info", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "friend user not found", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/{infoId}/laud",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> giveLaudForExploreInfo(@NotNull @ApiParam(value = "user's info id who you want to give laud", required = true) @PathVariable("infoId") Long infoId);
    
    
    @ApiOperation(value = "get the status of Laud For a User's info", notes = "This can only be done by the logged in user.", response = Integer.class, responseContainer = "Map", tags={ "infoExploration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = Integer.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Integer.class),
        @ApiResponse(code = 404, message = "friend user not found", response = Integer.class) })
    @RequestMapping(value = "/explore/{infoId}/laud/status",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,Integer>> getLaudStatusForExploreInfo(@NotNull @ApiParam(value = "explore of laud", required = true) @PathVariable("infoId") Long infoId);
    
    @ApiOperation(value = "remove a Laud For a User's explore info", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "friend user not found", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/{infoId}/laud",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<ResultMsg> removeLaudForExploreInfo(@NotNull @ApiParam(value = "explore info id who you want to remove the laud", required = true) @PathVariable("infoId") Long infoId);

    
    @ApiOperation(value = "get all the User who give the laud for the info", notes = "This can only be done by the logged in user.", response = UserSearchInfo.class, responseContainer = "List", tags={ "infoExploration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = UserSearchInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserSearchInfo.class),
        @ApiResponse(code = 404, message = "friend user not found", response = UserSearchInfo.class) })
    @RequestMapping(value = "/explore/{infoId}/laud/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserSearchInfo>> getAllLaudUsersForExploreInfo(@NotNull @ApiParam(value = "explore of laud", required = true) @PathVariable("infoId") Long infoId);
    
    
    @ApiOperation(value = "get my explorable information", notes = "This is for the base infomation for friend exploration.", response = UserExploreInfo.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = UserSearchInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserSearchInfo.class),
        @ApiResponse(code = 404, message = "friend user not found", response = UserSearchInfo.class) })
    @RequestMapping(value = "/explore/info/get",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<UserExploreInfo> getMyExploreInfo();
    
    /*@ApiOperation(value = "get my friends and request status for my explorable information", notes = "This is for the base infomation for friend exploration.", response = UserFriend.class, responseContainer = "List",tags={ "infoExploration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = UserSearchInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserSearchInfo.class),
        @ApiResponse(code = 404, message = "friend user not found", response = UserSearchInfo.class) })
    @RequestMapping(value = "/explore/friends/get",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserFriend>> getMyRquestedFriends();*/
}
