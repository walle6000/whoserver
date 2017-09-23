package io.swagger.api;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import io.swagger.exception.NotFoundException;
import io.swagger.model.ResultMsg;
import io.swagger.model.UserFriend;
import io.swagger.model.UserFriendRequest;
import io.swagger.service.UserFriendRequestService;
import io.swagger.service.UserFriendService;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

@RestController
public class UserFriendRequestApiController implements UserFriendRequestApi {
	
	private Logger logger = LoggerFactory.getLogger(UserFriendRequestApiController.class);

	@Autowired
	private UserFriendRequestService userFriendRequestService;

	@Override
	public ResponseEntity<ResultMsg> requestFriendForUser(@ApiParam(value = "friend's userid for adding",required=true ) @PathVariable("friendId") String friendId,
	        @ApiParam(value = "Send the request verify message to the friend" ,required=false ) @RequestParam(value="requestContent",required=false) String requestContent,
	        @ApiParam(value = "is share my friend,0:no,1;yes" ,required=false ) @RequestParam(value="shareFriend",required=false) Integer shareFriend,
	        @ApiParam(value = "The nickname you call your friend" ,required=false ) @RequestParam(value="nickName",required=false) String nickName,
	        @ApiParam(value = "Tags for your friend:labelRelationship" ,required=false ) @RequestParam(value="labelRelationship",required=false) String labelRelationship,
	        @ApiParam(value = "Tags for your friend:labelImpression" ,required=false ) @RequestParam(value="labelImpression",required=false) String labelImpression,
	        @ApiParam(value = "The description for your friend" ,required=false ) @RequestParam(value="description",required=false) String description,
	        @ApiParam(value = "The original source of your" ,required=false ) @RequestParam(value="friendOrigin",required=false) Integer friendOrigin) {
		ResultMsg response = null;
		try {
			userFriendRequestService.createFriendRequestForUser(friendId, requestContent, shareFriend, nickName, labelRelationship, labelImpression, description,friendOrigin);
			response = new ResultMsg(1,"sent frien request successfully.");
			logger.info("UserFriendRequestApiController - requestFriendForUser sent frien request successfully for freindId:" + friendId);
		} catch (NotFoundException e) {
			response = new ResultMsg(e.getCode(),e.getMessage());
			logger.error("Something wrong for send request to " + friendId + ",message is:"+e.getMessage());
		}
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String,List<UserFriendRequest>>> getFriendRequests() {
		logger.info("UserFriendApiController getFriendRequests...");
		Map<String,List<UserFriendRequest>> userFriendRequestList = userFriendRequestService.getAllUserFriendRequestMap();
		return new ResponseEntity<Map<String,List<UserFriendRequest>>>(userFriendRequestList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResultMsg> updateRequestForUser(@ApiParam(value = "friend's request id for updating",required=true ) @PathVariable("requestId") Long requestId,
	        @ApiParam(value = "Updated user friend's request" ,required=true ) @RequestBody UserFriendRequest userFriendRequest) {
		logger.info("UserFriendApiController updateRequestForUser...");
		userFriendRequestService.updateUserFriendRequestStatus(requestId, userFriendRequest);
		ResultMsg response = new ResultMsg(1,"Update friend request status successfully.");
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

}
