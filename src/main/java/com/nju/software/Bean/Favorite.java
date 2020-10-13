package com.nju.software.Bean;

import com.nju.software.Model.FavoriteKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/17/下午2:25
 * @Description:用来保存用户收藏的topologie
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "favorite")
@IdClass(FavoriteKey.class)
public class Favorite implements Serializable {
    @Id
    private String userId;
    @Id
    private String topologyId;
}
