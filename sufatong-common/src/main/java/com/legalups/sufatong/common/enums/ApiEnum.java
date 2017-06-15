package com.legalups.sufatong.common.enums;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/6/5
 */
public enum  ApiEnum {

    DEMO("0","test/1.0/helloApi"),
    PARAM("1","arb/1.0/globalItem"),
    ORGAN("2","arb/1.0/commission")
    ;

    private String code;
    private String url;

    private ApiEnum(String code,String url){
        this.code = code;
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
