package com.legalups.sufatong.common.exception;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/6/5
 */
public class BusinessException extends Exception{

    private static final long serialVersionUID = 4457113998128457652L;

    private String            code;

    private String            message;

    public BusinessException(String message){
        super(message);
        this.message = message;
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException( String code, String msg, Throwable e ) {
        super(msg, e);
        this.code = code;
        this.message = msg;
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
}
