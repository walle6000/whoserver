package io.swagger.api;

import io.swagger.model.ResultMsg;
import io.swagger.model.TopicMessage;

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

    @ApiOperation(value = "Returns the messages of topic.", notes = "", response = TopicMessage.class, responseContainer = "List", tags={ "message", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = TopicMessage.class) })
    @RequestMapping(value = "/message/{topicId}/receive",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<TopicMessage>> getMessagesOfTopic(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId);


    @ApiOperation(value = "send an message to other users in a topic", notes = "", response = ResultMsg.class, tags={ "message", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResultMsg.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ResultMsg.class),
        @ApiResponse(code = 401, message = "Invalid user authority", response = ResultMsg.class),
        @ApiResponse(code = 404, message = "Topic not found", response = ResultMsg.class),
        @ApiResponse(code = 405, message = "Validation exception", response = ResultMsg.class) })
    @RequestMapping(value = "/message/{topicId}/send",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ResultMsg> sendMessageOfTopic(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "content placed for sending message." ,required=true ) @RequestBody TopicMessage message);

}
