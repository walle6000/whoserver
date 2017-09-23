package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
/**
 * TopicDetail1
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
@Entity
@Table(name = "topicdetail")
public class TopicDetail  implements Serializable {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id = null;
  
  @JsonProperty("title")
  @Column(name="title",nullable=true)
  private String title = null;

  @JsonProperty("content")
  @Column(name = "content", nullable = false)
  private String content = null;

  @JsonProperty("imgUrls")
  @Column(name = "imgurls", nullable = true)
  private String imgUrls = null;

  @JsonProperty("fileUrls")
  @Column(name = "fileurls", nullable = true)
  private String fileUrls = null;
  
  @JsonProperty("rightNum")
  @Column(name="rightNum",nullable=false)
  private Integer rightNum = 0;
  
  @JsonProperty("tierNum")
  @Column(name="tiernum",nullable=true)
  private Integer tierNum;
  
  @JsonProperty("totalNum")
  @Column(name="totalNum",nullable=true)
  private Integer totalNum;
  
  @JsonProperty("discussType")
  @Column(name="discusstype",nullable=false)
  private Integer discussType = 0;
  
  @JsonProperty("createDate")
  @Column(name = "createDate")
  private Long createDate = null;
  
  @JsonProperty("endDate")
  @Column(name="endDate",nullable=true)
  private Long endDate;

  
  @JsonProperty("categoryType")
  @Column(name="categorytype",nullable=false)
  private Integer categoryType = null;
  
  public TopicDetail id(Long id) {
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

  public TopicDetail content(String content) {
    this.content = content;
    return this;
  }

   /**
   * Get detail
   * @return detail
  **/
  @ApiModelProperty(value = "")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getImgUrls() {
	return imgUrls;
  }

  public void setImgUrls(String imgUrls) {
	this.imgUrls = imgUrls;
  }

  public String getFileUrls() {
	return fileUrls;
  }

  public void setFileUrls(String fileUrls) {
	this.fileUrls = fileUrls;
  }
  
  /**
   * Get title
   * @return title
  **/
  public TopicDetail title(String title) {
	    this.title = title;
	    return this;
  }
  
  @ApiModelProperty(example = "recruit coder", required = true, value = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Get createDate
   * @return createDate
  **/
  public TopicDetail createDate(Long createDate) {
	    this.createDate = createDate;
	    return this;
  }
  
  @ApiModelProperty(value = "")
  public Long getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Long createDate) {
    this.createDate = createDate;
  }

  
  /**
   * Topic default right number
   * @return rightNum
  **/
  public TopicDetail rightNum(Integer rightNum) {
	this.rightNum = rightNum;
	return this;
  }

	  
  @ApiModelProperty(value = "Topic default right number")
  public Integer getRightNum() {
	return rightNum;
  }

  public void setRightNum(Integer rightNum) {
	this.rightNum = rightNum;
  }

    
  @ApiModelProperty(value = "")
  public Integer getTotalNum() {
		return totalNum;
  }

  public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
  }

  
  @ApiModelProperty(value = "")
  public Integer getTierNum() {
	return tierNum;
  }

  public void setTierNum(Integer tierNum) {
	this.tierNum = tierNum;
  }

  @ApiModelProperty(value = "")
  public Integer getDiscussType() {
	return discussType;
  }

  public void setDiscussType(Integer discussType) {
	this.discussType = discussType;
  }

  @ApiModelProperty(value = "")
  public Long getEndDate() {
	return endDate;
  }

  public void setEndDate(Long endDate) {
	this.endDate = endDate;
  }
  
  @ApiModelProperty(value = "")
  public Integer getCategoryType() {
	return categoryType;
  }

  public void setCategoryType(Integer categoryType) {
	this.categoryType = categoryType;
  }

public TopicDetail() {
		super();
  }

  public TopicDetail(String title, String content) {
		super();
		this.title = title;
		this.content = content;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TopicDetail topicDetail1 = (TopicDetail) o;
    return Objects.equals(this.id, topicDetail1.id) &&
        Objects.equals(this.content, topicDetail1.content) &&
        Objects.equals(this.imgUrls, topicDetail1.imgUrls) &&
        Objects.equals(this.fileUrls, topicDetail1.fileUrls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, content, imgUrls, fileUrls);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TopicDetail1 {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    detail: ").append(toIndentedString(content)).append("\n");
    sb.append("    imgUrls: ").append(toIndentedString(imgUrls)).append("\n");
    sb.append("    fileUrls: ").append(toIndentedString(fileUrls)).append("\n");
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

