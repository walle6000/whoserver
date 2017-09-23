	package io.swagger.model;
	
	import java.io.Serializable;
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
	import javax.validation.constraints.*;
	/**
	 * User Search
	 */
	@SuppressWarnings("serial")
	@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
	@Entity
	@Table(name="usersearchlog")
	public class UserSearchLog implements Serializable {
	  @JsonProperty("id")
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id = null;
		
	  @JsonProperty("ownId")
	  @Column(name="ownId",nullable=false)
	  private String ownId = null;
		
	  @JsonProperty("userId")
	  @Column(name="userId",nullable=false)
	  private String userId = null;
	  
	  @JsonProperty("userName")
	  @Column(name="userName")
	  private String userName = null;
	  
	  
	  @JsonProperty("userNamePinYin")
	  @Column(name="userNamePinYin")
	  private String userNamePinYin = null;
	  
	  
	  public Long getId() {
		return id;
	  }
	
	  public void setId(Long id) {
		this.id = id;
	  }
	
	/**
	   * Get name
	   * @return name
	  **/
	  @ApiModelProperty(value = "")
	  public String getUserName() {
	    return userName;
	  }
	
	  public void setUserName(String userName) {
	    this.userName = userName;
	  }
	  
	  
	  @ApiModelProperty(value = "")
	  public String getOwnId() {
		return ownId;
	  }
	
	  public void setOwnId(String ownId) {
		this.ownId = ownId;
	  }
	
	  @ApiModelProperty(value = "")
	  public String getUserId() {
		return userId;
	  }
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@ApiModelProperty(value = "")
	public String getUserNamePinYin() {
		return userNamePinYin;
	}

	public void setUserNamePinYin(String userNamePinYin) {
		this.userNamePinYin = userNamePinYin;
	}

	@Override
	  public boolean equals(java.lang.Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }
	    UserSearchLog tag = (UserSearchLog) o;
	    return Objects.equals(this.userId, tag.userId) &&
	        Objects.equals(this.userName, tag.userName) &&
	        Objects.equals(this.ownId, tag.ownId);
	  }
	
	  @Override
	  public int hashCode() {
	    return Objects.hash(ownId,userId,userName);
	  }
	
	  @Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class Tag {\n");
	    sb.append("    ownid: ").append(toIndentedString(ownId)).append("\n");
	    sb.append("    id: ").append(toIndentedString(userId)).append("\n");
	    sb.append("    name: ").append(toIndentedString(userName)).append("\n");
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
	
