package com.anjiplus.springboot.controller;

import com.anjiplus.springboot.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
//@Controller
//@ResponseBody
public class HelloController {

//    @Value("${cupSize}")
//    private String cupSize;
//
//    @Value("${age}")
//    private int age;
//
//    @Value("${content}")
//    private String content;

    @Autowired
    private GirlProperties girlProperties;

//    @RequestMapping(value = {"/say", "/hi"}, method = RequestMethod.POST)
//    @RequestMapping(value = "/say/id", method = RequestMethod.POST)
//    @RequestMapping(value = "/say", method = RequestMethod.POST)
//    @GetMapping(value = "/say")
    @PostMapping(value = "/say")
//    public String say(@PathVariable("id") Integer id){
    public String say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id){
        return "the girl's cupSize is " + girlProperties.getCupSize() + "\nid:" + id;
    }
}
