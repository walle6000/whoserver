package io.swagger.api;

import io.swagger.model.Response200;
import io.swagger.model.User;
import io.swagger.service.UserService;

import java.util.Map;

import io.swagger.annotations.*;
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


import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

@RestController
public class UserApiController implements UserApi {
	
	private Logger logger = LoggerFactory.getLogger(UserApiController.class);

	@Autowired
	private UserService userService;

    public ResponseEntity<Response200> createUser(@ApiParam(value = "Created user object" ,required=true ) @RequestBody User user) {
        // do some magic!
    	logger.info("UserApiController - createUser: User is\n" + user);
    	userService.saveUser(user);
    	logger.info("UserApiController - createUser is successful.");
    	Response200 response = new Response200(4,"create user successfully");
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
