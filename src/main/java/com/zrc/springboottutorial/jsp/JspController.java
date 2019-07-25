package com.zrc.springboottutorial.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/25 15:42
 * Description: No Description
 */

@Controller
public class JspController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("msg","I am 黑卡");
        return "index";
    }
}
