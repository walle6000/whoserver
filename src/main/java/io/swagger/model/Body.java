package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.UserProfile;
import javax.validation.constraints.*;
/**
 * Body
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

public class Body   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("userid")
  private String userid = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("identifyCode")
  private String identifyCode = null;

  @JsonProperty("userProfile")
  private UserProfile userProfile = null;

  @JsonProperty("userStatus")
  private Integer userStatus = null;

  public Body id(Long id) {
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

  public Body userid(String userid) {
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

  public Body password(String password) {
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

  public Body identifyCode(String identifyCode) {
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

  public Body userProfile(UserProfile userProfile) {
    this.userProfile = userProfile;
    return this;
  }

   /**
   * Get userProfile
   * @return userProfile
  **/
  @ApiModelProperty(value = "")
  public UserProfile getUserProfile() {
    return userProfile;
  }

  public void setUserProfile(UserProfile userProfile) {
    this.userProfile = userProfile;
  }

  public Body userStatus(Integer userStatus) {
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
    Body body = (Body) o;
    return Objects.equals(this.id, body.id) &&
        Objects.equals(this.userid, body.userid) &&
        Objects.equals(this.password, body.password) &&
        Objects.equals(this.identifyCode, body.identifyCode) &&
        Objects.equals(this.userProfile, body.userProfile) &&
        Objects.equals(this.userStatus, body.userStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userid, password, identifyCode, userProfile, userStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body {\n");
    
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

