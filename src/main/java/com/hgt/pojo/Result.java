package com.hgt.pojo;

import java.io.Serializable;

/**
 * 统一返回结果的封装
 */
public class Result implements Serializable {
    /*状态码*/
    private Integer code;
    /*状态对应的信息*/
    private String msg;
    /*返回值中的数据，可为空*/
    private Object data;

    /**
     * 含数据的返回结果
     *
     * @param resultCode 状态
     * @param data       数据
     */
    private Result(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    /**
     * 无数据的返回结果
     *
     * @param resultCode 状态
     */
    private Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    /**
     * 状态成功，且无返回数据的返回值
     * @return
     */
    public static Result success() {
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 状态成功，且有返回数据的返回值
     * @param data 数据
     * @return
     */
    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS,data);
    }

    /**
     * 状态失败，且无数据的返回值
     * @param resultCode 返回的状态
     * @return 返回值
     */
    public static Result fail(ResultCode resultCode){
        return new Result(resultCode);
    }

    /**
     * 状态失败，且有数据的返回值
     * @param resultCode 不同的状态
     * @param data 数据
     * @return 返回值
     */
    public static Result fail(ResultCode resultCode,Object data) {
        return new Result(resultCode,data);
    }

    /**
     * 异常状态，code 500
     * @param msg 信息
     * @return 返回值
     */
    public static Result fail(String msg) {
        return new Result(ResultCode.FAIL,msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
