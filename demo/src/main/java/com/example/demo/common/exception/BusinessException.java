package com.example.demo.common.exception;
import com.example.demo.common.ResultCode;
import lombok.Getter;


@Getter
public class BusinessException extends RuntimeException{
    private final Integer code;
    public BusinessException(ResultCode resultCode, String detailMessage) {
        super(detailMessage != null ? detailMessage : resultCode.getMessage());
        this.code = resultCode.getCode();
    }
}
