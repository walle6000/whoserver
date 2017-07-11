package io.swagger.api;

import io.swagger.model.Response200;
import io.swagger.model.User;
import io.swagger.service.UserService;

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

    public ResponseEntity<Response200> createUser(@ApiParam(value = "Created user object" ,required=true ) @RequestBody User user,HttpServletRequest req) {
        // do some magic!
    	logger.info("UserApiController - createUser: User is\n" + user);
    	HttpSession session = req.getSession(true); 
        String sessionId = session.getId();
        UserStatus us = userService.checkUser(user, sessionId);
        Response200 response = null;
        switch(us){
        case OK:
        	   logger.info("UserApiController - User checking is successful.");
        	   userService.saveUser(user);
               logger.info("UserApiController - createUser is successful.");
               response = new Response200(1,"create user successfully");
               break;
        case userIdEmpty:
        	   logger.info("UserApiController - User checking is fail<userid is empty>.");
               response = new Response200(2,"userid is empty");
               break;
        case userIdExist:
        	   logger.info("UserApiController - User checking is fail<userid existed>.");
        	   response = new Response200(2,"userid existed");
               break;
        case passwordEmpty:
        	   logger.info("UserApiController - User checking is fail<password is empty>.");
     	       response = new Response200(2,"password is empty");
               break;
        case identityEmpty:
        	   logger.info("UserApiController - User checking is fail<verify code is empty>.");
  	           response = new Response200(2,"verify code is empty");
               break;
        case identityExpired:
        	   logger.info("UserApiController - User checking is fail<verify code Expired>.");
	           response = new Response200(2,"verify code Expired");
               break;
        case identityWrong:
        	   logger.info("UserApiController - User checking is fail<verify code is wrong>.");
	           response = new Response200(2,"verify code is wrong");
               break;
        default:break;
        }
        return new ResponseEntity<Response200>(response,HttpStatus.OK);
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

    public ResponseEntity<Response200> loginUser( @NotNull @ApiParam(value = "The user name for login", required = true) @RequestParam(value = "username", required = true) String username,
         @NotNull @ApiParam(value = "The password for login in clear text", required = true) @RequestParam(value = "password", required = true) String password) {
        // do some magic!
        return new ResponseEntity<Response200>(HttpStatus.OK);
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
