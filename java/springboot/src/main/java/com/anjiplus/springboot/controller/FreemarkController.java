package com.anjiplus.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ftl")
public class FreemarkController {
    @RequestMapping("center")
    public String center(){
        return "freemarker/center";
    }

}

