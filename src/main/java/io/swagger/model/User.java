package io.swagger.model;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.UserProfile;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.*;
/**
 * User
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
@Entity
@Table(name="user")
public class User implements Serializable  {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id = null;

  @JsonProperty("userid")
  @Column(name="userid")
  private String userid = null;

  @JsonProperty("password")
  @Column(name="password")
  private String password = null;

  @JsonProperty("identifyCode")
  @Transient
  private String identifyCode = null;

  @JsonProperty("userProfile")
  @OneToOne(cascade={CascadeType.ALL})
  @JoinColumn(name="userprofile")
  private UserProfile userProfile = null;

  @JsonProperty("userStatus")
  @Column(name="userstatus")
  private Integer userStatus = 0;
  
  @JsonProperty("role")
  @Column(name="role")
  private String role = "endUser";
  
  

  public User() {
	super();
  }

  public User(String userid, String password, String identifyCode) {
	super();
	this.userid = userid;
	this.password = password;
	this.identifyCode = identifyCode;
  }

  public User id(Long id) {
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

  public User userid(String userid) {
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

  public User password(String password) {
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

  public User identifyCode(String identifyCode) {
    this.identifyCode = identifyCode;
    return this;
  }

   /**
   * Get identifyCode
   * @return identifyCode
  **/
  @ApiModelProperty(required = true, value = "")
  public String getIdentifyCode() {
    return identifyCode;
  }

  public void setIdentifyCode(String identifyCode) {
    this.identifyCode = identifyCode;
  }

  public User userProfile(UserProfile userProfile) {
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

  public User userStatus(Integer userStatus) {
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

  /**
   * User role
   * @return role
  **/
  @ApiModelProperty(value = "User role")
  public String getRole() {
	return role;
  }

  public void setRole(String role) {
	this.role = role;
  }

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.userid, user.userid) &&
        Objects.equals(this.password, user.password) &&
        Objects.equals(this.identifyCode, user.identifyCode) &&
        Objects.equals(this.userProfile, user.userProfile) &&
        Objects.equals(this.userStatus, user.userStatus)&&
        Objects.equals(this.role, user.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userid, password, identifyCode, userProfile, userStatus, role);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    identifyCode: ").append(toIndentedString(identifyCode)).append("\n");
    sb.append("    userProfile: ").append(toIndentedString(userProfile)).append("\n");
    sb.append("    userStatus: ").append(toIndentedString(userStatus)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

