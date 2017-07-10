package io.swagger.api;

import java.io.File;
import io.swagger.model.Response200;
import io.swagger.model.Topic;
import io.swagger.model.TopicTopology;

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



    public ResponseEntity<Response200> addTopic(@ApiParam(value = "Topic object that needs to be added to the store" ,required=true ) @RequestBody Topic topic) {
        // do some magic!
        return new ResponseEntity<Response200>(HttpStatus.OK);
    }

    public ResponseEntity<Response200> addUserForTopic(@ApiParam(value = "ID of topic to update",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "person who send the topic.", required=true ) @RequestPart(value="sender", required=true)  String sender) {
        // do some magic!
        return new ResponseEntity<Response200>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteTopic(@ApiParam(value = "Topic id to delete",required=true ) @PathVariable("topicId") Long topicId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Topic>> findTopicsByStatus( @NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "AVAILABLE, PENDING, CLOSE") @RequestParam(value = "status", required = true) List<String> status) {
        // do some magic!
        return new ResponseEntity<List<Topic>>(HttpStatus.OK);
    }

    public ResponseEntity<List<Topic>> findTopicsByTags( @NotNull @ApiParam(value = "Tags to filter by", required = true) @RequestParam(value = "tags", required = true) List<String> tags) {
        // do some magic!
        return new ResponseEntity<List<Topic>>(HttpStatus.OK);
    }

    public ResponseEntity<List<TopicTopology>> getParticipatorByTopicId(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId) {
        // do some magic!
        return new ResponseEntity<List<TopicTopology>>(HttpStatus.OK);
    }

    public ResponseEntity<Topic> getTopicById(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId) {
        // do some magic!
        return new ResponseEntity<Topic>(HttpStatus.OK);
    }

    public ResponseEntity<List<Topic>> getTopics() {
        // do some magic!
        return new ResponseEntity<List<Topic>>(HttpStatus.OK);
    }

    public ResponseEntity<Response200> sendTopicToUser(@ApiParam(value = "ID of topic to send.",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "person who receive the topic.", required=true ) @RequestPart(value="receiver", required=true)  String receiver,
        @ApiParam(value = "The way of sending.", required=true , allowableValues="INNER, MESSAGE, WEIXIN, QQ") @RequestPart(value="pattern", required=true)  String pattern) {
        // do some magic!
        return new ResponseEntity<Response200>(HttpStatus.OK);
    }

    public ResponseEntity<Response200> updateTopic(@ApiParam(value = "Topic object that needs to be added to the store" ,required=true ) @RequestBody Topic topic) {
        // do some magic!
        return new ResponseEntity<Response200>(HttpStatus.OK);
    }

    public ResponseEntity<Response200> uploadFile(@ApiParam(value = "ID of topic to update",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "file detail") @RequestPart("file") MultipartFile img,
        @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file) {
        // do some magic!
        return new ResponseEntity<Response200>(HttpStatus.OK);
    }

}
