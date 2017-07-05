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
 * TopicDetail1
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

public class TopicDetail   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("detail")
  private String detail = null;

  @JsonProperty("imgUrls")
  private List<String> imgUrls = new ArrayList<String>();

  @JsonProperty("fileUrls")
  private List<String> fileUrls = new ArrayList<String>();

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

  public TopicDetail detail(String detail) {
    this.detail = detail;
    return this;
  }

   /**
   * Get detail
   * @return detail
  **/
  @ApiModelProperty(value = "")
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public TopicDetail imgUrls(List<String> imgUrls) {
    this.imgUrls = imgUrls;
    return this;
  }

  public TopicDetail addImgUrlsItem(String imgUrlsItem) {
    this.imgUrls.add(imgUrlsItem);
    return this;
  }

   /**
   * Get imgUrls
   * @return imgUrls
  **/
  @ApiModelProperty(value = "")
  public List<String> getImgUrls() {
    return imgUrls;
  }

  public void setImgUrls(List<String> imgUrls) {
    this.imgUrls = imgUrls;
  }

  public TopicDetail fileUrls(List<String> fileUrls) {
    this.fileUrls = fileUrls;
    return this;
  }

  public TopicDetail addFileUrlsItem(String fileUrlsItem) {
    this.fileUrls.add(fileUrlsItem);
    return this;
  }

   /**
   * Get fileUrls
   * @return fileUrls
  **/
  @ApiModelProperty(value = "")
  public List<String> getFileUrls() {
    return fileUrls;
  }

  public void setFileUrls(List<String> fileUrls) {
    this.fileUrls = fileUrls;
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
        Objects.equals(this.detail, topicDetail1.detail) &&
        Objects.equals(this.imgUrls, topicDetail1.imgUrls) &&
        Objects.equals(this.fileUrls, topicDetail1.fileUrls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, detail, imgUrls, fileUrls);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TopicDetail1 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
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

