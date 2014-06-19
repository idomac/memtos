package com.quanix.memtos.server.web.controller;

import com.quanix.memtos.server.entity.User;
import com.quanix.memtos.server.web.bind.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by lihaoquan
 *
 * 首页控制器
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(@CurrentUser User user) {

       if(null != user) {
           //相关用户的业务操作
       }
       return "index";
    }
}
