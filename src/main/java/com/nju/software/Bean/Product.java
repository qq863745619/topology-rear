package com.nju.software.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)

@Document
public class Product implements Serializable {
    //所在分组编号
    private  String groupId;
    //产品编号
    @Id
    private String id;
    //产品名称
    private String name;
    //产品的图片
    private String img;
    //产品图标
    private String icon;
    //产品图片的长宽
    private Object style;
    //产品数据
    private Object data;


}
