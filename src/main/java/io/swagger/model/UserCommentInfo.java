package io.swagger.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
/**
 * Friend
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
public class UserCommentInfo implements Serializable {
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
  
  @JsonProperty("comment")
  private Comment comment = null;
  
  
  public UserCommentInfo() {
	super();
	// TODO Auto-generated constructor stub
  }

  public UserCommentInfo(User user,Comment comment) {
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
	 if(comment!=null){
    	 this.comment = comment;
     }	
  }
  
  
 
  
  
public UserCommentInfo id(Long id) {
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
public Comment getComment() {
	return comment;
}

public void setComment(Comment comment) {
	this.comment = comment;
}

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserCommentInfo tag = (UserCommentInfo) o;
    return Objects.equals(this.id, tag.id) &&
        Objects.equals(this.userid, tag.userid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tag {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
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

