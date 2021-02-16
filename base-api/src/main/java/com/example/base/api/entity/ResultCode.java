package com.example.base.api.entity;


import java.text.MessageFormat;

/**
 * @author 朱伟伟
 * @description
 **/
public enum ResultCode {
    SUCCESS(1000, "成功"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "{0}"),

    REQUEST_BODY_READ_ERROR(1003, "{0}"),

    COMMON(1004, "{0}"),

    ERROR(5000, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result getResult() {
        return new Result<>(String.valueOf(this.code), this.msg, null);
    }

    public Result getResult(Object... errMsg) {
        String format = MessageFormat.format(this.msg, errMsg);
        return new Result<>(String.valueOf(this.code), format, null);
    }

}
