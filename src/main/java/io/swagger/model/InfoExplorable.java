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
@Table(name="userinfoexplorable")
public class InfoExplorable implements Serializable  {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id = null;

  @JsonProperty("userId")
  @Column(name="userId")
  private String userId = null;
  
  @JsonProperty("infoTitle")
  @Column(name = "infoTitle")
  private String infoTitle = null;
  
  @JsonProperty("infoContent")
  @Column(name = "infoContent")
  private String infoContent = null;

  @JsonProperty("infoAddition")
  @Column(name="infoAddition")
  private String infoAddition = null;
  
  /*@JsonProperty("views")
  @Column(name="views")
  private Long views = null;
  
  @JsonProperty("thumbsUp")
  @Column(name="thumbsUp")
  private Long thumbsUp = null;*/
  
  @JsonProperty("createdTime")
  //@Temporal(TemporalType.TIMESTAMP)
  @Column(name = "createdTime")
  private Long createdTime = 0l;

  @JsonProperty("postImages")
  @Column(name="postImages")
  private String postImages = null;
  
  @JsonProperty("status")
  @Column(name="status")
  private Integer status = 0;

  public InfoExplorable id(Long id) {
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
  public String getInfoTitle() {
    return infoTitle;
  }

  public void setInfoTitle(String infoTitle) {
    this.infoTitle = infoTitle;
  }
  
  @ApiModelProperty(value = "")
  public String getInfoContent() {
    return infoContent;
  }

  public void setInfoContent(String infoContent) {
    this.infoContent = infoContent;
  }
  
  @ApiModelProperty(value = "")
  public String getInfoAddition() {
    return infoAddition;
  }

  public void setInfoAddition(String infoAddition) {
    this.infoAddition = infoAddition;
  }
  
  /*@ApiModelProperty(value = "")
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
  }*/
  
  @ApiModelProperty(value = "")
  public String getPostImages() {
    return postImages;
  }

  public void setPostImages(String postImages) {
    this.postImages = postImages;
  }

  
  @ApiModelProperty(value = "")
  public Long getCreatedTime() {
	return createdTime;
  }

  public void setCreatedTime(Long createdTime) {
	this.createdTime = createdTime;
  }
  
  
  @ApiModelProperty(value = "")
  public Integer getStatus() {
	return status;
  }

  public void setStatus(Integer status) {
	this.status = status;
  }

public InfoExplorable() {
	super();
  }

public InfoExplorable(String infoTitle, String infoContent, String infoAddition) {
	super();
	this.infoTitle = infoTitle;
	this.infoContent = infoContent;
	this.infoAddition = infoAddition;
}

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InfoExplorable infoExplorable = (InfoExplorable) o;
    return Objects.equals(this.id, infoExplorable.id) &&
        Objects.equals(this.userId, infoExplorable.userId) &&
        Objects.equals(this.infoTitle, infoExplorable.infoTitle) &&
        Objects.equals(this.infoContent, infoExplorable.infoContent) &&
        Objects.equals(this.infoAddition, infoExplorable.infoAddition) &&
        /*Objects.equals(this.views, infoExplorable.views) &&
        Objects.equals(this.thumbsUp, infoExplorable.thumbsUp) &&*/
        Objects.equals(this.createdTime, infoExplorable.createdTime) &&
        Objects.equals(this.postImages, infoExplorable.postImages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, infoTitle, infoContent, infoAddition,/* views, thumbsUp, */createdTime,postImages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InfoExplorable {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    infoTitle: ").append(toIndentedString(infoTitle)).append("\n");
    sb.append("    infoContent: ").append(toIndentedString(infoContent)).append("\n");
    sb.append("    infoAddition: ").append(toIndentedString(infoAddition)).append("\n");
    /*sb.append("    views: ").append(toIndentedString(views)).append("\n");
    sb.append("    thumbsUp: ").append(toIndentedString(thumbsUp)).append("\n");*/
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

