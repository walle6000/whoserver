package io.swagger.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.utils.PinYinUtil;
/**
 * Friend
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
public class UserTopicInfo implements Serializable {
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
  
  //-----------------------------------------------
  @JsonProperty("topicId")
  private Long topicId;
  
  @JsonProperty("title")
  private String title = null;
  
  @JsonProperty("content")
  private String content = null;
  
  @JsonProperty("createDate")
  private Long createDate = null;
  
  @JsonProperty("endDate")
  private Long endDate;
  
  @JsonProperty("imgUrls")
  private String imgUrls = null;
  
  @JsonProperty("status")
  private Integer status = null;
  
  @JsonProperty("rightNum")
  private Integer rightNum = null;
  
  @JsonProperty("totalNum")
  private Integer totalNum = null;
  
  @JsonProperty("remainderTime")
  private String remainderTime = null;
  
  @JsonProperty("topicCategory")
  private TopicCategory topicCategory = null;
  
  //-----------------------------------------------
  
  @JsonProperty("inviteTime")
  private Long inviteTime = null;
  
  @JsonProperty("message")
  private String message = null;
  
  //------------------------------------------------
  @JsonProperty("currentTotal")
  private Integer currentTotal = null;
  
  @JsonProperty("currentUserNum")
  private Integer currentUserNum = null;
  
  @JsonProperty("joinedUserInfo")
  private List<Map<String,String>> joinedUserInfo = null;
  
  //------------------------------------------------
  @JsonProperty("sender")
  private String sender = null;
  
  @JsonProperty("receiver")
  private String receiver = null;
//------------------------------------------------
  
  @JsonProperty("topicTopologyId")
  private Long topicTopologyId = null;
  
  @JsonProperty("joinTime")
  private Long joinTime = null;
  
  @JsonProperty("comment")
  private String comment = null;
  
  @JsonProperty("source")
  private Integer source = 0;
  
  @JsonProperty("topicTopologyStatus")
  private String topicTopologyStatus = null;
  
  @JsonProperty("selection")
  private Integer selection = null;
  
  
  public UserTopicInfo() {
	super();
	// TODO Auto-generated constructor stub
  }

  public UserTopicInfo(User user,Topic topic) {
	 super();
	 if(user!=null){
		 this.id = user.getId();
		 this.userid = user.getUserid();
		 this.userName = user.getUserProfile().getUserName();
		 this.userNamePinYin = user.getUserProfile().getUserPinYin()==null?PinYinUtil.getPinYin(this.userName):user.getUserProfile().getUserPinYin();
		 this.headIcon = user.getUserProfile().getHeadIcon();
		 this.thumbnailheadIcon = user.getUserProfile().getThumbnailheadIcon();
		 this.lastLogin = user.getUserProfile().getLastLogin();
		 this.sender = user.getUserid();
	 }
     if(topic!=null){
    	 this.topicId = topic.getId();
    	 this.title = topic.getDetail().getTitle();
    	 this.createDate = topic.getDetail().getCreateDate();
    	 this.endDate = topic.getDetail().getEndDate();
    	 this.content = topic.getDetail().getContent();
    	 this.status = topic.getStatus();
    	 this.imgUrls = topic.getDetail().getImgUrls();
    	 this.rightNum = topic.getDetail().getRightNum();
    	 this.totalNum = topic.getDetail().getTotalNum();
    	 /*if(!StringUtils.isEmpty(topic.getDetail().getImgUrls())){
    		 String[] imgs = topic.getDetail().getImgUrls().split("|");
    		 this.imgUrls = imgs[0];
    	 }*/
    	 Long now = new Date().getTime();
    	 Long day = (this.endDate-now)/(1000*60*60*24);
    	 //Long hour = (this.endDate-now-(1000*60*60*24)*day)/(1000*60*60);
    	 Long hour = (this.endDate-now)/(1000*60*60)%24;
    	 this.remainderTime = String.valueOf(day)+","+String.valueOf(hour);
     }	 
  }
  
  public UserTopicInfo(User user,Topic topic, TopicInviter topicInviter) {
	  this(user,topic);
	  this.inviteTime = topicInviter.getInviteTime();
	  this.message = topicInviter.getMessage();
  }
  
  public UserTopicInfo(User user,Topic topic, TopicTopology topicTopology) {
	  this(user,topic);
	  this.topicTopologyId = topicTopology.getId();
	  this.joinTime = topicTopology.getJoinTime();
	  this.source = topicTopology.getSource();
	  
	  String status = topicTopology.getStatus()==null?"0":topicTopology.getStatus();
	  status = status.lastIndexOf("-")!=-1?status.substring(status.lastIndexOf("-")+1):status;
	  this.topicTopologyStatus = status;
	  
	  String comment = topicTopology.getComment()==null?"None":topicTopology.getComment();
	  comment = comment.lastIndexOf("<|>")!=-1?comment.substring(comment.lastIndexOf("<|>")+3):comment;
	  this.comment = comment;
	  
	  this.selection = topicTopology.getSelection();
  }
  
  
  public UserTopicInfo id(Long id) {
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
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@ApiModelProperty(value = "")
	public Long getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	
	@ApiModelProperty(value = "")
	public String getImgUrls() {
		return imgUrls;
	}
	
	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}
	
	@ApiModelProperty(value = "")
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	
	@ApiModelProperty(value = "")
    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ApiModelProperty(value = "")
	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	
	
	@ApiModelProperty(value = "")
    public Long getInviteTime() {
		return inviteTime;
	}

	public void setInviteTime(Long inviteTime) {
		this.inviteTime = inviteTime;
	}

	@ApiModelProperty(value = "")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	@ApiModelProperty(value = "")
   public Integer getCurrentTotal() {
		return currentTotal;
	}

	public void setCurrentTotal(Integer currentTotal) {
		this.currentTotal = currentTotal;
	}

	@ApiModelProperty(value = "")
	public Integer getCurrentUserNum() {
		return currentUserNum;
	}

	public void setCurrentUserNum(Integer currentUserNum) {
		this.currentUserNum = currentUserNum;
	}
	
	
	@ApiModelProperty(value = "")
    public Integer getRightNum() {
		return rightNum;
	}

	public void setRightNum(Integer rightNum) {
		this.rightNum = rightNum;
	}

	@ApiModelProperty(value = "")
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
	 public UserTopicInfo sender(String sender) {
		    this.sender = sender;
			return this;
	 }
	
	@ApiModelProperty(value = "")
    public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public UserTopicInfo receiver(String receiver) {
	    this.receiver = receiver;
		return this;
   }
	
	@ApiModelProperty(value = "")
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	
	@ApiModelProperty(value = "")
    public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	
	public UserTopicInfo topicCategory(TopicCategory topicCategory) {
	    this.topicCategory = topicCategory;
		return this;
   }
	
	@ApiModelProperty(value = "")
    public TopicCategory getTopicCategory() {
		return topicCategory;
	}

	public void setTopicCategory(TopicCategory topicCategory) {
		this.topicCategory = topicCategory;
	}
	
	
	@ApiModelProperty(value = "")
    public String getRemainderTime() {
		return remainderTime;
	}

	public void setRemainderTime(String remainderTime) {
		this.remainderTime = remainderTime;
	}
	
	
	@ApiModelProperty(value = "")
    public List<Map<String, String>> getJoinedUserInfo() {
		return joinedUserInfo;
	}

	public void setJoinedUserInfo(List<Map<String, String>> joinedUserInfo) {
		this.joinedUserInfo = joinedUserInfo;
	}
	
	
	@ApiModelProperty(value = "")
	public Long getTopicTopologyId() {
		return topicTopologyId;
	}

	public void setTopicTopologyId(Long topicTopologyId) {
		this.topicTopologyId = topicTopologyId;
	}

	@ApiModelProperty(value = "")
	public Long getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Long joinTime) {
		this.joinTime = joinTime;
	}

	@ApiModelProperty(value = "")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@ApiModelProperty(value = "")
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	@ApiModelProperty(value = "")
	public String getTopicTopologyStatus() {
		return topicTopologyStatus;
	}

	public void setTopicTopologyStatus(String topicTopologyStatus) {
		this.topicTopologyStatus = topicTopologyStatus;
	}

	
	@ApiModelProperty(value = "")
	public Integer getSelection() {
		return selection;
	}

	public void setSelection(Integer selection) {
		this.selection = selection;
	}

	public void addJoinedUserInfo(User joinedUser){
		if(this.joinedUserInfo==null){
			this.joinedUserInfo = new ArrayList<Map<String,String>>();
		}
		Map<String,String> info = new HashMap<String,String>();
		info.put("userId", joinedUser.getUserid());
		info.put("headIcon", joinedUser.getUserProfile().getHeadIcon());
		info.put("thumbnailheadIcon", joinedUser.getUserProfile().getThumbnailheadIcon());
		info.put("userName", joinedUser.getUserProfile().getUserName());
		info.put("userPinYin", joinedUser.getUserProfile().getUserPinYin());
		joinedUserInfo.add(info);
	}

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserTopicInfo tag = (UserTopicInfo) o;
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
    sb.append("    nickName: ").append(toIndentedString(userid)).append("\n");
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

