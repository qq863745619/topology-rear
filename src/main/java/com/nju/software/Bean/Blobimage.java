package com.nju.software.Bean;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.InputStream;

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
@Table ( name ="blobimage" )
public class Blobimage  implements Serializable {
	private static final long serialVersionUID =  7373788477174694521L;

	@Id
   	@Column(name = "path" )
	private String path;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="file", columnDefinition="longblob", nullable=true)
    private byte[] file;

   	@Column(name = "ispublic" )
	private Boolean ispublic;

   	@Column(name = "random_name" )
	private Integer randomName;

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Boolean getIspublic() {
		return this.ispublic;
	}

	public void setIspublic(Boolean ispublic) {
		this.ispublic = ispublic;
	}

	public Integer getRandomName() {
		return this.randomName;
	}

	public void setRandomName(Integer randomName) {
		this.randomName = randomName;
	}

}
