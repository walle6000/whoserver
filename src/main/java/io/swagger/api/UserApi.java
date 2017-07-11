package io.swagger.api;

import io.swagger.model.Response200;
import io.swagger.model.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

@Api(value = "user", description = "the user API")
public interface UserApi {

    @ApiOperation(value = "Create user", notes = "Create user when first login system.", response = Response200.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Response200.class),
        @ApiResponse(code = 405, message = "invalid exception", response = Response200.class) })
    @RequestMapping(value = "/user",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Response200> createUser(@ApiParam(value = "Created user object" ,required=true ) @RequestBody User user,HttpServletRequest req);


    @ApiOperation(value = "Delete user", notes = "This can only be done by the logged in user.", response = Void.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid username supplied", response = Void.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Void.class),
        @ApiResponse(code = 404, message = "User not found", response = Void.class) })
    @RequestMapping(value = "/user/{userId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@ApiParam(value = "The id that needs to be deleted",required=true ) @PathVariable("userId") String userId);


    @ApiOperation(value = "identify Code", notes = "Returns an identify Code for a user for a moment.", response = Integer.class, responseContainer = "Map", tags={ "user", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = Integer.class),
    	@ApiResponse(code = 405, message = "invalid exception", response = Integer.class) })
    @RequestMapping(value = "/user/getIdentifyCode",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String, Integer>> getIdentifyCode();


    @ApiOperation(value = "Get user by user Id", notes = "", response = User.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = User.class),
        @ApiResponse(code = 400, message = "Invalid username supplied", response = User.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = User.class),
        @ApiResponse(code = 404, message = "User not found", response = User.class) })
    @RequestMapping(value = "/user/{userId}",
        produces = {"application/json"}, 
        method = RequestMethod.GET)
    ResponseEntity<User> getUserById(@ApiParam(value = "The user id that needs to be fetched.",required=true ) @PathVariable("userId") String userId);


    @ApiOperation(value = "Logs user into the system", notes = "", response = Response200.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Response200.class),
        @ApiResponse(code = 400, message = "Invalid username/password supplied", response = Response200.class),
        @ApiResponse(code = 405, message = "invalid exception", response = Response200.class) })
    @RequestMapping(value = "/user/login",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Response200> loginUser( @NotNull @ApiParam(value = "The user name for login", required = true) @RequestParam(value = "username", required = true) String username,
         @NotNull @ApiParam(value = "The password for login in clear text", required = true) @RequestParam(value = "password", required = true) String password);


    @ApiOperation(value = "Logs out current logged in user session", notes = "", response = Void.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Void.class) })
    @RequestMapping(value = "/user/logout",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Void> logoutUser();


    @ApiOperation(value = "Updated user", notes = "This can only be done by the logged in user.", response = Void.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid user supplied", response = Void.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Void.class),
        @ApiResponse(code = 404, message = "User not found", response = Void.class) })
    @RequestMapping(value = "/user/{userId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@ApiParam(value = "id that need to be updated",required=true ) @PathVariable("userId") String userId,
        @ApiParam(value = "Updated user object" ,required=true ) @RequestBody User user);

}
