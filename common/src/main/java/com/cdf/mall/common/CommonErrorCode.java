package com.cdf.mall.common;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/5/16 15:19
 * @Version 1.0
 */
public enum CommonErrorCode implements IErrorCodes{
    UNKNOWN_ERROR(1000, "未知错误"),
    INVALID_PERMISSION(1001, "权限异常"),
    INVALID_TOKEN(1013, "token错误"),
    INVALID_SIGN(1014, "签名错误"),
    INVALID_PARAM(1002, "参数错误"),
    ERROR_BLOCK(1003, "限流"),
    EXCEPTION_LOCK(1004, "获得锁失败"),
    ERROR_BAN(1005, "被封禁"),
    EXCEPTION_BUSINESS(1006, "被封禁"),
    ERROR_CACHE(1007, "缓存异常"),
    ERROR_DISK(1008, "磁盘错误"),
    ERROR_DB(1009, "数据库错误"),
    ERROR_NET(1010, "网络错误"),
    ERROR_INTERNAL(1012, "内部错误"),
    ERROR_SYSTEM_CLOSE(9999, "已关服"),
    ERROR_FORMAT(1013, "格式化错误");

    private int code;
    private String message;

    private CommonErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
