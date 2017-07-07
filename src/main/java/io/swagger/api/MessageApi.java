package io.swagger.api;

import io.swagger.model.Body4;
import io.swagger.model.Response200;
import io.swagger.model.InlineResponse2005;

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

@Api(value = "message", description = "the message API")
public interface MessageApi {

    @ApiOperation(value = "Returns the messages of topic.", notes = "", response = InlineResponse2005.class, responseContainer = "List", tags={ "message", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = InlineResponse2005.class) })
    @RequestMapping(value = "/message/{topicId}/receive",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<InlineResponse2005>> getMessagesOfTopic(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId);


    @ApiOperation(value = "send an message to other users in a topic", notes = "", response = Response200.class, tags={ "message", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Response200.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = Response200.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = Response200.class),
        @ApiResponse(code = 404, message = "Topic not found", response = Response200.class),
        @ApiResponse(code = 405, message = "Validation exception", response = Response200.class) })
    @RequestMapping(value = "/message/{topicId}/send",
        produces = { "application/xml", "application/json" }, 
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.POST)
    ResponseEntity<Response200> sendMessageOfTopic(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "content placed for sending message." ,required=true ) @RequestBody Body4 body);

}
