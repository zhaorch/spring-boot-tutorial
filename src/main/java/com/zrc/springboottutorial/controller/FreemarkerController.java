package com.zrc.springboottutorial.controller;

import com.zrc.springboottutorial.model.ZrcResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/25 16:16
 * Description: No Description
 */

@Controller
@RequestMapping("ftl")
public class FreemarkerController {
    @Autowired
    private ZrcResource resource;
    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("resource", resource);
        return "freemarker/index";
    }
}