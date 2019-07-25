package com.zrc.springboottutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/25 15:42
 * Description: No Description
 *
 * 如果开启了thymeleaf 则 JSP 会失效 不再使用
 */

@Controller
public class JspController {

    //如果开启了thymeleaf 则 JSP 会失效 不再使用
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("msg","I am 黑卡");
        return "index";
    }
}
