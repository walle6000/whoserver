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
@Table(name = "Topictopology")
public class TopicTopology implements Serializable  {
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
  
  @JsonProperty("joinTime")
  @Column(name = "jointime")
  private Long joinTime = null;
  
  @JsonProperty("comment")
  @Column(name = "comment")
  private String comment = null;
  
  /**
 	 * status:
 	 * 0: inner source
 	 * 1: wechat
 	 * 2: qq
 	 * 3: message
 	 */
  @JsonProperty("source")
  @Column(name = "source")
  private Integer source = 0;
  
   /**
	 * status:
	 * 0: 转发
	 * 1~*: 自定义 
	 */
  @JsonProperty("status")
  @Column(name = "status", nullable = false)
  private String status = null;
  
  
  @JsonProperty("selection")
  @Column(name = "selection", nullable = false)
  private Integer selection = 0;

  
  public TopicTopology(Long topicId, String sender, String reciver, String status) {
	super();
	this.topicId = topicId;
	this.sender = sender;
	this.reciver = reciver;
	this.status = status;
 }
  
  

  public TopicTopology() {
	super();
  }



  public TopicTopology topicId(Long topicId) {
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

  public TopicTopology sender(String sender) {
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

  public TopicTopology reciver(String reciver) {
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
   public Long getJoinTime() {
	    return joinTime;
   }

   public void setJoinTime(Long joinTime) {
	    this.joinTime = joinTime;
   }

   @ApiModelProperty(value = "")
   public String getStatus() {
	    return status;
   }

   public void setStatus(String status) {
	    this.status = status;
   }

   
   @ApiModelProperty(value = "")
   public String getComment() {
	return comment;
   }

   public void setComment(String comment) {
	this.comment = comment;
   }


   
   @ApiModelProperty(value = "")
   public Integer getSource() {
	return source;
   }



   public void setSource(Integer source) {
	this.source = source;
   }

   
   @ApiModelProperty(value = "")
   public Integer getSelection() {
	return selection;
   }

   public void setSelection(Integer selection) {
	this.selection = selection;
   }



@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TopicTopology topicTopology = (TopicTopology) o;
    return Objects.equals(this.topicId, topicTopology.topicId) &&
        Objects.equals(this.sender, topicTopology.sender) &&
        Objects.equals(this.reciver, topicTopology.reciver);
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

