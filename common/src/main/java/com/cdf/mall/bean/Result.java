package com.cdf.mall.bean;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/5/16 15:53
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
public class Result {
    private int code;
    private String message;
    private String requestId;
    private JSONObject addition;

    public Result() {
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public JSONObject getAddition() {
        return this.addition;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }

    public void setAddition(final JSONObject addition) {
        this.addition = addition;
    }


}
