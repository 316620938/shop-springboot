package com.hgt.pojo;

//返回结果的code&message
public enum ResultCode {
    // 成功
    SUCCESS(1, "成功"),
    FAIL(500,"异常"),
    // 参数无效
    PARAM_IS_INVALID(1001, "无效参数"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_IS_TYPE_ERROR(1003, "参数类型错误"),
    NO_PERMISSION(1004,"无权访问"),
    PARAM_MISSING(1005,"缺少参数");


    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
