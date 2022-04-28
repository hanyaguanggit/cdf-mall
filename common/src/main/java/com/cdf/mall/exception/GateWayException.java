package com.cdf.mall.exception;


import com.cdf.mall.common.IErrorCode;
import lombok.Data;

/**
 * hyg
 */

@Data
public class GateWayException extends RuntimeException{

    private long code;

    private String message;

    public GateWayException(IErrorCode iErrorCode) {
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
    }
}
