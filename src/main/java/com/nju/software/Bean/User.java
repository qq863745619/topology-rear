package com.nju.software.Bean;

import com.nju.software.Bean2.CmsUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "user")
public class User implements Serializable {
    /**{"id":"5e01ba9f6025d76071adc881","avatarUrl":"","email":"","phone":"","username":"17621937019",
            "role":"","captcha":"","vip":0,"vipExpiry":"0001-01-01T00:00:00Z","createdAt":"0001-01-01T00:00:00Z",
            "updatedAt":"0001-01-01T00:00:00Z","deletedAt":"0001-01-01T00:00:00Z"}
     **/
    @Id
    private String id;
    private String avatarUrl;
    private String email;
    private String phone;
    private String username;
    private String role;
    private String password;
    private String captcha;
    private Integer vip;
    private Long vipExpiry;
    private Long createdAt;
    private Long updatedAt;
    private Long deletedAt;

    public User(CmsUser cmsUser) {
        this.id = String.valueOf(cmsUser.getUid());


        this.phone = cmsUser.getUsername();


        this.username = cmsUser.getUsername();
        //this.

    }
}
