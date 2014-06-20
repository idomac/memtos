package com.quanix.memtos.server.web.controller;

import com.quanix.memtos.server.entity.Resource;
import com.quanix.memtos.server.entity.User;
import com.quanix.memtos.server.service.ResourceService;
import com.quanix.memtos.server.service.UserService;
import com.quanix.memtos.server.web.bind.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

/**
 * created by lihaoquan
 *
 * 首页控制器
 */
@Controller
public class IndexController {


    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/")
    public String index(@CurrentUser User user,Model model) {
        Set<String> permissions = userService.findPermissions(user.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }
}
