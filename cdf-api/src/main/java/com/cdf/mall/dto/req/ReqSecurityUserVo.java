package com.cdf.mall.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * hyg  请求用户认证信息入参
 */
@Data
public class ReqSecurityUserVo implements Serializable {
    @Schema(description = "用户名")
    private String loginName;

    @Schema(description = "密码(md5)")
    private String password;
    @Schema(description = "0未锁定，1锁定")
    private Boolean locked;

    @Schema(description = "0未启用，1启动")
    private Boolean enabled;
}
