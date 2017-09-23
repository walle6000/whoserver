package io.swagger.api;

import io.swagger.model.ResultMsg;
import io.swagger.model.User;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

@Api(value = "user", description = "the user API")
public interface UserApi {

    @ApiOperation(value = "Create user", notes = "Create user when first login system.", response = ResultMsg.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/user/create",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> createUser(@ApiParam(value = "Created user object" ,required=true ) @RequestBody User user);


    @ApiOperation(value = "Delete user", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid username supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "User not found", response = ResultMsg.class) })
    @RequestMapping(value = "/user/{userId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<ResultMsg> deleteUser(@ApiParam(value = "The id that needs to be deleted",required=true ) @PathVariable("userId") String userId);


    @ApiOperation(value = "identify Code", notes = "Returns an identify Code for a user for a moment.", response = ResultMsg.class, tags={ "user", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
    	@ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/user/getIdentifyCode/{userId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ResultMsg> getIdentifyCode(@ApiParam(value = "The userId is the phone number which user want to receive",required=true ) @PathVariable("userId") String userId,
    		                                  @ApiParam(value = "the message tye",required=false ) @RequestParam(value = "messageType", required = false) Integer messageType);


    @ApiOperation(value = "Get user by user Id", notes = "This can only be done by the logged in user.", response = User.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = User.class),
        @ApiResponse(code = 400, message = "Invalid username supplied", response = User.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = User.class),
        @ApiResponse(code = 404, message = "User not found", response = User.class) })
    @RequestMapping(value = "/user/{userId}",
        produces = {"application/json"}, 
        method = RequestMethod.GET)
    ResponseEntity<User> getUserById(@ApiParam(value = "The user id that needs to be fetched.",required=true ) @PathVariable("userId") @Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")String userId);
    
    @ApiOperation(value = "Get user by user Id with login user's info", notes = "This can only be done by the logged in user.", response = User.class, responseContainer = "Map", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = User.class),
        @ApiResponse(code = 400, message = "Invalid username supplied", response = User.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = User.class),
        @ApiResponse(code = 404, message = "User not found", response = User.class) })
    @RequestMapping(value = "/user/{userId}/withMe",
        produces = {"application/json"}, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,User>> getUserByIdWithMe(@ApiParam(value = "The user id that needs to be fetched.",required=true ) @PathVariable("userId") String userId);


    @ApiOperation(value = "Logs user into the system", notes = "", response = ResultMsg.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid username/password supplied", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "invalid exception", response = ResultMsg.class) })
    @RequestMapping(value = "/user/login",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> loginUser( @NotNull @ApiParam(value = "The user name for login", required = true) @RequestParam(value = "userid", required = true) String userid,
         @NotNull @ApiParam(value = "The password for login in clear text", required = true) @RequestParam(value = "password", required = true) String password
         /*@NotNull @ApiParam(value = "The indentify code to verify a person but not a machine", required = true) @RequestParam(value = "identifyCode", required = true) String identifyCode*/);


    @ApiOperation(value = "Logs out current logged in user session", notes = "", response = Void.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Void.class) })
    @RequestMapping(value = "/user/logout",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Void> logoutUser();
    
    @ApiOperation(value = "update user last login time", notes = "", response = Void.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Void.class) })
    @RequestMapping(value = "/user/lastLogin",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateLastLogin();


    @ApiOperation(value = "Updated user's profile info", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid user supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "User not found", response = ResultMsg.class) })
    @RequestMapping(value = "/user/update",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> updateUserProfile(@ApiParam(value = "Updated user profile object" ,required=true ) @RequestBody UserProfile userProfile);
    
    @ApiOperation(value = "Updated user's password", notes = "This api is used to reset user's password if user forget his password.", response = ResultMsg.class, tags={ "user", })
    @ApiResponses(value = { 
    	@ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid user supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "User not found", response = ResultMsg.class) })
    @RequestMapping(value = "/user/{userId}/reset",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> updateUserPassword(@ApiParam(value = "user id that need to be updated",required=true) @PathVariable("userId") String userId,
    	@NotNull @ApiParam(value = "the new password" ,required=true) @RequestParam(value = "newPassword", required = true) String newPassword,
        @NotNull @ApiParam(value = "The indentify code to verify a person but not a machine", required = true) @RequestParam(value = "identifyCode", required = true) String identifyCode);
    
    
    @ApiOperation(value = "Get a user list info by client's phome number list or User Id List", notes = "This can only be done by the logged in user.", response = UserSearchInfo.class, responseContainer = "List", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = UserSearchInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserSearchInfo.class),
        @ApiResponse(code = 404, message = "friend user not found", response = UserSearchInfo.class) })
    @RequestMapping(value = "/user/search/list",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserSearchInfo>> searchUserListInfoByPhoneIds(@NotNull @ApiParam(value = "Phone number list get from client device", required = true) @RequestParam(value = "PhoneIds", required = true)List<String> PhoneIds);
    
    
    @ApiOperation(value = "Get a user map info by client's phome number list", notes = "This can only be done by the logged in user.", response = UserSearchInfo.class, responseContainer = "Map", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = UserSearchInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserSearchInfo.class),
        @ApiResponse(code = 404, message = "friend user not found", response = UserSearchInfo.class) })
    @RequestMapping(value = "/user/search/map",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Map<String,List<UserSearchInfo>>> searchUserMapInfoByPhoneNums(@NotNull @ApiParam(value = "Phone number and name list get from client device", required = true) @RequestBody List<UserSearchLog> userSearchList);
    
    @ApiOperation(value = "upload a head icon", notes = "", response = ResultMsg.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/user/{userId}/uploadhead",
        produces = { "application/json" }, 
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> uploadHead(@ApiParam(value = "head icon for the userId",required=true ) @PathVariable("userId") String userId,
        @ApiParam(value = "head file content") @RequestParam("headIconFile") MultipartFile headIconFile);
    
    @ApiOperation(value = "Get a user status after user login", notes = "This can only be done by the logged in user.", response = Integer.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = Integer.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Integer.class),
        @ApiResponse(code = 404, message = "friend user not found", response = Integer.class) })
    @RequestMapping(value = "/user/status",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,Integer>> getLoginUserStatus();
    
    @ApiOperation(value = "search a user's address list with friend info by keyword", notes = "This can only be done by the logged in user.", response = UserSearchInfo.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = UserSearchInfo.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = UserSearchInfo.class),
        @ApiResponse(code = 404, message = "friend user not found", response = UserSearchInfo.class) })
    @RequestMapping(value = "/user/search/address",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserSearchInfo>> searchAddressListForUser(@NotNull @ApiParam(value = "keyword for searching", required = true) @RequestParam(value = "keyWord", required = true)String keyWord,
    		                                                      @NotNull @ApiParam(value = "client device Id of woner", required = true) @RequestParam(value = "ownId", required = true)String ownId);
    
    @ApiOperation(value = "give a Laud For a User", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "friend user not found", response = ResultMsg.class) })
    @RequestMapping(value = "/user/{userId}/laud",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<ResultMsg> giveLaudForUser(@NotNull @ApiParam(value = "userid who you want to give laud", required = true) @PathVariable("userId") String userId);
    
    @ApiOperation(value = "get the number of Laud For a User", notes = "This can only be done by the logged in user.", response = Long.class, responseContainer = "Map", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = Long.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Long.class),
        @ApiResponse(code = 404, message = "friend user not found", response = Long.class) })
    @RequestMapping(value = "/user/{userId}/laud",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,Long>> getLaudForUser(@NotNull @ApiParam(value = "keyword for searching", required = true) @PathVariable("userId") String userId);
    
    @ApiOperation(value = "get the status of Laud For a User", notes = "This can only be done by the logged in user.", response = Integer.class, responseContainer = "Map", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = Integer.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Integer.class),
        @ApiResponse(code = 404, message = "friend user not found", response = Integer.class) })
    @RequestMapping(value = "/user/{userId}/laud/status",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,Integer>> getLaudStatusForUser(@NotNull @ApiParam(value = "userid of laud", required = true) @PathVariable("userId") String userId);
    
    @ApiOperation(value = "remove a Laud For a User", notes = "This can only be done by the logged in user.", response = ResultMsg.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "friend user not found", response = ResultMsg.class) })
    @RequestMapping(value = "/user/{userId}/laud",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<ResultMsg> removeLaudForUser(@NotNull @ApiParam(value = "userid who you want to remove the laud", required = true) @PathVariable("userId") String userId);
    
    
    @ApiOperation(value = "get the parameter map info of a login User", notes = "This can only be done by the logged in user.", response = Integer.class, responseContainer = "Map", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid friend supplied", response = Integer.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Integer.class),
        @ApiResponse(code = 404, message = "friend user not found", response = Integer.class) })
    @RequestMapping(value = "/user/notify/para_map",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Map<String,Integer>> getParameterMapInfoForUser();
}
