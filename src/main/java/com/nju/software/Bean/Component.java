package com.nju.software.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Document
public class Component {
    @Id
    private String id;
    //产品分组信息
    private String group;
    //分类简介
    private String des;
    //分类图片
    private String image;

    private List<Product> children;
}
