package com.legalups.sufatong.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/6/14
 */
@Controller
@RequestMapping("page")
public class WebPageController {

    @RequestMapping("test")
    public String toTest(){
        return "/test";
    }

}
