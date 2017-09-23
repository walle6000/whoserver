package io.swagger.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.utils.DateUtil;
/**
 * Friend
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-31T03:12:51.178Z")
@Entity
@Table(name="userfriendrequest")
public class UserFriendRequest implements Serializable {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id = null;
  
  @JsonProperty(value="relativeId")
  @Column(name="relativeId",nullable=false)
  private Long relativeId = null;

  @JsonProperty("requestContent")
  @Column(name="requestContent",nullable=false)
  private String requestContent = null;
  
  @JsonProperty("requesterId")
  @Column(name="requesterId",columnDefinition = "varchar(255) default NULL comment '申请人ID'",nullable=false)
  private String requesterId = null;
  
  @JsonProperty("requester")
  @Column(name="requester",columnDefinition = "varchar(255) default NULL comment '申请人名称'", nullable=false)
  private String requester = null;
  
  @JsonProperty("decisionId")
  @Column(name="decisionId",columnDefinition = "varchar(255) default NULL comment '被申请人ID'",nullable=false)
  private String decisionId = null;
  
  @JsonProperty("decisioner")
  @Column(name="decisioner",columnDefinition = "varchar(255) default NULL comment '被申请人名称'",nullable=false)
  private String decisioner = null;
  
  @JsonProperty(value="createdate")
  //@Temporal(TemporalType.TIMESTAMP)
  @Column(name = "createDate", nullable = false)
  private Long createDate = null;
  
  @JsonProperty(value="hujuId")
  @Column(name="hujuId",nullable=false)
  private Long hujuId = null;
  
  @JsonProperty(value="thumbnailHeadicon")
  @Column(name="thumbnailHeadicon",nullable=false)
  private String thumbnailHeadicon = null;
  
  @JsonProperty("friendOrigin")
  @Column(name="friendOrigin",nullable=true)
  private Integer friendOrigin = null;
  /*
   * 0: pending
   * 1: approve
   * 2: reject
   * */
  @JsonProperty("status")
  @NotNull
  @Column(name="status",nullable=false)
  private Integer status = 0;

  public UserFriendRequest id(Long id) {
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

  public UserFriendRequest requestContent(String requestContent) {
    this.requestContent = requestContent;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getRequestContent() {
    return requestContent;
  }

  public void setRequestContent(String requestContent) {
    this.requestContent = requestContent;
  }

  @ApiModelProperty(value = "")
  public String getRequester() {
	return requester;
  }

  public void setRequester(String requester) {
	this.requester = requester;
  }
  
  @ApiModelProperty(value = "")
  public String getDecisionId() {
	return decisionId;
  }

  public void setDecisionId(String decisionId) {
	this.decisionId = decisionId;
  }
  
  @ApiModelProperty(value = "")
  public Long getRelativeId() {
	return relativeId;
  }

  public void setRelativeId(Long relativeId) {
	this.relativeId = relativeId;
  }

  @ApiModelProperty(value = "")
  public Long getCreateDate() {
	return createDate;
  }

  public void setCreateDate(Long createDate) {
	this.createDate = createDate;
  }
  @ApiModelProperty(value = "")
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@ApiModelProperty(value = "")
	public String getRequesterId() {
		return requesterId;
	}
	
	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}
	
	@ApiModelProperty(value = "")
	public String getDecisioner() {
		return decisioner;
	}
	
	public void setDecisioner(String decisioner) {
		this.decisioner = decisioner;
	}
	
	@ApiModelProperty(value = "")
	public Long getHujuId() {
		return hujuId;
	}
	
	public void setHujuId(Long hujuId) {
		this.hujuId = hujuId;
	}
	
	@ApiModelProperty(value = "")
	public String getThumbnailHeadicon() {
		return thumbnailHeadicon;
	}
	
	public void setThumbnailHeadicon(String thumbnailHeadicon) {
		this.thumbnailHeadicon = thumbnailHeadicon;
	}
	
	@ApiModelProperty(value = "")
    public Integer getFriendOrigin() {
		return friendOrigin;
	}

	public void setFriendOrigin(Integer friendOrigin) {
		this.friendOrigin = friendOrigin;
	}

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserFriendRequest tag = (UserFriendRequest) o;
    return Objects.equals(this.id, tag.id) &&
        Objects.equals(this.requestContent, tag.requestContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, requestContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tag {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    requestContent: ").append(toIndentedString(requestContent)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public String getRequestDate(){
	  return DateUtil.toStrYMD(createDate);
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

