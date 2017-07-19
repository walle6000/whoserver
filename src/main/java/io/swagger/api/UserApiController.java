package io.swagger.api;

import io.swagger.model.AccessToken;
import io.swagger.model.ResultMsg;
import io.swagger.model.User;
import io.swagger.service.UserService;
import io.swagger.service.WebTokenService;

import java.util.Map;

import io.swagger.annotations.*;
import io.swagger.common.UserStatus;

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
	private WebTokenService webTokenService;

    public ResponseEntity<ResultMsg> createUser(@ApiParam(value = "Created user object" ,required=true ) @RequestBody User user,HttpServletRequest req) {
        // do some magic!
    	logger.info("UserApiController - createUser: User is\n" + user);
    	HttpSession session = req.getSession(true); 
        String sessionId = session.getId();
        UserStatus us = userService.checkSaveUser(user, sessionId);
        ResultMsg response = null;
        switch(us){
        case OK:
        	   logger.info("UserApiController - createUser:User checking is successful.");
        	   userService.saveUser(user,true);
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
        	   logger.info("UserApiController - createUser:User checking is fail<userid existed>.");
        	   response = new ResultMsg(2,"userid existed");
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

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "The id that needs to be deleted",required=true ) @PathVariable("userId") String userId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Integer>> getIdentifyCode() {
        // do some magic!
        return new ResponseEntity<Map<String, Integer>>(HttpStatus.OK);
    }

    public ResponseEntity<User> getUserById(@ApiParam(value = "The user id that needs to be fetched.",required=true ) @PathVariable("userId") String userId) {
        // do some magic!
    	logger.info("UserApiController - getUserById: userid=" + userId);
    	User user = userService.getUserByUserid(userId);
    	logger.info("UserApiController - getUserById successfully and the user is:\n"+user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    public ResponseEntity<ResultMsg> loginUser( @NotNull @ApiParam(value = "The user id for login", required = true) @RequestParam(value = "userid", required = true) String userid,
         @NotNull @ApiParam(value = "The password for login in clear text", required = true) @RequestParam(value = "password", required = true) String password,
         @NotNull @ApiParam(value = "The indentify code to verify a person but not a machine", required = true) @RequestParam(value = "identifyCode", required = true) String identifyCode,
         HttpServletRequest req) {
        // do some magic!
    	HttpSession session = req.getSession(true); 
        String sessionId = session.getId();
    	logger.info("UserApiController - loginUser:user start to login。");
    	User user = new User(userid,password,identifyCode);
    	UserStatus us = userService.checkLoginUser(user, sessionId);
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

    public ResponseEntity<Void> logoutUser() {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "id that need to be updated",required=true ) @PathVariable("userId") String userId,
        @ApiParam(value = "Updated user object" ,required=true ) @RequestBody User user) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
