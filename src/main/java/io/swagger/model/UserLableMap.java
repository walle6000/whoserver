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
 * UserLableMap
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
@Entity
@Table(name = "userlablemap")
public class UserLableMap implements Serializable {
	@JsonProperty("id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	@JsonProperty("lableid")
	@Column(name = "lableid")
	private Integer lableId = 0;

	//labletype( 1:lableRelationship; 2:lableImpression )
	@JsonProperty("labletype")
	@Column(name = "labletype")
	private Integer lableType = 0;

	@JsonProperty("lablerelationship")
	@Column(name = "lableRelationship", nullable = true, unique = true)
	private String lableRelationship = null;

	@JsonProperty("lableimpression")
	@Column(name = "lableimpression", nullable = true, unique = true)
	private String lableImpression = null;

	public UserLableMap id(Long id) {
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

	/**
	 * Get lableId
	 * 
	 * @return lableId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull
	public Integer getlableId() {
		return lableId;
	}

	public void setlableId(Integer lableId) {
		this.lableId = lableId;
	}

	/**
	 * Get lableType
	 * 
	 * @return lableType
	 **/
	@ApiModelProperty(required = true, value = "")
	public Integer getlableType() {
		return lableType;
	}

	public void setlableType(Integer lableType) {
		this.lableType = lableType;
	}

	/**
	 * Get lableRelationship
	 * 
	 * @return lableRelationship
	 **/
	@ApiModelProperty(value = "")
	public String getlableRelationship() {
		return lableRelationship;
	}

	public void setlableRelationship(String lableRelationship) {
		this.lableRelationship = lableRelationship;
	}

	/**
	 * Get lableImpression
	 * 
	 * @return lableImpression
	 **/
	@ApiModelProperty(value = "")
	public String getlableImpression() {
		return lableImpression;
	}

	public void setlableImpression(String lableImpression) {
		this.lableImpression = lableImpression;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserLableMap user = (UserLableMap) o;
		return Objects.equals(this.id, user.id)
				&& Objects.equals(this.lableId, user.lableId)
				&& Objects.equals(this.lableType, user.lableType)
				&& Objects.equals(this.lableRelationship, user.lableRelationship)
				&& Objects.equals(this.lableImpression, user.lableImpression);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, lableId, lableType, lableRelationship,lableImpression);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class User {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    lableId: ").append(toIndentedString(lableId))
				.append("\n");
		sb.append("    lableType: ").append(toIndentedString(lableType))
				.append("\n");
		sb.append("    lableRelationship: ")
				.append(toIndentedString(lableRelationship)).append("\n");
		sb.append("    lableImpression: ")
				.append(toIndentedString(lableImpression)).append("\n");
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
