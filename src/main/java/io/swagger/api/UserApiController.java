package io.swagger.api;

import io.swagger.model.Body;
import io.swagger.model.Body1;
import io.swagger.model.InlineResponse200;
import io.swagger.model.InlineResponse2001;
import io.swagger.service.UserService;

import java.util.Map;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

@Controller
public class UserApiController implements UserApi {

	@Autowired
	private UserService userService;

    public ResponseEntity<InlineResponse200> createUser(@ApiParam(value = "Created user object" ,required=true ) @RequestBody Body body) {
        // do some magic!
        return new ResponseEntity<InlineResponse200>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "The id that needs to be deleted",required=true ) @PathVariable("userId") String userId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Integer>> getIdentifyCode() {
        // do some magic!
        return new ResponseEntity<Map<String, Integer>>(HttpStatus.OK);
    }

    public ResponseEntity<InlineResponse2001> getUserById(@ApiParam(value = "The user id that needs to be fetched.",required=true ) @PathVariable("userId") String userId) {
        // do some magic!
    	userService.getUserByUserid(userId);
        return new ResponseEntity<InlineResponse2001>(HttpStatus.OK);
    }

    public ResponseEntity<InlineResponse200> loginUser( @NotNull @ApiParam(value = "The user name for login", required = true) @RequestParam(value = "username", required = true) String username,
         @NotNull @ApiParam(value = "The password for login in clear text", required = true) @RequestParam(value = "password", required = true) String password) {
        // do some magic!
        return new ResponseEntity<InlineResponse200>(HttpStatus.OK);
    }

    public ResponseEntity<Void> logoutUser() {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "id that need to be updated",required=true ) @PathVariable("userId") String userId,
        @ApiParam(value = "Updated user object" ,required=true ) @RequestBody Body1 body) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
