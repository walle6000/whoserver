package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
/**
 * TopicTopology
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
@Entity
@Table(name = "topicinviter")
public class TopicInviter implements Serializable  {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id = null;	
	
  @JsonProperty("topicId")
  @Column(name = "topicid", nullable = false)
  private Long topicId = null;

  @JsonProperty("sender")
  @Column(name = "sender", nullable = false)
  private String sender = null;

  @JsonProperty("reciver")
  @Column(name = "reciver", nullable = false)
  private String reciver = null;
  
  @JsonProperty("inviteTime")
  @Column(name = "invitetime")
  private Long inviteTime = null;
  
  @JsonProperty("message")
  @Column(name = "message")
  private String message = null;
  
   /**
	 * status:
	 * 0: pending
	 * 1: inviting
	 * 2: confirmed 
	 * 3: cancel
	 */
  @JsonProperty("status")
  @Column(name = "status", nullable = false)
  private Integer status = 0;

  
  public TopicInviter(Long topicId, String sender, String reciver, Integer status) {
	super();
	this.topicId = topicId;
	this.sender = sender;
	this.reciver = reciver;
	this.status = status;
 }
  
  

  public TopicInviter() {
	super();
 }



  public TopicInviter topicId(Long topicId) {
    this.topicId = topicId;
    return this;
  }

   /**
   * Get topicId
   * @return topicId
  **/
  @ApiModelProperty(value = "")
  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

  public TopicInviter sender(String sender) {
    this.sender = sender;
    return this;
  }

   /**
   * Get sender
   * @return sender
  **/
  @ApiModelProperty(value = "")
  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public TopicInviter reciver(String reciver) {
    this.reciver = reciver;
    return this;
  }
  
  
  @ApiModelProperty(value = "")
  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  @ApiModelProperty(value = "")
  public String getReciver() {
		return reciver;
  }

   public void setReciver(String reciver) {
	    this.reciver = reciver;
   }

   
   @ApiModelProperty(value = "")
   public Long getInviteTime() {
	return inviteTime;
   }


   public void setInviteTime(Long inviteTime) {
	this.inviteTime = inviteTime;
   }


   @ApiModelProperty(value = "")
   public Integer getStatus() {
	    return status;
   }

   public void setStatus(Integer status) {
	    this.status = status;
   }

   
   @ApiModelProperty(value = "")
   public String getMessage() {
	return message;
   }



   public void setMessage(String message) {
	this.message = message;
   }



@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TopicInviter topicInviter = (TopicInviter) o;
    return Objects.equals(this.topicId, topicInviter.topicId) &&
        Objects.equals(this.sender, topicInviter.sender) &&
        Objects.equals(this.reciver, topicInviter.reciver);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topicId, sender, reciver);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TopicTopology {\n");
    
    sb.append("    topicId: ").append(toIndentedString(topicId)).append("\n");
    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    recivers: ").append(toIndentedString(reciver)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

