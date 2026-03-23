package com.example.demo.common.exception;

import com.example.demo.common.Result;
import com.example.demo.common.ResultCode;
import com.example.demo.common.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        e.printStackTrace();
        return Result.fail(ResultCode.INTERNAL_ERROR.getCode(), e.getMessage());
    }
}