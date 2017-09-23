package io.swagger.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
/**
 * InfoExplorable
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

@Entity
@Table(name="comment")
public class Comment implements Serializable  {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id = null;

  @JsonProperty("userId")
  @Column(name="userId")
  private String userId = null;
  
  @JsonProperty("infoId")
  @Column(name="infoId")
  private Long infoId = null;
  
  @JsonProperty("status")
  @Column(name="status",nullable=false)
  private Integer status = 0;
  
  @JsonProperty("parentId")
  @Column(name="parentId")
  private String parentId = null;
  
  @JsonProperty("title")
  @Column(name = "title")
  private String title = null;
  
  @JsonProperty("content")
  @Column(name = "content")
  private String content = null;

  @JsonProperty("addition")
  @Column(name="addition")
  private String addition = null;
  
  @JsonProperty("views")
  @Column(name="views")
  private Long views = null;
  
  @JsonProperty("thumbsUp")
  @Column(name="thumbsUp")
  private Long thumbsUp = null;
  
  @JsonProperty("createdTime")
  @Column(name = "createdTime")
  private Long createdTime = null;


  public Comment id(Long id) {
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


  @ApiModelProperty(value = "")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
 
  @ApiModelProperty(value = "")
  public Long getInfoId() {
    return infoId;
  }

  public void setInfoId(Long infoId) {
    this.infoId = infoId;
  }
  
  @ApiModelProperty(value = "")
  public Integer getStatus() {
    return status;
  }
  
  public void setStatus(Integer status) {
	    this.status = status;
	  }
	  
  @ApiModelProperty(value = "")
  public String getParentId() {
		return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }
  
  @ApiModelProperty(value = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  
  @ApiModelProperty(value = "")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
  
  @ApiModelProperty(value = "")
  public String getAddition() {
    return addition;
  }

  public void setAddition(String addition) {
    this.addition = addition;
  }
  
  @ApiModelProperty(value = "")
  public Long getViews() {
    return views;
  }

  public void setViews(Long views) {
    this.views = views;
  }
  
  @ApiModelProperty(value = "")
  public Long getThumbsUp() {
    return thumbsUp;
  }

  public void setThumbsUp(Long thumbsUp) {
    this.thumbsUp = thumbsUp;
  }
  
  @ApiModelProperty(value = "")
  public Long getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Long createdTime) {
    this.createdTime = createdTime;
  }
  

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment infoExplorable = (Comment) o;
    return Objects.equals(this.id, infoExplorable.id) &&
        Objects.equals(this.userId, infoExplorable.userId) &&
        Objects.equals(this.infoId, infoExplorable.infoId) &&
        Objects.equals(this.status, infoExplorable.status) &&
        Objects.equals(this.parentId, infoExplorable.parentId) &&
        Objects.equals(this.title, infoExplorable.title) &&
        Objects.equals(this.content, infoExplorable.content) &&
        Objects.equals(this.addition, infoExplorable.addition) &&
        Objects.equals(this.views, infoExplorable.views) &&
        Objects.equals(this.thumbsUp, infoExplorable.thumbsUp) &&
        Objects.equals(this.createdTime, infoExplorable.createdTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId,infoId,status,parentId, title, content, addition, views, thumbsUp, createdTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InfoExplorable {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    infoId: ").append(toIndentedString(infoId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    addition: ").append(toIndentedString(addition)).append("\n");
    sb.append("    views: ").append(toIndentedString(views)).append("\n");
    sb.append("    thumbsUp: ").append(toIndentedString(thumbsUp)).append("\n");
    sb.append("    createdTime: ").append(toIndentedString(createdTime)).append("\n");
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

