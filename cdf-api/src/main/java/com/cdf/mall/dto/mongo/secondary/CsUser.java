package com.cdf.mall.dto.mongo.secondary;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Description 用户相关
 * @Author hanyaguang
 * @Date 2022/2/18 11:53
 * @Version 1.0
 */
@Data
@Document(collection = "cs-user")
@Accessors(chain = true)
public class CsUser implements Serializable {
    @Id
    private String id;

    // 名称
    private String name;

    //状态
    private String status;
}
