package com.legalups.sufatong.common.support;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/6/4
 */
public class TokenUtil {


    @Value("${zcname}")
    private String zcname;

    @Value("${zcpwd}")
    private String zcpwd;


    public static String getToken(){
        return "a71b6841-f601-3350-ba49-e7a9f8946ed1";
    }


}
