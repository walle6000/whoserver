package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import javax.validation.constraints.*;
/**
 * InlineResponse2005
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

public class InlineResponse2005   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("topicId")
  private Long topicId = null;

  @JsonProperty("sender")
  private Long sender = null;

  @JsonProperty("recivers")
  private List<Long> recivers = new ArrayList<Long>();

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("createDate")
  private DateTime createDate = null;

  public InlineResponse2005 id(Long id) {
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

  public InlineResponse2005 topicId(Long topicId) {
    this.topicId = topicId;
    return this;
  }

   /**
   * Get topicId
   * @return topicId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

  public InlineResponse2005 sender(Long sender) {
    this.sender = sender;
    return this;
  }

   /**
   * Get sender
   * @return sender
  **/
  @ApiModelProperty(value = "")
  public Long getSender() {
    return sender;
  }

  public void setSender(Long sender) {
    this.sender = sender;
  }

  public InlineResponse2005 recivers(List<Long> recivers) {
    this.recivers = recivers;
    return this;
  }

  public InlineResponse2005 addReciversItem(Long reciversItem) {
    this.recivers.add(reciversItem);
    return this;
  }

   /**
   * Get recivers
   * @return recivers
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public List<Long> getRecivers() {
    return recivers;
  }

  public void setRecivers(List<Long> recivers) {
    this.recivers = recivers;
  }

  public InlineResponse2005 content(String content) {
    this.content = content;
    return this;
  }

   /**
   * Get content
   * @return content
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public InlineResponse2005 createDate(DateTime createDate) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2005 inlineResponse2005 = (InlineResponse2005) o;
    return Objects.equals(this.id, inlineResponse2005.id) &&
        Objects.equals(this.topicId, inlineResponse2005.topicId) &&
        Objects.equals(this.sender, inlineResponse2005.sender) &&
        Objects.equals(this.recivers, inlineResponse2005.recivers) &&
        Objects.equals(this.content, inlineResponse2005.content) &&
        Objects.equals(this.createDate, inlineResponse2005.createDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, topicId, sender, recivers, content, createDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2005 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    topicId: ").append(toIndentedString(topicId)).append("\n");
    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    recivers: ").append(toIndentedString(recivers)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
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

