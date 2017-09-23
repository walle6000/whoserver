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
 * Tag
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
@Entity
@Table(name = "topicfund")
public class TopicFund implements Serializable {
	@JsonProperty("id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	@JsonProperty("totoalAmount")
	@Column(name = "totoalamount", nullable = false)
	private Integer totoalAmount = null;

	@JsonProperty("ratio")
	@Column(name = "ratio", nullable = false)
	private Integer ratio = null;

	@JsonProperty("transmissionAmount")
	@Column(name = "transmissionamount", nullable = false)
	private Integer transmissionAmount = null;

	@JsonProperty("rewardAmount")
	@Column(name = "rewardamount", nullable = false)
	private Integer rewardAmount = null;
	
	/**
	 * status:
	 * 0: unconfirmed
	 * 1: confirmed 
	 */
	@JsonProperty("status")
	@Column(name = "status", nullable = false)
	private Integer status = 0;

	/*
	 * asignType: 0: random assignment 1: average assignment
	 */
	@JsonProperty("assignType")
	@Column(name = "assigntype", nullable = false)
	private Integer assignType = null;
	
	@JsonProperty("totalRewardNum")
	@Column(name="totalrewardnum",nullable=true)
	private Integer totalRewardNum;

	public TopicFund() {
		super();
	}

	public TopicFund(Integer totoalAmount, Integer ratio) {
		super();
		this.totoalAmount = totoalAmount;
		this.ratio = ratio;
		this.assignType = 0;
		this.transmissionAmount = this.totoalAmount * this.ratio / 100;
		this.rewardAmount = this.totoalAmount - this.transmissionAmount;
	}

	public TopicFund(Integer totoalAmount, Integer ratio, Integer assignType) {
		super();
		this.totoalAmount = totoalAmount;
		this.ratio = ratio;
		this.assignType = assignType;
		this.transmissionAmount = this.totoalAmount * this.ratio / 100;
		this.rewardAmount = this.totoalAmount - this.transmissionAmount;
	}

	public TopicFund id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
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
	public Integer getTotoalAmount() {
		return totoalAmount;
	}

	public void setTotoalAmount(Integer totoalAmount) {
		this.totoalAmount = totoalAmount;
	}

	@ApiModelProperty(value = "")
	public Integer getRatio() {
		return ratio;
	}

	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}

	@ApiModelProperty(value = "")
	public Integer getTransmissionAmount() {
		return transmissionAmount;
	}

	public void setTransmissionAmount(Integer transmissionAmount) {
		this.transmissionAmount = transmissionAmount;
	}

	@ApiModelProperty(value = "")
	public Integer getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(Integer rewardAmount) {
		this.rewardAmount = rewardAmount;
	}
	
	
	@ApiModelProperty(value = "")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ApiModelProperty(value = "")
	public Integer getAssignType() {
		return assignType;
	}

	public void setAssignType(Integer assignType) {
		this.assignType = assignType;
	}

	
	@ApiModelProperty(value = "")
	public Integer getTotalRewardNum() {
		return totalRewardNum;
	}

	public void setTotalRewardNum(Integer totalRewardNum) {
		this.totalRewardNum = totalRewardNum;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TopicFund tag = (TopicFund) o;
		return Objects.equals(this.id, tag.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Tag {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
