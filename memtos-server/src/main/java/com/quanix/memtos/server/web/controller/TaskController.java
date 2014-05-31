package com.quanix.memtos.server.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * @author : lihaoquan
 */
@Controller
@RequestMapping(value = "/task")
public class TaskController {


    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "welcome";
    }
}
