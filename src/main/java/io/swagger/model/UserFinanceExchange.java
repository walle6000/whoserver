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
 * Tag
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
@Entity
@Table(name = "userfinanceexchange")
public class UserFinanceExchange implements Serializable {
	@JsonProperty("id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	@JsonProperty("transactionNo")
	@Column(name = "transactionno", nullable = false)
	private Long transactionNo = null;

	@JsonProperty("userId")
	@Column(name = "userid", nullable = false)
	private String userId = null;


	@JsonProperty("amount")
	@Column(name = "amount", nullable = false)
	private Integer amount = null;
	
	/*
	 * gainType
	 * 0: deposit
	 * 1： reward
	 * 2： transmission
	 * 3： draw
	 */
	@JsonProperty("amountType")
	@Column(name = "amounttype", nullable = false)
	private Integer amountType = null;
	
	/**
	 * status:
	 * 0: unconfirmed
	 * 1: confirmed 
	 */
	@JsonProperty("status")
	@Column(name = "status", nullable = false)
	private Integer status = 0;

	
	@JsonProperty("exchangeTime")
	@Column(name = "exchangetime")
	private Long exchangeTime = null;

	public UserFinanceExchange() {
		super();
	}

	public UserFinanceExchange id(Long id) {
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
	public Long getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(Long transactionNo) {
		this.transactionNo = transactionNo;
	}


	@ApiModelProperty(value = "")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ApiModelProperty(value = "")
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@ApiModelProperty(value = "")
	public Integer getAmountType() {
		return amountType;
	}

	public void setAmountType(Integer amountType) {
		this.amountType = amountType;
	}

	@ApiModelProperty(value = "")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ApiModelProperty(value = "")
	public Long getExchangeTime() {
		return exchangeTime;
	}

	public void setExchangeTime(Long exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserFinanceExchange tag = (UserFinanceExchange) o;
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
