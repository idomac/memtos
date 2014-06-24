package com.quanix.memtos.server.web.controller;

import com.quanix.memtos.server.entity.User;
import com.quanix.memtos.server.service.OrganizationService;
import com.quanix.memtos.server.service.UserService;
import com.quanix.memtos.server.web.controller.base.BaseCRUDController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * created by lihaoquan
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseCRUDController<User,Long> {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @Autowired
    private OrganizationService organizationService;



    @Override
    @RequiresPermissions("user:view")
    public String list(Model model) {
        return super.list(model);
    }


    /**
     * 展示密码修改页面
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "修改密码");
        return "user/changePassword";
    }

    /**
     * 密码修改操作提交
     * @param id
     * @param newPassword
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") Long id, String newPassword,
     RedirectAttributes redirectAttributes) {
        userService.changePassword(id,newPassword);
        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return "redirect:/user";
    }

    @Override
    protected void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
    }
}
