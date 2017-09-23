package io.swagger.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.utils.DateUtil;
import io.swagger.utils.PinYinUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;

import org.assertj.core.util.Compatibility.System;
/**
 * Friend
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
public class UserFriendInfo implements Serializable {
  @JsonProperty("id")
  private Long id = null;
  
  @JsonProperty("userid")
  private String userid = null;
  
  @JsonProperty("userName")
  private String userName = null;
  
  @JsonProperty("userNamePinYin")
  private String userNamePinYin = null;
  
  @JsonProperty("headIcon")
  private String headIcon = null;
  
  @JsonProperty("thumbnailheadIcon")
  private String thumbnailheadIcon = null;
  
  @JsonProperty("lastLogin")
  private Long lastLogin = null;

  @JsonProperty("nickName")
  private String nickName = null;
  
  @JsonProperty("nickNamePinYin")
  private String nickNamePinYin = null;
  
  @JsonProperty("friendDays")
  private Long friendDays = 0L;
  
  @JsonProperty("labelRelationship")
  private String labelRelationship = null;
  
  @JsonProperty("labelImpression")
  private String labelImpression = null;
  
  @JsonProperty("friendOrigin")
  private Integer friendOrigin = null;
  
  @JsonProperty("description")
  private String description = null;
  
  public UserFriendInfo() {
	super();
	// TODO Auto-generated constructor stub
  }

  public UserFriendInfo(User user,UserFriend userFriend) {
	 super();
	 if(user!=null){
		 this.id = user.getId();
		 this.userid = user.getUserid();
		 this.userName = user.getUserProfile().getUserName();
		 this.userNamePinYin = user.getUserProfile().getUserPinYin()==null?PinYinUtil.getPinYin(this.userName):user.getUserProfile().getUserPinYin();
		 this.headIcon = user.getUserProfile().getHeadIcon();
		 this.thumbnailheadIcon = user.getUserProfile().getThumbnailheadIcon();
		 this.lastLogin = user.getUserProfile().getLastLogin();
	 }
     if(userFriend!=null){
    	 this.nickName = userFriend.getNickName();
    	 this.nickNamePinYin = userFriend.getNickNamePinYin()==null?PinYinUtil.getPinYin(this.nickName):userFriend.getNickNamePinYin();
    	 this.friendDays = userFriend.getCreateDate()!=null?DateUtil.getSpanDays(new Date().getTime(), userFriend.getCreateDate()):0L;
    	 this.labelRelationship = userFriend.getLabelRelationship();
    	 this.labelImpression = userFriend.getLabelImpression();
    	 this.friendOrigin = userFriend.getFriendOrigin();
    	 this.description = userFriend.getDescription();
     }	 
  }
  
  
public UserFriendInfo id(Long id) {
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

  public UserFriendInfo nickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  
 
  @ApiModelProperty(value = "")
public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

@ApiModelProperty(value = "")
public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

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

@ApiModelProperty(value = "")
public Long getLastLogin() {
	return lastLogin;
}

public void setLastLogin(Long lastLogin) {
	this.lastLogin = lastLogin;
}


@ApiModelProperty(value = "")
public String getUserNamePinYin() {
	return userNamePinYin;
}

public void setUserNamePinYin(String userNamePinYin) {
	this.userNamePinYin = userNamePinYin;
}

@ApiModelProperty(value = "")
public String getNickNamePinYin() {
	return nickNamePinYin;
}

public void setNickNamePinYin(String nickNamePinYin) {
	this.nickNamePinYin = nickNamePinYin;
}
@ApiModelProperty(value = "")
public Long getFriendDays() {
	return friendDays;
}

public void setFriendDays(Long friendDays) {
	this.friendDays = friendDays;
}
@ApiModelProperty(value = "")
public String getLabelRelationship() {
	return labelRelationship;
}

public void setLabelRelationship(String labelRelationship) {
	this.labelRelationship = labelRelationship;
}

@ApiModelProperty(value = "")
public Integer getFriendOrigin() {
	return friendOrigin;
}

public void setFriendOrigin(Integer friendOrigin) {
	this.friendOrigin = friendOrigin;
}

@ApiModelProperty(value = "")
public String getLabelImpression() {
	return labelImpression;
}

public void setLabelImpression(String labelImpression) {
	this.labelImpression = labelImpression;
}

@ApiModelProperty(value = "")
public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserFriendInfo tag = (UserFriendInfo) o;
    return Objects.equals(this.id, tag.id) &&
        Objects.equals(this.nickName, tag.nickName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nickName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tag {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nickName: ").append(toIndentedString(nickName)).append("\n");
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
  
  public String getFirstAlphabet(){
	  return this.userNamePinYin != null&&this.userNamePinYin.length()!=0? this.userNamePinYin.toUpperCase().charAt(0) + "":"";
  }
  
  public String getFirstCharacter(){
	  return this.userName != null&&this.userName.length()!=0? this.userName.charAt(0) + "":"";
  }
}

