package com.legalups.sufatong.common.support;

import lombok.Data;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/5/20
 */
@Data
public class ApplicationContext  {


//    private static volatile ApplicationContext instance;
//
//    public static ApplicationContext getInstance() {
//        if (instance == null) {
//            throw new IllegalStateException("SSO组件尚未初始化!");
//        }
//        return instance;
//    }

    private  String notLoginJsonString ="未登录";

    private String noAuth="无权限";


    private boolean forApp;



}
