package com.example.demo.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResultCode {

    // 成功
    SUCCESS(200, "操作成功", HttpStatus.OK),

    // 通用错误
    FAIL(500, "操作失败", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(401, "未授权", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(403, "禁止访问", HttpStatus.FORBIDDEN),
    NOT_FOUND(404, "资源不存在", HttpStatus.NOT_FOUND),
    INTERNAL_ERROR(500, "服务器内部错误",HttpStatus.INTERNAL_SERVER_ERROR),

    // 业务错误 (示例)
    PARAM_ERROR(400, "参数错误", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1001, "用户不存在", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(1002, "密码错误", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ResultCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
