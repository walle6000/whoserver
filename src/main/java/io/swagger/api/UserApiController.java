package io.swagger.api;

import io.swagger.model.AccessToken;
import io.swagger.model.FileUpload;
import io.swagger.model.ResultMsg;
import io.swagger.model.User;
import io.swagger.model.UserFriend;
import io.swagger.model.UserFriendRequest;
import io.swagger.model.UserProfile;
import io.swagger.model.UserSearchInfo;
import io.swagger.model.UserSearchLog;
import io.swagger.service.UserFriendRequestService;
import io.swagger.service.UserFriendService;
import io.swagger.service.UserSearchLogService;
import io.swagger.service.UserService;
import io.swagger.service.common.WebTokenService;
import io.swagger.utils.UserUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.*;
import io.swagger.common.UserStatus;
import io.swagger.exception.CommonException;
import io.swagger.exception.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

@RestController
public class UserApiController implements UserApi {
	
	private Logger logger = LoggerFactory.getLogger(UserApiController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSearchLogService userSearchLogService;
	
	@Autowired
	private WebTokenService webTokenService;
	
	@Autowired  
	private HttpServletRequest request;

	@Autowired  
	private UserFriendRequestService userFriendRequestService;
	/*
	 *   新用户注册
	 * */
    public ResponseEntity<ResultMsg> createUser(@ApiParam(value = "Created user object" ,required=true ) @RequestBody User user) {
    	logger.info("UserApiController - createUser: User is\n" + user);
    	HttpSession session = request.getSession(true); 
        String sessionId = session.getId();
        UserStatus us = userService.checkSaveUser(user, sessionId);
        ResultMsg response = null;
        switch(us){
        case OK:
        	   logger.info("UserApiController - createUser:User checking is successful.");
        	   userService.createNewUser(user);
               logger.info("UserApiController - createUser:createUser is successful.");
               logger.info("UserApiController - createUser:createUser new id is:"+user.getId());
               logger.info("UserApiController - createUser:createUser new userid is:"+user.getUserid());
               logger.info("UserApiController - createUser:createUser new user role is:"+user.getRole());
               logger.info("UserApiController - createUser:createUser get access token.");
               AccessToken accessToken = webTokenService.getAccessToken(user);
               response = new ResultMsg(1,"create user successfully",accessToken);
               break;
        case userIdEmpty:
        	   logger.info("UserApiController - createUser:User checking is fail<userid is empty>.");
               response = new ResultMsg(2,"userid is empty");
               break;
        case userIdExist:
        	   logger.info("UserApiController - createUser:User checking is <userid existed> and return token.");
        	   User currentUser = userService.getUserByUserid(user.getUserid());
        	   logger.info("UserApiController - createUser:userid existed and get access token.");
               AccessToken accessTokenExisted = webTokenService.getAccessToken(currentUser);
        	   response = new ResultMsg(1,"userid existed",accessTokenExisted);
               break;
        case passwordEmpty:
        	   logger.info("UserApiController - createUser:User checking is fail<password is empty>.");
     	       response = new ResultMsg(2,"password is empty");
               break;
        case identityEmpty:
        	   logger.info("UserApiController - createUser:User checking is fail<verify code is empty>.");
  	           response = new ResultMsg(2,"verify code is empty");
               break;
        case identityExpired:
        	   logger.info("UserApiController - createUser:User checking is fail<verify code Expired>.");
	           response = new ResultMsg(2,"verify code Expired");
               break;
        case identityWrong:
        	   logger.info("UserApiController - createUser:User checking is fail<verify code is wrong>.");
	           response = new ResultMsg(2,"verify code is wrong");
               break;
        default:break;
        }
        return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
    }

    
    public ResponseEntity<ResultMsg> deleteUser(@ApiParam(value = "The id that needs to be deleted",required=true ) @PathVariable("userId") String userId) {
    	logger.info("UserApiController - deleteUser - userId:" + userId);
    	ResultMsg response = null;
    	try {
			userService.deleteUser(userId);
			response = new ResultMsg(1,"delete user successfully");
			logger.info("UserApiController - deleteUser have deleted user userId:" + userId);
		} catch (NotFoundException e) {
			response = new ResultMsg(e.getCode(),e.getMessage());
			logger.error("The deleting user is not found by using:" + userId);
		}
        return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResultMsg> getIdentifyCode(@ApiParam(value = "The userId is the phone number which user use to create.",required=true ) @PathVariable("userId") String userId,
    		@ApiParam(value = "the message tye",required=false ) @RequestParam(value = "messageType", required = false) Integer messageType) {
    	logger.info("UserApiController - getIdentifyCode");
    	HttpSession session = request.getSession(true); 
        String sessionId = session.getId();
        if(messageType==null){
        	messageType = -1;
        }
        boolean result = userService.getIdentifyCode(sessionId,userId,messageType);
        ResultMsg response = null;
        if(result){
        	response = new ResultMsg(1,"user get Identify Code successfully");
        }else{
        	response = new ResultMsg(2,"user get Identify Code failed");
        }
        return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
    }

    public ResponseEntity<User> getUserById(@ApiParam(value = "The user id that needs to be fetched.",required=true ) @PathVariable("userId") @Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")String userId) {
        // do some magic!
    	logger.info("UserApiController - getUserById: userid=" + userId);
    	User user = userService.getUserByUserid(userId);
    	logger.info("UserApiController - getUserById successfully and the user is:\n"+user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
    
    public ResponseEntity<Map<String,User>> getUserByIdWithMe(@ApiParam(value = "The user id that needs to be fetched.",required=true ) @PathVariable("userId") String userId) {
		// do some magic!
		logger.info("UserApiController - getUserById: userid=" + userId);
		User currentUser = UserUtil.getCurrentLoginUserInfo();
		User user = userService.getUserByUserid(userId);
		Map<String,User> result = new HashMap<String,User> ();
		result.put("requester", currentUser);
		result.put("result", user);
		return new ResponseEntity<Map<String,User>>(result,HttpStatus.OK);
    }

    public ResponseEntity<ResultMsg> loginUser( @NotNull @ApiParam(value = "The user id for login", required = true) @RequestParam(value = "userid", required = true) String userid,
         @NotNull @ApiParam(value = "The password for login in clear text", required = true) @RequestParam(value = "password", required = true) String password
         /*@NotNull @ApiParam(value = "The indentify code to verify a person but not a machine", required = true) @RequestParam(value = "identifyCode", required = true) String identifyCode*/) {
        // do some magic!
    	//HttpSession session = request.getSession(true); 
        //String sessionId = session.getId();
    	logger.info("UserApiController - loginUser:user start to login。");
    	User user = new User(userid,password/*,identifyCode*/);
    	UserStatus us = userService.checkLoginUser(user/*, sessionId*/);
    	ResultMsg response = null;
    	switch(us){
        case OK:
        	   logger.info("UserApiController - loginUser:User login is successful.");
               logger.info("UserApiController - loginUser:login User get access token.");
               AccessToken accessToken = webTokenService.getAccessToken(userService.getUserByUserid(user.getUserid()));
               response = new ResultMsg(1,"login user successfully",accessToken);
               break;
        case userIdEmpty:
        	   logger.info("UserApiController - loginUser:User checking is fail<userid is empty>.");
               response = new ResultMsg(2,"userid is empty");
               break;
        case userIdNotExist:
        	   logger.info("UserApiController - loginUser:User checking is fail<userid not existed>.");
        	   response = new ResultMsg(2,"userid is not existed");
               break;
        case passwordEmpty:
        	   logger.info("UserApiController - loginUser:User checking is fail<password is empty>.");
     	       response = new ResultMsg(2,"password is empty");
               break;
        case passwordWrong:
        	   logger.info("UserApiController - loginUser:User checking is fail<password is wrong>.");
  	           response = new ResultMsg(2,"password is wrong");
               break;
        /*case identityEmpty:
        	   logger.info("UserApiController - loginUser:User checking is fail<verify code is empty>.");
  	           response = new ResultMsg(2,"verify code is empty");
               break;
        case identityExpired:
        	   logger.info("UserApiController - loginUser:User checking is fail<verify code Expired>.");
	           response = new ResultMsg(2,"verify code Expired");
               break;
        case identityWrong:
        	   logger.info("UserApiController - loginUser:User checking is fail<verify code is wrong>.");
	           response = new ResultMsg(2,"verify code is wrong");
               break;*/
        default:break;
        }
        return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
    }

    public ResponseEntity<Void> logoutUser() {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<ResultMsg> updateUserProfile(@ApiParam(value = "Updated user object" ,required=true ) @RequestBody UserProfile userProfile) {
        // do some magic!
    	userService.updateUserProfile(userProfile);
    	ResultMsg response = new ResultMsg(1,"update user profile successfully");
        return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
    }

	@Override
	public ResponseEntity<List<UserSearchInfo>> searchUserListInfoByPhoneIds(@NotNull @ApiParam(value = "Phone number list get from client device", required = true) @RequestParam(value = "PhoneIds", required = true)List<String> PhoneIds) {
		//List<UserSearchInfo> userInfo = userService.searchUserListInfoByPhoneNums(PhoneIds);
		List<UserSearchInfo> userInfo = userService.searchUserListInfoByPhoneId(PhoneIds);
		return new ResponseEntity<List<UserSearchInfo>>(userInfo,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Map<String,List<UserSearchInfo>>> searchUserMapInfoByPhoneNums(@NotNull @ApiParam(value = "Phone number list get from client device", required = true) @RequestBody List<UserSearchLog> userSearchList) {
		Map<String, List<UserSearchInfo>> userMapInfo = userService.searchUserMapInfoBySearchModel(userSearchList);
		
		return new ResponseEntity<Map<String, List<UserSearchInfo>>>(userMapInfo,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ResultMsg> uploadHead(@ApiParam(value = "head icon for the userId",required=true ) @PathVariable("userId") String userId,
	        @ApiParam(value = "head file content") @RequestParam("headIconFile") MultipartFile headIconFile) {
		ResultMsg response = null;
		try {
			FileUpload fileUpload = userService.uploadHead(userId, headIconFile);
			if(fileUpload==null){
				response = new ResultMsg(101101,"head image upload fialed.");
			}else{
				response = new ResultMsg(1,"head image upload successfully.",fileUpload);
			}
		} catch (CommonException e) {
			response = new ResultMsg(e.getCode(),e.getMessage());
		}
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ResultMsg> updateUserPassword(@ApiParam(value = "user id that need to be updated",required=true) @PathVariable("userId") String userId,
	    	@NotNull @ApiParam(value = "the new password" ,required=true) @RequestParam(value = "newPassword", required = true)  String newPassword,
	        @NotNull @ApiParam(value = "The indentify code to verify a person but not a machine", required = true) @RequestParam(value = "identifyCode", required = true) String identifyCode) {
		HttpSession session = request.getSession(true); 
        String sessionId = session.getId();
		UserStatus us = userService.resetUserPassword(userId,newPassword,identifyCode,sessionId);
		ResultMsg response = null;
    	switch(us){
        case OK:
        	   logger.info("UserApiController - updateUserPassword:User reset password is successful.");
               response = new ResultMsg(1,"reset user password successfully");
               break;
        case userIdNotExist:
        	   logger.info("UserApiController - loginUser:User checking is fail<userid not existed>.");
        	   response = new ResultMsg(2,"userid is not existed");
               break;
        case passwordEmpty:
        	   logger.info("UserApiController - loginUser:User checking is fail<password is empty>.");
     	       response = new ResultMsg(2,"new password is empty");
               break;
        case identityEmpty:
        	   logger.info("UserApiController - loginUser:User checking is fail<verify code is empty>.");
  	           response = new ResultMsg(2,"verify code is empty");
               break;
        case identityExpired:
        	   logger.info("UserApiController - loginUser:User checking is fail<verify code Expired>.");
	           response = new ResultMsg(2,"verify code Expired");
               break;
        case identityWrong:
        	   logger.info("UserApiController - loginUser:User checking is fail<verify code is wrong>.");
	           response = new ResultMsg(2,"verify code is wrong");
               break;
        default:break;
        }
        return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Map<String, Integer>> getLoginUserStatus() {
		Map<String,Integer> userStatus = userService.getCurrentUserStatus();
		return new ResponseEntity<Map<String, Integer>>(userStatus,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Void> updateLastLogin() {
		userService.updateUserLastLogin();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<UserSearchInfo>> searchAddressListForUser(@NotNull @ApiParam(value = "keyword for searching", required = true) @RequestParam(value = "keyWord", required = true)String keyWord,
			                                                             @NotNull @ApiParam(value = "client device Id of woner", required = true) @RequestParam(value = "ownId", required = true)String ownId) {
		List<UserSearchInfo> UserSearchInfoList = userSearchLogService.searchLogsOfUser(keyWord,ownId);
		return new ResponseEntity<List<UserSearchInfo>>(UserSearchInfoList,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ResultMsg> giveLaudForUser(@NotNull @ApiParam(value = "userid who you want to give laud", required = true) @PathVariable("userId") String userId) {
		boolean result = userService.giveLaudForUser(userId);
		ResultMsg response = null;
		if(result){
			 response = new ResultMsg(1,"gvie user laud successfully.");
		}else{
			 response = new ResultMsg(405,"you have already given the laud.");
		}
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}

	
	@Override
	public ResponseEntity<Map<String, Long>> getLaudForUser(@NotNull @ApiParam(value = "keyword for searching", required = true) @PathVariable("userId") String userId) {
		Map<String, Long> result = userService.getLaudForUser(userId);
		return new ResponseEntity<Map<String, Long>>(result,HttpStatus.OK);
	}

	
	@Override
	public ResponseEntity<Map<String, Integer>> getLaudStatusForUser(@NotNull @ApiParam(value = "userid of laud", required = true) @PathVariable("userId") String userId) {
		Map<String, Integer> result = userService.getLaudStatusForUser(userId);
		return new ResponseEntity<Map<String, Integer>>(result,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ResultMsg> removeLaudForUser(@NotNull @ApiParam(value = "userid who you want to remove the laud", required = true) @PathVariable("userId") String userId) {
		boolean result = userService.removeLaudForUser(userId);
		ResultMsg response = null;
		if(result){
			 response = new ResultMsg(1,"remove user laud successfully.");
		}else{
			 response = new ResultMsg(405,"you have already removed the laud.");
		}
		return new ResponseEntity<ResultMsg>(response,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Map<String, Integer>> getParameterMapInfoForUser() {
		Map<String, Integer> sysMap = new HashMap<String, Integer>();
		List<UserFriendRequest> friendRequestList = userFriendRequestService.getAllUserFriendRequest();
		sysMap.put("friendRequest", friendRequestList.size());
		return new ResponseEntity<Map<String, Integer>>(sysMap,HttpStatus.OK);
	}


}
