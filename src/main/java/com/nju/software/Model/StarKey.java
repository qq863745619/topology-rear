package com.nju.software.Model;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/17/下午2:25
 * @Description:
 */
public class StarKey implements Serializable {
    private String userId;
    private String topologyId;
}
