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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
/**
 * Friend
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
@Entity
@Table(name="userfriend")
public class UserFriend implements Serializable {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id = null;

  @JsonProperty("nickName")
  @Column(name="nickName",nullable=false)
  private String nickName = null;
  
  @JsonProperty("nickNamePinYin")
  @Column(name="nickNamePinYin",nullable=false)
  private String nickNamePinYin = null;
  
  @JsonProperty("userId")
  @Column(name="userId",nullable=false)
  private String userId = null;
  
  @JsonProperty("friendId")
  @Column(name="friendId",nullable=false)
  private String friendId = null;
  
  @JsonProperty("labelRelationship")
  @Column(name="labelRelationship",nullable=true)
  private String labelRelationship = null;
  
  @JsonProperty("labelImpression")
  @Column(name="labelImpression",nullable=true)
  private String labelImpression = null;
  
  @JsonProperty("friendOrigin")
  @Column(name="friendOrigin",nullable=true)
  private Integer friendOrigin = null;
  
  @JsonProperty("isFavorite")
  @Column(name="isFavorite",nullable=false)
  private Integer isFavorite = 0;
  
  @JsonProperty("shareFriend")
  @Column(name="shareFriend",nullable=false)
  private Integer shareFriend = 0;

  @JsonProperty("description")
  @Column(name="description",nullable=true)
  private String description;
  
  @JsonProperty("createDate")
  //@Temporal(TemporalType.TIMESTAMP)
  @Column(name = "createdate")
  //@org.hibernate.annotations.CreationTimestamp  
  private Long createDate = null;
  /*
   * 0: pending 
   * 1: confirmed
   * 2: reject
   * */
  @JsonProperty("status")
  @Column(name="status",nullable=false)
  private Integer status = 0;

  public UserFriend id(Long id) {
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

  public UserFriend nickName(String nickName) {
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
  public String getUserId() {
	return userId;
  }

  public void setUserId(String userId) {
	this.userId = userId;
  }
  
  @ApiModelProperty(value = "")
  public String getFriendId() {
	return friendId;
  }

  public void setFriendId(String friendId) {
	this.friendId = friendId;
  }
  
  	@ApiModelProperty(value = "")
	public String getLabelRelationship() {
		return labelRelationship;
	}
	
	public void setLabelRelationship(String labelRelationship) {
		this.labelRelationship = labelRelationship;
	}
	
	@ApiModelProperty(value = "")
	public String getLabelImpression() {
		return labelImpression;
	}
	
	public void setLabelImpression(String labelImpression) {
		this.labelImpression = labelImpression;
	}
	
	@ApiModelProperty(value = "")
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	@ApiModelProperty(value = "")
	public String getNickNamePinYin() {
		return nickNamePinYin;
	}

	public void setNickNamePinYin(String nickNamePinYin) {
		this.nickNamePinYin = nickNamePinYin;
	}
	
	@ApiModelProperty(value = "")
	public Integer getIsFavorite() {
		return isFavorite;
	}
	
	public void setIsFavorite(Integer isFavorite) {
		this.isFavorite = isFavorite;
	}

	@ApiModelProperty(value = "")
	public Integer getFriendOrigin() {
		return friendOrigin;
	}
	
	public void setFriendOrigin(Integer friendOrigin) {
		this.friendOrigin = friendOrigin;
	}

	
	
	@ApiModelProperty(value = "")
    public Integer getShareFriend() {
		return shareFriend;
	}

	public void setShareFriend(Integer shareFriend) {
		this.shareFriend = shareFriend;
	}
	
	
	@ApiModelProperty(value = "")
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@ApiModelProperty(value = "")
	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
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
    UserFriend tag = (UserFriend) o;
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
    sb.append("    nickNamePinYin: ").append(toIndentedString(nickNamePinYin)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    friendId: ").append(toIndentedString(friendId)).append("\n");
    sb.append("    labelRelationship: ").append(toIndentedString(labelRelationship)).append("\n");
    sb.append("    labelImpression: ").append(toIndentedString(labelImpression)).append("\n");
    sb.append("    friendOrigin: ").append(toIndentedString(friendOrigin)).append("\n");
    sb.append("    isFavorite: ").append(toIndentedString(isFavorite)).append("\n");
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
	  return this.nickNamePinYin != null&&this.nickNamePinYin.length()!=0? this.nickNamePinYin.toUpperCase().charAt(0) + "":"";
  }
  
  public String getFirstCharacter(){
	  return this.nickName != null&&this.nickName.length()!=0? this.nickName.charAt(0) + "":"";
  }
}

