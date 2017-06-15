package com.legalups.sufatong.controller.home;

import com.legalups.sufatong.common.model.LoginInfo;
import com.legalups.sufatong.common.result.WebResult;
import com.legalups.sufatong.dao.mapper.FirstTbMapperExt;
import com.legalups.sufatong.model.FirstTb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/6/5
 */
@RestController
@RequestMapping("home")
@Slf4j
public class HomeController {

    @Autowired
    private FirstTbMapperExt firstTbMapperExt;

    @Value("${sessionKey}")
    private String sessionKey;

    @RequestMapping("demo")
    public String demo(){
        return "hello world";
    }

    @RequestMapping("login")
    public WebResult<LoginInfo> login(HttpServletRequest request,String username, String password) throws Exception{
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setRealName("aaaaaa");
        loginInfo.setUsername(username);
//        String cookieValue = CookieUtil.getCookieValue(request, sessionKey);
        request.getSession().setAttribute(request.getSession().getId(), loginInfo);
        return WebResult.success(loginInfo);
    }

    @RequestMapping("data")
    public FirstTb getData(){
        return firstTbMapperExt.selectByPrimaryKey(1l);
    }

}
