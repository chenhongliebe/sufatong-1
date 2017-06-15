package com.legalups.sufatong.common.result;

import java.io.Serializable;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/5/14
 */
public class WebResult<T> implements Serializable{

    private static final long serialVersionUID = 4160748732980096244L;

    private String code;

    private String message;

    private T data;

    public WebResult(T data) {
        this.data = data;
        this.code = RespCodeEnum.SUCCESS.getCode();
        this.message = RespCodeEnum.SUCCESS.getDesc();
    }

    public static <T> WebResult<T> success(T data){
        return new WebResult(data);
    }

    public static <T> WebResult<T> ret(String code, String message ){
        return new WebResult(code,message);
    }
    public static <T> WebResult<T> ret(RespCodeEnum respCodeEnum ){
        return new WebResult(respCodeEnum.getCode(),respCodeEnum.getDesc());
    }
    public static <T> WebResult<T> ret(RespCodeEnum respCodeEnum ,String message){
        return new WebResult(respCodeEnum.getCode(),message);
    }

    public WebResult(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
