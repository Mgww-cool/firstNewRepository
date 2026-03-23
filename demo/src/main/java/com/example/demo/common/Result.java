package com.example.demo.common;

import lombok.Data;
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    private Long timestamp;
    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
    public Result(){}
    public Result(ResultCode resultCode){
        this(resultCode,null);
    }
    // --- 静态便捷方法 ---

    /**
     * 成功返回 (带数据)
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    /**
     * 成功返回 (无数据)
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS, null);
    }

    /**
     * 失败返回 (使用枚举)
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<>(resultCode, null);
    }

    /**
     * 失败返回 (自定义消息)
     */
    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAIL.getCode());
        result.setMessage(message);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }

    /**
     * 失败返回 (自定义状态码和消息)
     */
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }
}
