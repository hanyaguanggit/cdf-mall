package com.cdf.mall.exception.biz;

import com.alibaba.fastjson.JSONObject;
import com.cdf.mall.common.IErrorCodes;

/**
 * @Description 业务异常
 * @Author hanyaguang
 * @Date 2022/5/16 15:06
 * @Version 1.0
 */
public class BizException extends RuntimeException{
    public static final long serialVersionUID = 1254542134545L;
    private ErrorParam[] errorParams;
    private IErrorCodes errorCode;
    private String json;

    private BizException(String json) {
        this.json = json;
    }

    public BizException(ErrorParam... errorParams) {
        this.errorParams = errorParams;
    }

    public BizException(IErrorCodes errorCode, ErrorParam... errorParams) {
        this.errorCode = errorCode;
        this.errorParams = errorParams;
    }

    public static String toJSON(BizException globalException) {
        if (globalException.getJson() != null) {
            return globalException.getJson();
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", globalException.getErrorCode().getCode());
            jsonObject.put("message", globalException.getErrorCode().getMessage());
            JSONObject addition = new JSONObject();
            jsonObject.put("addition", addition);
            ErrorParam[] var3 = globalException.getErrorParams();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                ErrorParam errorParam = var3[var5];
                addition.put(errorParam.getName(), errorParam.getValue());
            }

            return jsonObject.toJSONString();
        }
    }

    public static BizException fromJSON(String json) {
        BizException ge = new BizException(json);
        return ge;
    }

    public ErrorParam[] getErrorParams() {
        return this.errorParams;
    }

    public IErrorCodes getErrorCode() {
        return this.errorCode;
    }

    public String getJson() {
        return this.json;
    }
}
