package com.cdf.mall.dto;

import lombok.Data;

/**
 * @Description 系统日志实体
 * @Author hanyaguang
 * @Date 2022/4/19 15:02
 * @Version 1.0
 */
@Data
public class SystemLogDto {

    private long id;
    /**用户id*/
    private String userId;
    /**用户类型*/
    private int userType;

    /**操作模块*/
    private String module;

    /**操作类型*/
    private String operateType;

    /**操作前参数*/
    private String beforeParams;

    /**操作时请求参数*/
    private String operateParams;

    /**开始时间*/
    private String startTime;

    /**结束时间*/
    private String endTime;

    /**操作状态描述*/
    private int resultStatus;

    /**操作结果描述*/
    private String resultMsg;
}
