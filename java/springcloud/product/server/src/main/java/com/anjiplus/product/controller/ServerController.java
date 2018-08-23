package com.anjiplus.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/22 11:02
 * @Description:
 */
@RestController
@RequestMapping("server")
public class ServerController {
    @GetMapping("msg")
    public String msg(){
        return "this i product msg";
    }
}
