package com.cdf.mall.dto.mongo.primary;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "cs-region")
@Accessors(chain = true)
public class CsRegion implements Serializable {

    @Id
    private String id;
    // code
    private String code;
    // 名称
    private String name;
    // 父id
    private String parentId;
    // 状态，1：启动，0：关闭
    private String status;
    // 创建时间
    private Date createDate;

}
