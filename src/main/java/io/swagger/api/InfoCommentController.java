package io.swagger.api;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.exception.NotFoundException;
import io.swagger.model.Comment;
import io.swagger.model.ResultMsg;
import io.swagger.model.UserCommentInfo;
import io.swagger.model.UserFriend;
import io.swagger.model.UserFriendInfo;
import io.swagger.model.UserFriendRequest;
import io.swagger.model.UserLableMap;
import io.swagger.model.UserSearchInfo;
import io.swagger.service.CommentService;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")


@RestController
public class InfoCommentController implements InfoCommentApi{
	
	//private Logger logger = LoggerFactory.getLogger(InfoExploreController.class);

	@Autowired
	private CommentService commentService;
	
	
	@Override
	public ResponseEntity<ResultMsg> postAComment(@NotNull @ApiParam(value = "The id of a user's explorable information",required=true ) @PathVariable("infoId") Long infoId,@ApiParam(value = "my comment" ) @RequestBody Comment comment){
		ResultMsg response = null;
		
		comment.setInfoId(infoId);
		commentService.createAComment(comment);
		response = new ResultMsg(1,	"postAComment for exploration success");
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);		
	}
	
	@Override
	public ResponseEntity<ResultMsg> thumbupForAComment(@NotNull @ApiParam(value = "comment id", required = true) @PathVariable("commentId") Long commentId){
		ResultMsg response = null;
		
		commentService.Thumbup(commentId);
		response = new ResultMsg(1,	"thumbup For A Comment success");
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);		
	}
	
	@Override
	public ResponseEntity<List<UserCommentInfo>> getAllComments(@NotNull @ApiParam(value = "The id of a user's explorable information",required=true ) @PathVariable("infoId") Long infoId){
		List<UserCommentInfo> response = null;
		
		response = commentService.getAllComments(infoId);
		return new ResponseEntity<List<UserCommentInfo>>(response,HttpStatus.OK);		
	}
	
}
	
