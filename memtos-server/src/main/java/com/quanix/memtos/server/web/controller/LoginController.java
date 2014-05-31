package com.quanix.memtos.server.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * created by quanix
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        System.out.println("控制器处理登录....");
        return "login";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String loginSystem() {

        System.out.println("提交登陆信息");

        return "welcome";

    }
}
