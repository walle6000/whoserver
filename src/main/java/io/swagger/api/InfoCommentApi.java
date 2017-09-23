package io.swagger.api;

import io.swagger.model.Comment;
import io.swagger.model.InfoExplorable;
import io.swagger.model.ResultMsg;
import io.swagger.model.User;
import io.swagger.model.UserCommentInfo;
import io.swagger.model.UserFriend;
import io.swagger.model.UserProfile;
import io.swagger.model.UserSearchInfo;
import io.swagger.model.UserSearchLog;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-29T03:12:51.178Z")


public interface InfoCommentApi {	
	
	//post a comment
	@ApiOperation(value = "post a comment", notes = "This is the request for friend exploration.", response = ResultMsg.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/explore/info/comment/{infoId}/post",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
	ResponseEntity<ResultMsg> postAComment(@NotNull @ApiParam(value = "The id of a user's explorable information",required=true ) @PathVariable("infoId") Long infoId,@ApiParam(value = "my comment" ) @RequestBody Comment comment);
	
	// thumb up for a comment
	@ApiOperation(value = "thumb up for a comment", notes = "This is the request for friend exploration.", response = ResultMsg.class, tags = { "infoExploration", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
			@ApiResponse(code = 400, message = "Invalid info supplied", response = ResultMsg.class),
			@ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
			@ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
	@RequestMapping(value = "/explore/info/comment/{commentId}/thumbup", 
		produces = { "application/json" }, 
		method = RequestMethod.PUT)
	ResponseEntity<ResultMsg> thumbupForAComment(@NotNull @ApiParam(value = "comment id", required = true) @PathVariable("commentId") Long commentId);


	//get all comments for an user's explorable information
	@ApiOperation(value = "get all comments", notes = "This is the request for friend exploration.", response = UserCommentInfo.class, tags={ "infoExploration", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = UserCommentInfo.class),
        @ApiResponse(code = 400, message = "Invalid info supplied", response = UserCommentInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserCommentInfo.class),
        @ApiResponse(code = 405, message = "invalid exception", response = UserCommentInfo.class) })
    @RequestMapping(value = "/explore/info/comment/{infoId}/getAll",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
	ResponseEntity<List<UserCommentInfo>> getAllComments(@NotNull @ApiParam(value = "The id of a user's explorable information",required=true ) @PathVariable("infoId") Long infoId);

	
}
