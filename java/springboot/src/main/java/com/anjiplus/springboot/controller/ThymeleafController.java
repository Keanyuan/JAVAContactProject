package com.anjiplus.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("th")
public class ThymeleafController {
    @RequestMapping("center")
    public String center(){
        return "thymeleaf/center";
    }

}

