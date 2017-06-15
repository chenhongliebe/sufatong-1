package com.legalups.sufatong.controller.test;

import com.legalups.sufatong.common.result.WebResult;
import com.legalups.sufatong.common.support.AuthUtil;
import com.legalups.sufatong.service.arbitration.ArbitrationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/6/5
 */
@RequestMapping("test")
@RestController
public class ApiTestController {


    @Autowired
    private ArbitrationSerivce arbitrationSerivce;

    @RequestMapping("demo")
    public WebResult<String> testDemo(String name) throws Exception {
        if (name.equals("1")){
            return WebResult.success(AuthUtil.getUserInfo().getRealName());
        }

        return arbitrationSerivce.getDemo(name);
    }


}
