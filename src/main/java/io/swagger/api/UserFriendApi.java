package io.swagger.api;

import io.swagger.model.ResultMsg;
import io.swagger.model.Topic;
import io.swagger.model.User;
import io.swagger.model.UserFriend;
import io.swagger.model.UserFriendInfo;
import io.swagger.model.UserFriendRequest;
import io.swagger.model.UserLableMap;
import io.swagger.model.UserSearchInfo;

import java.util.Map;

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

import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-031T03:12:51.178Z")

@Api(value = "friend", description = "the friend API")
public interface UserFriendApi {
	
    @ApiOperation(value = "Get user lable map ", notes = "get all lables that provided by huju back-end.", response = UserLableMap.class, responseContainer = "List", tags={ "friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = UserLableMap.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserLableMap.class),
        @ApiResponse(code = 404, message = "friend user not found", response = UserLableMap.class) })
    @RequestMapping(value = "/friend/{lableType}/list",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserLableMap>> getLableMaps(@ApiParam(value = "lable types for searching( 1:lableRelationship; 2:lableImpression )",required=true ) @PathVariable("lableType") Integer lableType);
	
    @ApiOperation(value = "Update info for a user's friend", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "friend user not found", response = ResultMsg.class) })
    @RequestMapping(value = "/friend/{friendId}/update",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> updateFriendForUser(@ApiParam(value = "friend's userid for adding",required=true ) @PathVariable("friendId") String friendId,
        @ApiParam(value = "Updated user friend's info" ,required=true ) @RequestBody UserFriend friend);

    @ApiOperation(value = "Get a user's all friends info", notes = "This can only be done by the logged in user.", response = UserSearchInfo.class, responseContainer = "Map", tags={ "friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = UserSearchInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserSearchInfo.class),
        @ApiResponse(code = 404, message = "friend user not found", response = UserSearchInfo.class) })
    @RequestMapping(value = "/friend/list",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,Map<String,List<UserSearchInfo>>>> getFriendsForUser();
    
    @ApiOperation(value = "Get the common friends with user's one friend", notes = "This can only be done by the logged in user.", response = UserSearchInfo.class, responseContainer = "List", tags={ "friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = UserSearchInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserSearchInfo.class),
        @ApiResponse(code = 404, message = "friend user not found", response = UserSearchInfo.class) })
    @RequestMapping(value = "/friend/{friendId}/common",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserSearchInfo>> getCommonFriendsForUser(@ApiParam(value = "friend's userid for searching",required=true ) @PathVariable("friendId") String friendId);
    
    @ApiOperation(value = "give the number of common friends with user's one friend", notes = "This can only be done by the logged in user.", response = Integer.class, responseContainer = "Map", tags={ "friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = Integer.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Integer.class),
        @ApiResponse(code = 404, message = "friend user not found", response = Integer.class) })
    @RequestMapping(value = "/friend/{friendId}/common/num",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,Integer>> getNumCommonFriendsForUser(@NotNull @ApiParam(value = "friend's userid for searching",required=true ) @PathVariable("friendId") String friendId);
    
    @ApiOperation(value = "give the number of friends for user", notes = "This can only be done by the logged in user.", response = Integer.class, responseContainer = "Map", tags={ "friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = Integer.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Integer.class),
        @ApiResponse(code = 404, message = "friend user not found", response = Integer.class) })
    @RequestMapping(value = "/friend/number",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,Integer>> getNumberFriendsForUser();
    
    @ApiOperation(value = "search a user's friend info by userid", notes = "This can only be done by the logged in user.", response = UserSearchInfo.class, responseContainer = "List", tags={ "friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = UserSearchInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserSearchInfo.class),
        @ApiResponse(code = 404, message = "friend user not found", response = UserSearchInfo.class) })
    @RequestMapping(value = "/friend/search",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserSearchInfo>> searchFriendForUser(@NotNull @ApiParam(value = "userid for searching", required = true) @RequestParam(value = "keyWord", required = true)String keyWord);

    
    @ApiOperation(value = "search a user's friend info by userid and output a map format", notes = "This can only be done by the logged in user.", response = UserSearchInfo.class, responseContainer = "Map", tags={ "friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = UserSearchInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserSearchInfo.class),
        @ApiResponse(code = 404, message = "friend user not found", response = UserSearchInfo.class) })
    @RequestMapping(value = "/friend/search/map",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,Map<String,List<UserSearchInfo>>>> searchFriendMapForUser(@NotNull @ApiParam(value = "userid for searching", required = true) @RequestParam(value = "keyWord", required = true)String keyWord);
    
    @ApiOperation(value = "Get one friend of user", notes = "This can only be done by the logged in user.", response = UserFriendInfo.class, tags={ "friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = UserFriendInfo.class),
        @ApiResponse(code = 400, message = "Invalid username supplied", response = UserFriendInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserFriendInfo.class),
        @ApiResponse(code = 404, message = "User not found", response = UserFriendInfo.class) })
    @RequestMapping(value = "/friend/{friendId}",
        produces = {"application/json"}, 
        method = RequestMethod.GET)
    ResponseEntity<UserFriendInfo> getFriendById(@ApiParam(value = "Get one friend of loging user",required=true ) @PathVariable("friendId") String friendId);
     
    @ApiOperation(value = "Get all my favorite friends", notes = "This can only be done by the logged in user.", response = UserSearchInfo.class, responseContainer = "List",  tags={ "friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = UserSearchInfo.class),
        @ApiResponse(code = 404, message = "favorite friends are not found", response = UserSearchInfo.class) })
    	@RequestMapping(value = "/friend/favoritelist",
        produces = {"application/json"}, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserSearchInfo>> getMyFavotiteFriends();
    
    @ApiOperation(value = "set favorite status to one of my friend(isFavorite=0,normal friend;isFavorite=1,my favorite friend)", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "friend", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "friend user not found", response = ResultMsg.class) })
    @RequestMapping(value = "/friend/{friendId}/favorite",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> setFavoriteStatus(@ApiParam(value = "friend's userid",required=true ) @PathVariable("friendId") String friendId,
        @ApiParam(value = "favorite status(isFavorite=0,normal friend;isFavorite=1,my favorite friend)" ,required=true ) @RequestParam(value = "isFavorite", required = true) Integer isFavorite);
}
