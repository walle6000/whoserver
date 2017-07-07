package io.swagger.api;

import io.swagger.model.Body2;
import io.swagger.model.Body3;
import java.io.File;
import io.swagger.model.Response200;
import io.swagger.model.InlineResponse2002;
import io.swagger.model.InlineResponse2003;
import io.swagger.model.InlineResponse2004;

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

@Api(value = "topic", description = "the topic API")
public interface TopicApi {

    @ApiOperation(value = "Add a new topic.", notes = "", response = Response200.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Response200.class),
        @ApiResponse(code = 405, message = "Invalid input", response = Response200.class) })
    @RequestMapping(value = "/topic",
        produces = { "application/xml", "application/json" }, 
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.POST)
    ResponseEntity<Response200> addTopic(@ApiParam(value = "Topic object that needs to be added to the store" ,required=true ) @RequestBody Body3 body);


    @ApiOperation(value = "add a user for a topic", notes = "", response = Response200.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Response200.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Response200.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Response200.class),
        @ApiResponse(code = 404, message = "Topic not found", response = Response200.class),
        @ApiResponse(code = 405, message = "Validation exception", response = Response200.class) })
    @RequestMapping(value = "/topic/{topicId}/addUser",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Response200> addUserForTopic(@ApiParam(value = "ID of topic to update",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "person who send the topic.", required=true ) @RequestPart(value="sender", required=true)  String sender);


    @ApiOperation(value = "Deletes a topic", notes = "", response = Void.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Void.class),
        @ApiResponse(code = 404, message = "Topic not found", response = Void.class),
        @ApiResponse(code = 405, message = "Validation exception", response = Void.class) })
    @RequestMapping(value = "/topic/{topicId}",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteTopic(@ApiParam(value = "Topic id to delete",required=true ) @PathVariable("topicId") Long topicId);


    @ApiOperation(value = "Finds topics by status", notes = "Multiple status values can be provided with comma separated strings", response = InlineResponse2002.class, responseContainer = "List", tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = InlineResponse2002.class),
        @ApiResponse(code = 400, message = "Invalid status value", response = InlineResponse2002.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = InlineResponse2002.class),
        @ApiResponse(code = 404, message = "Topic not found", response = InlineResponse2002.class),
        @ApiResponse(code = 405, message = "Validation exception", response = InlineResponse2002.class) })
    @RequestMapping(value = "/topic/findByStatus",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<InlineResponse2002>> findTopicsByStatus( @NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "AVAILABLE, PENDING, CLOSE") @RequestParam(value = "status", required = true) List<String> status);


    @ApiOperation(value = "Finds Topics by tags", notes = "Muliple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.", response = InlineResponse2002.class, responseContainer = "List", tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = InlineResponse2002.class),
        @ApiResponse(code = 400, message = "Invalid tag value", response = InlineResponse2002.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = InlineResponse2002.class),
        @ApiResponse(code = 404, message = "Topic not found", response = InlineResponse2002.class),
        @ApiResponse(code = 405, message = "Validation exception", response = InlineResponse2002.class) })
    @RequestMapping(value = "/topic/findByTags",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<InlineResponse2002>> findTopicsByTags( @NotNull @ApiParam(value = "Tags to filter by", required = true) @RequestParam(value = "tags", required = true) List<String> tags);


    @ApiOperation(value = "Find topic's participator by ID", notes = "Returns a group of users", response = InlineResponse2004.class, responseContainer = "List", tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = InlineResponse2004.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = InlineResponse2004.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = InlineResponse2004.class),
        @ApiResponse(code = 404, message = "Topic not found", response = InlineResponse2004.class),
        @ApiResponse(code = 405, message = "Validation exception", response = InlineResponse2004.class) })
    @RequestMapping(value = "/topic/{topicId}/participator",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<InlineResponse2004>> getParticipatorByTopicId(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId);


    @ApiOperation(value = "Find topic by ID", notes = "Returns a single topic", response = InlineResponse2003.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = InlineResponse2003.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = InlineResponse2003.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = InlineResponse2003.class),
        @ApiResponse(code = 404, message = "Topic not found", response = InlineResponse2003.class),
        @ApiResponse(code = 405, message = "Validation exception", response = InlineResponse2003.class) })
    @RequestMapping(value = "/topic/{topicId}",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<InlineResponse2003> getTopicById(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId);


    @ApiOperation(value = "Find all topic of login user.", notes = "Returns a login user's topics", response = InlineResponse2002.class, responseContainer = "List", tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = InlineResponse2002.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = InlineResponse2002.class),
        @ApiResponse(code = 405, message = "Validation exception", response = InlineResponse2002.class) })
    @RequestMapping(value = "/topic",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<InlineResponse2002>> getTopics();


    @ApiOperation(value = "send a topic to other person.", notes = "", response = Response200.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Response200.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Response200.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Response200.class),
        @ApiResponse(code = 404, message = "Topic not found", response = Response200.class),
        @ApiResponse(code = 405, message = "Validation exception", response = Response200.class) })
    @RequestMapping(value = "/topic/{topicId}/sendToUser",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Response200> sendTopicToUser(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "person who receive the topic.", required=true ) @RequestPart(value="receiver", required=true)  String receiver,
        @ApiParam(value = "The way of sending.", required=true , allowableValues="INNER, MESSAGE, WEIXIN, QQ") @RequestPart(value="pattern", required=true)  String pattern);


    @ApiOperation(value = "Update an existing topic", notes = "", response = Response200.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Response200.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Response200.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Response200.class),
        @ApiResponse(code = 404, message = "Topic not found", response = Response200.class),
        @ApiResponse(code = 405, message = "Validation exception", response = Response200.class) })
    @RequestMapping(value = "/topic",
        produces = { "application/xml", "application/json" }, 
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.PUT)
    ResponseEntity<Response200> updateTopic(@ApiParam(value = "Topic object that needs to be added to the store" ,required=true ) @RequestBody Body2 body);


    @ApiOperation(value = "uploads a file", notes = "", response = Response200.class, tags={ "topic", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Response200.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Response200.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Response200.class),
        @ApiResponse(code = 404, message = "Topic not found", response = Response200.class),
        @ApiResponse(code = 405, message = "Validation exception", response = Response200.class) })
    @RequestMapping(value = "/topic/{topicId}/uploadfile",
        produces = { "application/json" }, 
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    ResponseEntity<Response200> uploadFile(@ApiParam(value = "ID of topic to update",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "file detail") @RequestPart("file") MultipartFile img,
        @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file);

}
