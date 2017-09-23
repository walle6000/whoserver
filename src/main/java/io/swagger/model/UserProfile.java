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
 * UserUserProfile
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")

@Entity
@Table(name="userprofile")
public class UserProfile implements Serializable  {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id = null;

  @JsonProperty("userName")
  @Column(name="username")
  private String userName = null;
  
  @JsonProperty("userPinYin")
  @Column(name="userpinyin")
  private String userPinYin = null;
  
  @JsonProperty("gender")
  @Column(name="gender")
  private String gender = null;

  @JsonProperty("email")
  @Column(name="email")
  private String email = null;

  @JsonProperty("phone")
  @Column(name="phone")
  private String phone = null;

  @JsonProperty("weixin")
  @Column(name="weixin")
  private String weixin = null;

  @JsonProperty("qq")
  @Column(name="qq")
  private String qq = null;

  @JsonProperty("headIcon")
  @Column(name="headIcon")
  private String headIcon = null;
  
  @JsonProperty("thumbnailheadIcon")
  @Column(name="thumbnailheadIcon")
  private String thumbnailheadIcon = null;
  
  @JsonProperty("birthDay")
  //@Temporal(TemporalType.TIMESTAMP)
  @Column(name = "birthday")
  private String birthDay = null;
  
  @JsonProperty("homeTown")
  @Column(name = "hometown")
  private String homeTown = null;

  @JsonProperty("summry")
  @Column(name="summry")
  private String summry = null;
  
  @JsonProperty("tag")
  @Column(name="tag")
  private String tag;
  
  @JsonProperty("others")
  @Column(name="others")
  private String others;
  
  @Lob
  @Column(name=" qrCode", columnDefinition="BLOB", nullable=true) 
  private byte[] qrCode = null;
  
  @JsonProperty("lastLogin")
  //@Temporal(TemporalType.TIMESTAMP)
  @Column(name = "lastLogin")
  private Long lastLogin = null;

  public UserProfile id(Long id) {
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

  public UserProfile userName(String userName) {
    this.userName = userName;
    return this;
  }

   /**
   * Get userName
   * @return userName
  **/
  @ApiModelProperty(value = "")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public UserProfile email(String email) {
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserProfile phone(String phone) {
    this.phone = phone;
    return this;
  }

   /**
   * Get phone
   * @return phone
  **/
  @ApiModelProperty(value = "")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public UserProfile weixin(String weixin) {
    this.weixin = weixin;
    return this;
  }

   /**
   * Get weixin
   * @return weixin
  **/
  @ApiModelProperty(value = "")
  public String getWeixin() {
    return weixin;
  }

  public void setWeixin(String weixin) {
    this.weixin = weixin;
  }

  public UserProfile qq(String qq) {
    this.qq = qq;
    return this;
  }

   /**
   * Get qq
   * @return qq
  **/
  @ApiModelProperty(value = "")
  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public UserProfile headIcon(String headIcon) {
    this.headIcon = headIcon;
    return this;
  }

   /**
   * Get headIcon
   * @return headIcon
  **/
  @ApiModelProperty(value = "")
  public String getHeadIcon() {
    return headIcon;
  }

  public void setHeadIcon(String headIcon) {
    this.headIcon = headIcon;
  }

  
  @ApiModelProperty(value = "")
  public String getThumbnailheadIcon() {
	return thumbnailheadIcon;
  }

  public void setThumbnailheadIcon(String thumbnailheadIcon) {
	this.thumbnailheadIcon = thumbnailheadIcon;
  }

  public UserProfile summry(String summry) {
    this.summry = summry;
    return this;
  }

  
   /**
   * Get summry
   * @return summry
  **/
  @ApiModelProperty(value = "")
  public String getSummry() {
    return summry;
  }

  public void setSummry(String summry) {
    this.summry = summry;
  }
  

  @ApiModelProperty(value = "")
  public byte[] getQrCode() {
	return qrCode;
  }

  public void setQrCode(byte[] qrCode) {
	this.qrCode = qrCode;
  }
  
  
  @ApiModelProperty(value = "")
  public String getUserPinYin() {
	return userPinYin;
  }

  public void setUserPinYin(String userPinYin) {
	this.userPinYin = userPinYin;
  }

  @ApiModelProperty(value = "")
  public Long getLastLogin() {
	return lastLogin;
  }

  public void setLastLogin(Long lastLogin) {
	this.lastLogin = lastLogin;
  }
  
  @ApiModelProperty(value = "")
  public String getGender() {
	return gender;
  }

  public void setGender(String gender) {
	this.gender = gender;
  }

  @ApiModelProperty(value = "")
  public String getBirthDay() {
	return birthDay;
  }

  public void setBirthDay(String birthDay) {
	this.birthDay = birthDay;
  }

  @ApiModelProperty(value = "")
  public String getHomeTown() {
	return homeTown;
  }

  public void setHomeTown(String homeTown) {
	this.homeTown = homeTown;
  }

  
  @ApiModelProperty(value = "")
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@ApiModelProperty(value = "")
	public String getOthers() {
		return others;
	}

public void setOthers(String others) {
	this.others = others;
}

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserProfile userUserProfile = (UserProfile) o;
    return Objects.equals(this.id, userUserProfile.id) &&
        Objects.equals(this.userName, userUserProfile.userName) &&
        Objects.equals(this.email, userUserProfile.email) &&
        Objects.equals(this.phone, userUserProfile.phone) &&
        Objects.equals(this.weixin, userUserProfile.weixin) &&
        Objects.equals(this.qq, userUserProfile.qq) &&
        Objects.equals(this.headIcon, userUserProfile.headIcon) &&
        Objects.equals(this.summry, userUserProfile.summry);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, email, phone, weixin, qq, headIcon, summry);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserUserProfile {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    weixin: ").append(toIndentedString(weixin)).append("\n");
    sb.append("    qq: ").append(toIndentedString(qq)).append("\n");
    sb.append("    headIcon: ").append(toIndentedString(headIcon)).append("\n");
    sb.append("    birthDay: ").append(toIndentedString(birthDay)).append("\n");
    sb.append("    summry: ").append(toIndentedString(summry)).append("\n");
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

