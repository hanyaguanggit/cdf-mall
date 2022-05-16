package com.cdf.mall.exception;


import com.cdf.mall.common.IErrorCode;
import lombok.Data;

/**
 * hyg
 */
@Data
public class BusinessException extends Exception {
    private long code;

    private String message;
    public BusinessException(){super();}

    public  BusinessException(IErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public BusinessException(long code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * hyg
     * @param message
     */
    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
}
