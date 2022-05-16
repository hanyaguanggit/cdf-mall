package com.cdf.mall.exception.biz;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/5/16 15:07
 * @Version 1.0
 */
public class ErrorParam {
    public static final ErrorParam ENABLE_ERROR_LOG = new ErrorParam("_PARAM_ERROR_LOG_");
    public static final ErrorParam ENABLE_ERROR_ALERT = new ErrorParam("_PARAM_ERROR_ALERT_");
    public static final ErrorParam ENABLE_ERROR_POINT = new ErrorParam("_PARAM_ERROR_POINT_");
    public static final ErrorParam ENABLE_ERROR_RISK = new ErrorParam("_PARAM_ERROR_RISK_");
    private String name;
    private String value;

    public ErrorParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public ErrorParam(String name) {
        this.name = name;
        this.value = "1";
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
