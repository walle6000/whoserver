package io.swagger.api;

import io.swagger.model.Body4;
import io.swagger.model.InlineResponse200;
import io.swagger.model.InlineResponse2005;

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
public class MessageApiController implements MessageApi {



    public ResponseEntity<List<InlineResponse2005>> getMessagesOfTopic(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId) {
        // do some magic!
        return new ResponseEntity<List<InlineResponse2005>>(HttpStatus.OK);
    }

    public ResponseEntity<InlineResponse200> sendMessageOfTopic(@ApiParam(value = "ID of topic to return",required=true ) @PathVariable("topicId") Long topicId,
        @ApiParam(value = "content placed for sending message." ,required=true ) @RequestBody Body4 body) {
        // do some magic!
        return new ResponseEntity<InlineResponse200>(HttpStatus.OK);
    }

}