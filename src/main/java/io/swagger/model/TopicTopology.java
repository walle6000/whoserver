package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
/**
 * TopicTopology
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

public class TopicTopology   {
  @JsonProperty("topicId")
  private Long topicId = null;

  @JsonProperty("sender")
  private Long sender = null;

  @JsonProperty("recivers")
  private List<Long> recivers = new ArrayList<Long>();

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

  public TopicTopology sender(Long sender) {
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

  public TopicTopology recivers(List<Long> recivers) {
    this.recivers = recivers;
    return this;
  }

  public TopicTopology addReciversItem(Long reciversItem) {
    this.recivers.add(reciversItem);
    return this;
  }

   /**
   * Get recivers
   * @return recivers
  **/
  @ApiModelProperty(value = "")
  public List<Long> getRecivers() {
    return recivers;
  }

  public void setRecivers(List<Long> recivers) {
    this.recivers = recivers;
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
        Objects.equals(this.recivers, topicTopology.recivers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topicId, sender, recivers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TopicTopology {\n");
    
    sb.append("    topicId: ").append(toIndentedString(topicId)).append("\n");
    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    recivers: ").append(toIndentedString(recivers)).append("\n");
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

