package com.legalups.sufatong.common.result;

import lombok.Data;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/5/14
 */

public enum RespCodeEnum {

    SUCCESS("0", "成功"),
    SYS_ERROR("110", "系统异常"),

    APIGET_ERROR("10001","调用仲裁接口异常")
    ;

    private String code;

    private String desc;


    private RespCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
