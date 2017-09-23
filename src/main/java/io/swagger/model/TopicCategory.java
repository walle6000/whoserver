package io.swagger.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.*;
/**
 * TopicCategory1
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
@Entity
@Table(name="topiccategory")
public class TopicCategory implements Serializable  {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id = null;

  @JsonProperty("name")
  @Column(name="name",nullable=false)
  private String name = null;
  
  @JsonProperty("userId")
  @Column(name="userid",nullable=false)
  private String userId = null;
  
  @JsonProperty("categoryType")
  @Column(name="categorytype",nullable=false)
  private Integer categoryType = null;
  
  @JsonProperty("ratio")
  @Column(name="ratio",nullable=false)
  private Integer ratio;
  
  @JsonProperty("period")
  @Column(name="period",nullable=false)
  private Integer period;
  
  @JsonProperty("rightNum")
  @Column(name="rightnum",nullable=false)
  private Integer rightNum;
  
  @JsonProperty("tierNum")
  @Column(name="tiernum",nullable=true)
  private Integer tierNum;
  
  @JsonProperty("totalNum")
  @Column(name="totalnum",nullable=true)
  private Integer totalNum;
  
  @JsonProperty("totalRewardNum")
  @Column(name="totalrewardnum",nullable=false)
  private Integer totalRewardNum;
  
  @JsonProperty("discussType")
  @Column(name="discusstype",nullable=false)
  private Integer discussType;
  
  @JsonProperty("status")
  @Column(name="status",nullable=false)
  private Integer status = 0;
  

  public TopicCategory id(Long id) {
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

  public TopicCategory name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  
  @ApiModelProperty(value = "")
  public String getUserId() {
	return userId;
  }

  public void setUserId(String userId) {
	this.userId = userId;
  }

  @ApiModelProperty(value = "")
  public Integer getCategoryType() {
	return categoryType;
  }

  public void setCategoryType(Integer categoryType) {
	this.categoryType = categoryType;
  }

  @ApiModelProperty(value = "")
  public Integer getRatio() {
	return ratio;
  }

  public void setRatio(Integer ratio) {
	this.ratio = ratio;
  }

  @ApiModelProperty(value = "")
  public Integer getPeriod() {
	return period;
  }

  public void setPeriod(Integer period) {
	this.period = period;
  }

  @ApiModelProperty(value = "")
  public Integer getRightNum() {
	return rightNum;
  }

  public void setRightNum(Integer rightNum) {
	this.rightNum = rightNum;
  }

  @ApiModelProperty(value = "")
  public Integer getTierNum() {
	return tierNum;
  }

  public void setTierNum(Integer tierNum) {
	this.tierNum = tierNum;
  }

  @ApiModelProperty(value = "")
  public Integer getTotalNum() {
	return totalNum;
  }

  public void setTotalNum(Integer totalNum) {
	this.totalNum = totalNum;
  }

  @ApiModelProperty(value = "")
  public Integer getTotalRewardNum() {
	return totalRewardNum;
  }

  public void setTotalRewardNum(Integer totalRewardNum) {
	this.totalRewardNum = totalRewardNum;
  }

  @ApiModelProperty(value = "")
  public Integer getDiscussType() {
	return discussType;
  }

  public void setDiscussType(Integer discussType) {
	this.discussType = discussType;
  }
  
  
  @ApiModelProperty(value = "")
  public Integer getStatus() {
	return status;
  }

  public void setStatus(Integer status) {
	this.status = status;
  }

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TopicCategory topicCategory1 = (TopicCategory) o;
    return Objects.equals(this.id, topicCategory1.id) &&
        Objects.equals(this.name, topicCategory1.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TopicCategory1 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

