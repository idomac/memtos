package com.quanix.memtos.server.web.controller;

import com.quanix.memtos.server.entity.User;
import com.quanix.memtos.server.service.UserService;
import com.quanix.memtos.server.web.controller.base.BaseCRUDController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * created by lihaoquan
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseCRUDController<User,Long> {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);


}
