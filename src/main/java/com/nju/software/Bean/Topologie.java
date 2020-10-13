package com.nju.software.Bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Document
public class Topologie implements Serializable {

    //图形文件id
    @Id
    private String id;
    //图形文件名称
    private String name;
    //图形文件描述
    private String desc;
    //图形数据
    private Object data;
    //这个是文件生成的图片地址
    private String image;
    //文件拥有者id
    private String userId;
    //拥有者姓名
    private String username;
    //编辑者id
    private String editorId;
    //编辑者姓名
    private String editorName;
    //是否共享
    private Boolean shared;
    //点赞数
    private Integer star;
    //收藏数
    private Integer hot;

    private Long createdAt;
    private Long updatedAt;
    private Long deletedAt;

}
