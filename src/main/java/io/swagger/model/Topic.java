package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.TopicCategory;
import io.swagger.model.TopicCategory;
import io.swagger.model.TopicDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
/**
 * Topic
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
@Entity
@Table(name="topic")
public class Topic implements Serializable  {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id = null;

  /*@JsonProperty("category")
  private TopicCategory category = null;*/
  
  @JsonProperty("createUser")
  @Column(name="createUser",nullable=false)
  private String createUser = null;
  
  @JsonProperty("TopicFund")
  @OneToOne(cascade={CascadeType.ALL})
  @JoinColumn(name="topicfund")
  private TopicFund topicFund = null;

  @OneToOne(cascade={CascadeType.ALL})
  @JoinColumn(name="detail")
  @JsonProperty("detail")
  private TopicDetail detail = null;

  //@JsonProperty("tags")
  //private List<TopicCategory> tags = new ArrayList<TopicCategory>();

  /**
   * topic status
   * 0: pending
   * 1: closed
   */
  @JsonProperty("status")
  @Column(name="status",nullable=true)
  private Integer status = 0;

  public Topic id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /*public Topic category(TopicCategory category) {
    this.category = category;
    return this;
  }*/

   /**
   * Get category
   * @return category
  **/
 /* @ApiModelProperty(value = "")
  public TopicCategory getCategory() {
    return category;
  }

  public void setCategory(TopicCategory category) {
    this.category = category;
  }*/



  

  
   /**
   * Get createUser
   * @return createUser
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  
  /**
   * Get detail
   * @return detail
  **/
  public Topic detail(TopicDetail detail) {
    this.detail = detail;
    return this;
  }
   
  @ApiModelProperty(value = "")
  public TopicDetail getDetail() {
    return detail;
  }

  public void setDetail(TopicDetail detail) {
    this.detail = detail;
  }
  
  @ApiModelProperty(value = "")
  public TopicFund getTopicFund() {
		return topicFund;
  }

  public void setTopicFund(TopicFund topicFund) {
		this.topicFund = topicFund;
  }

	@ApiModelProperty(value = "")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


  /*public Topic tags(List<TopicCategory> tags) {
    this.tags = tags;
    return this;
  }

  public Topic addTagsItem(TopicCategory tagsItem) {
    this.tags.add(tagsItem);
    return this;
  }*/

   /**
   * Get tags
   * @return tags
  **/
  /*@ApiModelProperty(value = "")
  public List<TopicCategory> getTags() {
    return tags;
  }

  public void setTags(List<TopicCategory> tags) {
    this.tags = tags;
  }*/

  /*public Topic status(StatusEnum status) {
    this.status = status;
    return this;
  }

   *//**
   * topic status
   * @return status
  **//*
  @ApiModelProperty(value = "topic status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Topic complete(Boolean complete) {
    this.complete = complete;
    return this;
  }

   *//**
   * Get complete
   * @return complete
  **//*
  @ApiModelProperty(value = "")
  public Boolean getComplete() {
    return complete;
  }

  public void setComplete(Boolean complete) {
    this.complete = complete;
  }
*/
  
  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Topic topic = (Topic) o;
    return Objects.equals(this.id, topic.id) &&
        //Objects.equals(this.category, topic.category) &&
        Objects.equals(this.createUser, topic.createUser) &&
        Objects.equals(this.detail, topic.detail) &&
        //Objects.equals(this.tags, topic.tags) &&
        Objects.equals(this.status, topic.status);
        //Objects.equals(this.complete, topic.complete);
  }

 
@Override
  public int hashCode() {
    return Objects.hash(id, /*category, title, createDate, */createUser, detail,/* tags, */status/*, complete*/);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Topic {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    //sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    createUser: ").append(toIndentedString(createUser)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    //sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    //sb.append("    complete: ").append(toIndentedString(complete)).append("\n");
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

