package io.swagger.api;

import java.util.HashMap;
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
import io.swagger.model.ResultMsg;
import io.swagger.model.UserFriend;
import io.swagger.model.UserFriendInfo;
import io.swagger.model.UserFriendRequest;
import io.swagger.model.UserLableMap;
import io.swagger.model.UserSearchInfo;
import io.swagger.service.UserFriendService;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

@RestController
public class UserFriendApiController implements UserFriendApi {
	
	private Logger logger = LoggerFactory.getLogger(UserFriendApiController.class);

	@Autowired
	private UserFriendService userFriendService;

	@Override
	public ResponseEntity<List<UserLableMap>> getLableMaps(@ApiParam(value = "lable types for searching", required = true) @PathVariable("lableType") Integer lableType) {
		List<UserLableMap> lableList = userFriendService.getLableMaps(lableType);
		return new ResponseEntity<List<UserLableMap>>(lableList,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResultMsg> updateFriendForUser(@ApiParam(value = "friend's userid for adding",required=true ) @PathVariable("friendId") String friendId,
	                                                     @ApiParam(value = "Updated user friend's info" ,required=true ) @RequestBody UserFriend friend) {
		ResultMsg response = null;
		try {
			userFriendService.updateFriendInfoForUser(friendId, friend);
			response = new ResultMsg(1,"create user's friend successfully");
		} catch (NotFoundException e) {
			logger.error(e.getMessage());
			response = new ResultMsg(e.getCode(),e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String,Map<String,List<UserSearchInfo>>>> getFriendsForUser() {
		Map<String,Map<String,List<UserSearchInfo>>> userFriendMap = userFriendService.getFriendMapOfUser();
		return new ResponseEntity<Map<String,Map<String,List<UserSearchInfo>>>>(userFriendMap,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserSearchInfo>> searchFriendForUser(@NotNull @ApiParam(value = "userid for searching", required = true) @RequestParam(value = "keyWord", required = true) String keyWord) {
		List<UserSearchInfo> friendList = userFriendService.searchFriendsOfUser(keyWord.trim());
		return new ResponseEntity<List<UserSearchInfo>>(friendList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserFriendInfo> getFriendById(@ApiParam(value = "Get one friend of loging user",required=true ) @PathVariable("friendId")String friendId) {
		UserFriendInfo userFriendinfo = userFriendService.getUserFriendInfoByFriendId(friendId);
		return new ResponseEntity<UserFriendInfo>(userFriendinfo,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<UserSearchInfo>> getMyFavotiteFriends(){
		List<UserSearchInfo> friendList = userFriendService.getMyFavoriteFriends();
		return new ResponseEntity<List<UserSearchInfo>>(friendList,HttpStatus.OK);		
	}

	@Override
	public ResponseEntity<ResultMsg> setFavoriteStatus(@ApiParam(value = "friend's userid",required=true ) @PathVariable("friendId") String friendId,
	        @ApiParam(value = "favorite status(isFavorite=0,normal friend;isFavorite=1,my favorite friend)" ,required=true ) @RequestParam(value = "isFavorite", required = true) Integer isFavorite){
		ResultMsg response = null;
		UserFriend friend = new UserFriend();
		friend.setIsFavorite(isFavorite);
		try {
			userFriendService.updateFriendInfoForUser(friendId, friend);
			response = new ResultMsg(1,"create user's friend successfully");
		} catch (NotFoundException e) {
			logger.error(e.getMessage());
			response = new ResultMsg(e.getCode(),e.getMessage());
			e.printStackTrace();
		}		
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserSearchInfo>> getCommonFriendsForUser(@ApiParam(value = "friend's userid for searching",required=true ) @PathVariable("friendId") String friendId) {
		List<UserSearchInfo> friendList = userFriendService.getCommonFriendsForUser(friendId);
		return new ResponseEntity<List<UserSearchInfo>>(friendList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Integer>> getNumCommonFriendsForUser(String friendId) {
		Map<String, Integer> result = userFriendService.getNumCommonFriendsForUser(friendId);
		return new ResponseEntity<Map<String, Integer>>(result,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Integer>> getNumberFriendsForUser() {
		Map<String, Integer> result = new HashMap<String,Integer>();
		List<UserFriend> friendList = userFriendService.getAllFriendsOfUser();
		result.put("friendNum", friendList.size());
		return new ResponseEntity<Map<String, Integer>>(result,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Map<String, List<UserSearchInfo>>>> searchFriendMapForUser(@NotNull @ApiParam(value = "userid for searching", required = true) @RequestParam(value = "keyWord", required = true) String keyWord) {
		Map<String,Map<String,List<UserSearchInfo>>> userFriendMap = userFriendService.searchFriendMapOfUser(keyWord.trim());
		return new ResponseEntity<Map<String,Map<String,List<UserSearchInfo>>>>(userFriendMap,HttpStatus.OK);
	}
}
