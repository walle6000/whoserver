package io.swagger.api;

import io.swagger.model.ResultMsg;
import io.swagger.model.Topic;
import io.swagger.model.User;
import io.swagger.model.UserFriend;
import io.swagger.model.UserFriendRequest;

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

@Api(value = "friendRequest", description = "the friend API")
public interface UserFriendRequestApi {
	
	@ApiOperation(value = "Create a user's friend request", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "friendRequest", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Login user not found", response = ResultMsg.class) })
    @RequestMapping(value = "/friend/{friendId}/create",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> requestFriendForUser(@ApiParam(value = "friend's userid for adding",required=true ) @PathVariable("friendId") String friendId,
        @ApiParam(value = "Send the request verify message to the friend" ,required=false ) @RequestParam(value="requestContent",required=false) String requestContent,
        @ApiParam(value = "is share my friend,0:no,1;yes" ,required=false ) @RequestParam(value="shareFriend",required=false) Integer shareFriend,
        @ApiParam(value = "The nickname you call your friend" ,required=false ) @RequestParam(value="nickName",required=false) String nickName,
        @ApiParam(value = "Tags for your friend:labelRelationship" ,required=false ) @RequestParam(value="labelRelationship",required=false) String labelRelationship,
        @ApiParam(value = "Tags for your friend:labelImpression" ,required=false ) @RequestParam(value="labelImpression",required=false) String labelImpression,
        @ApiParam(value = "The description for your friend" ,required=false ) @RequestParam(value="description",required=false) String description,
        @ApiParam(value = "The original source of your" ,required=false ) @RequestParam(value="friendOrigin",required=false) Integer friendOrigin);
	
	@ApiOperation(value = "get user's friend request", notes = "This can only be done by the logged in user.", response = UserFriendRequest.class,responseContainer = "Map", tags={ "friendRequest", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = UserFriendRequest.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserFriendRequest.class),
        @ApiResponse(code = 405, message = "Login user not found", response = UserFriendRequest.class) })
    @RequestMapping(value = "/friend/requests",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,List<UserFriendRequest>>> getFriendRequests();
	
	@ApiOperation(value = "Update a user's friend request", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "friendRequest", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "friend user not found", response = ResultMsg.class) })
    @RequestMapping(value = "/friend/{requestId}/request",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> updateRequestForUser(@ApiParam(value = "friend's request id for updating",required=true ) @PathVariable("requestId") Long requestId,
        @ApiParam(value = "Updated user friend's request" ,required=true ) @RequestBody UserFriendRequest userFriendRequest);

}
