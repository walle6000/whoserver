package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.InlineResponse2001UserProfile;
import javax.validation.constraints.*;
/**
 * InlineResponse2001
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

public class InlineResponse2001   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("userid")
  private String userid = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("identifyCode")
  private String identifyCode = null;

  @JsonProperty("userProfile")
  private InlineResponse2001UserProfile userProfile = null;

  @JsonProperty("userStatus")
  private Integer userStatus = null;

  public InlineResponse2001 id(Long id) {
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

  public InlineResponse2001 userid(String userid) {
    this.userid = userid;
    return this;
  }

   /**
   * Get userid
   * @return userid
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public InlineResponse2001 password(String password) {
    this.password = password;
    return this;
  }

   /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public InlineResponse2001 identifyCode(String identifyCode) {
    this.identifyCode = identifyCode;
    return this;
  }

   /**
   * Get identifyCode
   * @return identifyCode
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getIdentifyCode() {
    return identifyCode;
  }

  public void setIdentifyCode(String identifyCode) {
    this.identifyCode = identifyCode;
  }

  public InlineResponse2001 userProfile(InlineResponse2001UserProfile userProfile) {
    this.userProfile = userProfile;
    return this;
  }

   /**
   * Get userProfile
   * @return userProfile
  **/
  @ApiModelProperty(value = "")
  public InlineResponse2001UserProfile getUserProfile() {
    return userProfile;
  }

  public void setUserProfile(InlineResponse2001UserProfile userProfile) {
    this.userProfile = userProfile;
  }

  public InlineResponse2001 userStatus(Integer userStatus) {
    this.userStatus = userStatus;
    return this;
  }

   /**
   * User Status
   * @return userStatus
  **/
  @ApiModelProperty(value = "User Status")
  public Integer getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(Integer userStatus) {
    this.userStatus = userStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2001 inlineResponse2001 = (InlineResponse2001) o;
    return Objects.equals(this.id, inlineResponse2001.id) &&
        Objects.equals(this.userid, inlineResponse2001.userid) &&
        Objects.equals(this.password, inlineResponse2001.password) &&
        Objects.equals(this.identifyCode, inlineResponse2001.identifyCode) &&
        Objects.equals(this.userProfile, inlineResponse2001.userProfile) &&
        Objects.equals(this.userStatus, inlineResponse2001.userStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userid, password, identifyCode, userProfile, userStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2001 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    identifyCode: ").append(toIndentedString(identifyCode)).append("\n");
    sb.append("    userProfile: ").append(toIndentedString(userProfile)).append("\n");
    sb.append("    userStatus: ").append(toIndentedString(userStatus)).append("\n");
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

