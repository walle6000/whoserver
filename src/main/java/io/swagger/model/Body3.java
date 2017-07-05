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
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import javax.validation.constraints.*;
/**
 * Body3
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

public class Body3   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("category")
  private TopicCategory category = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("createDate")
  private DateTime createDate = null;

  @JsonProperty("createUser")
  private String createUser = null;

  @JsonProperty("rightNum")
  private Integer rightNum = null;

  @JsonProperty("detail")
  private TopicDetail detail = null;

  @JsonProperty("tags")
  private List<TopicCategory> tags = new ArrayList<TopicCategory>();

  /**
   * topic status
   */
  public enum StatusEnum {
    AVAILABLE("available"),
    
    PENDING("pending"),
    
    CLOSE("close");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("complete")
  private Boolean complete = false;

  public Body3 id(Long id) {
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

  public Body3 category(TopicCategory category) {
    this.category = category;
    return this;
  }

   /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(value = "")
  public TopicCategory getCategory() {
    return category;
  }

  public void setCategory(TopicCategory category) {
    this.category = category;
  }

  public Body3 title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(example = "recruit coder", required = true, value = "")
  @NotNull
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Body3 createDate(DateTime createDate) {
    this.createDate = createDate;
    return this;
  }

   /**
   * Get createDate
   * @return createDate
  **/
  @ApiModelProperty(value = "")
  public DateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(DateTime createDate) {
    this.createDate = createDate;
  }

  public Body3 createUser(String createUser) {
    this.createUser = createUser;
    return this;
  }

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

  public Body3 rightNum(Integer rightNum) {
    this.rightNum = rightNum;
    return this;
  }

   /**
   * Topic default right number
   * @return rightNum
  **/
  @ApiModelProperty(value = "Topic default right number")
  public Integer getRightNum() {
    return rightNum;
  }

  public void setRightNum(Integer rightNum) {
    this.rightNum = rightNum;
  }

  public Body3 detail(TopicDetail detail) {
    this.detail = detail;
    return this;
  }

   /**
   * Get detail
   * @return detail
  **/
  @ApiModelProperty(value = "")
  public TopicDetail getDetail() {
    return detail;
  }

  public void setDetail(TopicDetail detail) {
    this.detail = detail;
  }

  public Body3 tags(List<TopicCategory> tags) {
    this.tags = tags;
    return this;
  }

  public Body3 addTagsItem(TopicCategory tagsItem) {
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * Get tags
   * @return tags
  **/
  @ApiModelProperty(value = "")
  public List<TopicCategory> getTags() {
    return tags;
  }

  public void setTags(List<TopicCategory> tags) {
    this.tags = tags;
  }

  public Body3 status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * topic status
   * @return status
  **/
  @ApiModelProperty(value = "topic status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Body3 complete(Boolean complete) {
    this.complete = complete;
    return this;
  }

   /**
   * Get complete
   * @return complete
  **/
  @ApiModelProperty(value = "")
  public Boolean getComplete() {
    return complete;
  }

  public void setComplete(Boolean complete) {
    this.complete = complete;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body3 body3 = (Body3) o;
    return Objects.equals(this.id, body3.id) &&
        Objects.equals(this.category, body3.category) &&
        Objects.equals(this.title, body3.title) &&
        Objects.equals(this.createDate, body3.createDate) &&
        Objects.equals(this.createUser, body3.createUser) &&
        Objects.equals(this.rightNum, body3.rightNum) &&
        Objects.equals(this.detail, body3.detail) &&
        Objects.equals(this.tags, body3.tags) &&
        Objects.equals(this.status, body3.status) &&
        Objects.equals(this.complete, body3.complete);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, category, title, createDate, createUser, rightNum, detail, tags, status, complete);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body3 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
    sb.append("    createUser: ").append(toIndentedString(createUser)).append("\n");
    sb.append("    rightNum: ").append(toIndentedString(rightNum)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    complete: ").append(toIndentedString(complete)).append("\n");
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

