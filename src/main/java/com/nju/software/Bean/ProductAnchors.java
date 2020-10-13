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
@Table ( name ="product_anchors" )
public class ProductAnchors  implements Serializable {
	private static final long serialVersionUID =  8895578706196946398L;

	@Id
   	@Column(name = "id" )
	private String id;

   	@Column(name = "anchors_id" )
	private Long anchorsId;

   	@Column(name = "name" )
	private String name;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getAnchorsId() {
		return this.anchorsId;
	}

	public void setAnchorsId(Long anchorsId) {
		this.anchorsId = anchorsId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
