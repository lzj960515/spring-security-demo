package com.lzj.my.spring.security.result;

import java.io.Serializable;

public class ApiResponse implements Serializable {
    /**
     * 操作状态，用于描述该请求操作是否成功,
     * true表示成功
     * false表示失败
     */
    private boolean state;
    /**
     * 错误码
     */
    private String code;
    /**
     * 描述
     */
    private String msg;
    /**
     * 操作结果数据
     */
    private Object data;


    public ApiResponse() {}

    public ApiResponse(boolean state) {
        this.state = state;
        this.code = state ? ResponseEnum.OPERATION_SUCCESS.code : ResponseEnum.OPERATION_FAIL.code;
        this.msg = state ? ResponseEnum.OPERATION_SUCCESS.message : ResponseEnum.OPERATION_FAIL.message;
    }

    public ApiResponse(boolean state, ResponseEnum responseEnum) {
        this(state, responseEnum, null);
    }

    public ApiResponse(boolean state, ResponseEnum responseEnum, Object data) {
        this.state = state;
        this.code = responseEnum.code;
        this.msg = responseEnum.message;
        this.data = data;
    }

    public ApiResponse(boolean state, String code, String message) {
        this.state = state;
        this.code = code;
        this.msg = message;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

}
