package io.swagger.api;

import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.exception.CommonException;
import io.swagger.exception.NotFoundException;
import io.swagger.model.InfoExplorable;
import io.swagger.model.ResultMsg;
import io.swagger.model.UserExploreInfo;
import io.swagger.model.UserFriend;
import io.swagger.model.UserFriendInfo;
import io.swagger.model.UserFriendRequest;
import io.swagger.model.UserLableMap;
import io.swagger.model.UserSearchInfo;
import io.swagger.service.InfoExploreService;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")


@RestController
public class InfoExploreController implements InfoExploreApi{
	
	//private Logger logger = LoggerFactory.getLogger(InfoExploreController.class);

	@Autowired
	private InfoExploreService infoExploreService;
	
	
	@Override
	public ResponseEntity<ResultMsg> requestToShare(@ApiParam(value = "isRequest= 0 or 1",required=true ) @PathVariable("isRequest") Integer isRequest,@ApiParam(value = "friends I request for.." ,required=true ) @RequestParam(value = "friendIds", required = true) String friendIds){
		ResultMsg response = null;
		
		infoExploreService.requestToShare(isRequest,friendIds);
		response = new ResultMsg(1,	"requestToShare for exploration success");
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);		
	}
	
	@Override
	public ResponseEntity<ResultMsg> agreeToShare(@ApiParam(value = "isAgree= 0 or 1",required=true ) @PathVariable("isAgree") Integer isAgree,@NotNull @ApiParam(value = "the friend I'm going to agree with", required = true) @RequestParam(value = "friendId", required = true)String friendId){
		ResultMsg response = null;
		
		boolean result = infoExploreService.agreeToShare(isAgree,friendId);
		if(result){
        	response = new ResultMsg(1,"agree to Share for exploration successfully.");
        }else{
        	response = new ResultMsg(2,"Fail to agree Sharing for exploration.");
        }
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);		
	}
	
	@Override
	public ResponseEntity<ResultMsg> createExplorableInfo(@ApiParam(value = "user infomation for exploration object" ,required=true ) @RequestBody InfoExplorable infoExplorable){
		ResultMsg response = null;
		
		infoExploreService.createExplorationInfo(infoExplorable);
		response = new ResultMsg(1,	"creating infomation for exploration success");
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResultMsg> deleteExplorableInfo(){		
		ResultMsg response = null;
		
		infoExploreService.deleteExplorationInfo();
		response = new ResultMsg(1,	"deleting infomation for exploration success");
		return new ResponseEntity<ResultMsg>(response, HttpStatus.OK);
	}
	
	/*@Override
	public ResponseEntity<List<Map<String,Object>>> getMyFriend_Infos(){
		
		List<Map<String,Object>> infoResult = infoExploreService.getMyFriend_Infos();
		return new ResponseEntity<List<Map<String,Object>>>(infoResult,HttpStatus.OK);		
	}
	
	@Override
	public ResponseEntity<List<Map<String,Object>>> getMyFriends(){
		
		List<Map<String,Object>> infoResult = infoExploreService.getMyFriends();
		return new ResponseEntity<List<Map<String,Object>>>(infoResult,HttpStatus.OK);		
	}
	
	@Override
	public ResponseEntity<List<Map<String,Object>>> exploreAUser_Infos(@ApiParam(value = "userId,the user to be explored",required=true ) @PathVariable("userId") String userId){
		
		List<Map<String,Object>> infoResult = infoExploreService.exploreAUser_Infos(userId);
		return new ResponseEntity<List<Map<String,Object>>>(infoResult,HttpStatus.OK);		
	}
	
	@Override
	public ResponseEntity<List<Map<String,Object>>> exploreAUser_Friends(@ApiParam(value = "userId,the user to be explored",required=true ) @PathVariable("userId") String userId){
		
		List<Map<String,Object>> infoResult = infoExploreService.exploreAUser_Friends(userId);
		return new ResponseEntity<List<Map<String,Object>>>(infoResult,HttpStatus.OK);		
	}*/

	@Override
	public ResponseEntity<List<UserExploreInfo>> exploreUserFreindInfos(@NotNull @ApiParam(value = "userId,the user to be explored",required=true ) @PathVariable("userId") String userId,@RequestParam(value = "isAll", required = false,defaultValue = "0")Integer isAll) {
		List<UserExploreInfo> infoResult = infoExploreService.exploreUserFreindInfos(userId, isAll);
		return new ResponseEntity<List<UserExploreInfo>>(infoResult,HttpStatus.OK);	
	}

	@Override
	public ResponseEntity<List<UserExploreInfo>> getShareFriendInfo(@NotNull @ApiParam(value = "userId,the user to be explored",required=true ) @PathVariable("userId") String userId,@RequestParam(value = "isAll", required = false,defaultValue = "0")Integer isAll) {
		List<UserExploreInfo> infoResult = infoExploreService.getShareFriendInfoByUserId(userId, isAll);
		return new ResponseEntity<List<UserExploreInfo>>(infoResult,HttpStatus.OK);		
	}

	@Override
	public ResponseEntity<ResultMsg> createExplorableInfoByParam(@ApiParam(value = "title of info", required = false) @RequestParam(value = "infoTitle", required = false)String infoTitle,
                                                                 @NotNull @ApiParam(value = "content of info", required = true) @RequestParam(value = "infoContent", required = true)String infoContent,
                                                                 @ApiParam(value = "addition of info", required = false) @RequestParam(value = "infoAddition", required = false)String infoAddition,
                                                                 @ApiParam(value = "picture files of info") @RequestParam("postImages") List<MultipartFile> postImages) {
        ResultMsg response = null;
		try {
			infoExploreService.createExplorationInfo(infoTitle,infoContent,infoAddition,postImages);
		} catch (CommonException e) {
			response = new ResultMsg(e.getCode(),e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
		}
		response = new ResultMsg(1,	"creating infomation for exploration success");
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResultMsg> createExplorableInfoWithNoPicByParam(@ApiParam(value = "title of info", required = false) @RequestParam(value = "infoTitle", required = false)String infoTitle,
                                                                          @NotNull @ApiParam(value = "content of info", required = true) @RequestParam(value = "infoContent", required = true)String infoContent,
                                                                          @ApiParam(value = "addition of info", required = false) @RequestParam(value = "infoAddition", required = false)String infoAddition) {
		ResultMsg response = null;
		try {
			infoExploreService.createExplorationInfo(infoTitle,infoContent,infoAddition,new ArrayList<MultipartFile>());
		} catch (CommonException e) {
			response = new ResultMsg(e.getCode(),e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
		}
		response = new ResultMsg(1,	"creating infomation for exploration success");
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResultMsg> giveLaudForExploreInfo(@NotNull @ApiParam(value = "user's info id who you want to give laud", required = true) @PathVariable("infoId") Long infoId) {
		boolean result = infoExploreService.giveLaudForExploreInfo(infoId);
		ResultMsg response = null;
		if(result){
			 response = new ResultMsg(1,"gvie user laud successfully.");
		}else{
			 response = new ResultMsg(405,"you have already given the laud.");
		}
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Integer>> getLaudStatusForExploreInfo(@NotNull @ApiParam(value = "explore of laud", required = true) @PathVariable("infoId") Long infoId) {
		Map<String, Integer> result = infoExploreService.getLaudStatusForExploreInfo(infoId);
		return new ResponseEntity<Map<String, Integer>>(result,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResultMsg> removeLaudForExploreInfo(@NotNull @ApiParam(value = "explore info id who you want to remove the laud", required = true) @PathVariable("infoId") Long infoId) {
		boolean result = infoExploreService.removeLaudForExploreInfo(infoId);
		ResultMsg response = null;
		if(result){
			 response = new ResultMsg(1,"remove user laud successfully.");
		}else{
			 response = new ResultMsg(405,"you have already removed the laud.");
		}
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserSearchInfo>> getAllLaudUsersForExploreInfo(@NotNull @ApiParam(value = "explore of laud", required = true) @PathVariable("infoId") Long infoId) {
		List<UserSearchInfo> laudUsers = infoExploreService.getAllLaudUsersForExploreInfo(infoId);
		return new ResponseEntity<List<UserSearchInfo>>(laudUsers,HttpStatus.OK);	
	}
	
	@Override
	public ResponseEntity<UserExploreInfo> getMyExploreInfo(){
		UserExploreInfo info = null;
		info = infoExploreService.getMyExploreInfo();
		
		return new ResponseEntity<UserExploreInfo>(info,HttpStatus.OK);	
	}

	
	/*@Override
	public ResponseEntity<List<UserFriend>> getMyRquestedFriends(){
		List<UserFriend> result = null;
		result = infoExploreService.getMyRquestedFriends();
		
		return new ResponseEntity<List<UserFriend>>(result,HttpStatus.OK);	
	}*/
	
		
}
