package com.nju.software.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description  
 * @Author  baodan_Fan 
 * @Date 2020-08-22 
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table ( name ="image" )
public class Image  implements Serializable {
	private static final long serialVersionUID =  2416041484557006471L;

	@Id
   	@Column(name = "id" )
	private String id;

   	@Column(name = "created_at" )
	private Long createdAt;

   	@Column(name = "deleted_at" )
	private Long deletedAt;

   	@Column(name = "image" )
	private String image;

   	@Column(name = "user_id" )
	private String userId;

   	@Column(name = "username" )
	private String username;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Long getDeletedAt() {
		return this.deletedAt;
	}

	public void setDeletedAt(Long deletedAt) {
		this.deletedAt = deletedAt;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
