package io.swagger.api;

import io.swagger.model.Body2;
import io.swagger.model.Body3;
import java.io.File;
import io.swagger.model.InlineResponse200;
import io.swagger.model.InlineResponse2002;
import io.swagger.model.InlineResponse2003;
import io.swagger.model.InlineResponse2004;

import io.swagger.annotations.*;

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
public class TopicApiController implements TopicApi {



    public ResponseEntity<InlineResponse200> addTopic(@ApiParam(value = "Topic object that needs to be added to the store" ,required=true ) @RequestBody Body3 body) {
        // do some magic!
        return new ResponseEntity<InlineResponse200>(HttpStatus.OK);
    }

    public ResponseEntity<InlineResponse200> addUserForTopic(@ApiParam(value = "ID of topic to update",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "person who send the topic.", required=true ) @RequestPart(value="sender", required=true)  String sender) {
        // do some magic!
        return new ResponseEntity<InlineResponse200>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteTopic(@ApiParam(value = "Topic id to delete",required=true ) @PathVariable("topicId") Long topicId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<InlineResponse2002>> findTopicsByStatus( @NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "AVAILABLE, PENDING, CLOSE") @RequestParam(value = "status", required = true) List<String> status) {
        // do some magic!
        return new ResponseEntity<List<InlineResponse2002>>(HttpStatus.OK);
    }

    public ResponseEntity<List<InlineResponse2002>> findTopicsByTags( @NotNull @ApiParam(value = "Tags to filter by", required = true) @RequestParam(value = "tags", required = true) List<String> tags) {
        // do some magic!
        return new ResponseEntity<List<InlineResponse2002>>(HttpStatus.OK);
    }

    public ResponseEntity<List<InlineResponse2004>> getParticipatorByTopicId(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId) {
        // do some magic!
        return new ResponseEntity<List<InlineResponse2004>>(HttpStatus.OK);
    }

    public ResponseEntity<InlineResponse2003> getTopicById(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId) {
        // do some magic!
        return new ResponseEntity<InlineResponse2003>(HttpStatus.OK);
    }

    public ResponseEntity<List<InlineResponse2002>> getTopics() {
        // do some magic!
        return new ResponseEntity<List<InlineResponse2002>>(HttpStatus.OK);
    }

    public ResponseEntity<InlineResponse200> sendTopicToUser(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "person who receive the topic.", required=true ) @RequestPart(value="receiver", required=true)  String receiver,
        @ApiParam(value = "The way of sending.", required=true , allowableValues="INNER, MESSAGE, WEIXIN, QQ") @RequestPart(value="pattern", required=true)  String pattern) {
        // do some magic!
        return new ResponseEntity<InlineResponse200>(HttpStatus.OK);
    }

    public ResponseEntity<InlineResponse200> updateTopic(@ApiParam(value = "Topic object that needs to be added to the store" ,required=true ) @RequestBody Body2 body) {
        // do some magic!
        return new ResponseEntity<InlineResponse200>(HttpStatus.OK);
    }

    public ResponseEntity<InlineResponse200> uploadFile(@ApiParam(value = "ID of topic to update",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "file detail") @RequestPart("file") MultipartFile img,
        @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file) {
        // do some magic!
        return new ResponseEntity<InlineResponse200>(HttpStatus.OK);
    }

}
