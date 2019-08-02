package com.zrc.springboottutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/25 16:05
 * Description: No Description
 */
@Controller
@RequestMapping("th")
public class ThymeleafController {
    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "thymeleaf-黑卡");
        return "thymeleaf/index";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(String username,String password){
        return "Login success ! Hello "+username;
    }

}
