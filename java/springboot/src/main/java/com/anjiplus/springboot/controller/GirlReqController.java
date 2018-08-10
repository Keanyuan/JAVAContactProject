package com.anjiplus.springboot.controller;

import com.anjiplus.springboot.Interface.GirlRepository;
import com.anjiplus.springboot.dao.Girl;
import com.anjiplus.springboot.service.GirlService;
import com.anjiplus.springboot.utils.AjJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/girl")
public class GirlReqController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

//    查询
    @GetMapping(value = "/list")
    public AjJSONResult girlList(){
        List<Girl> list =  girlRepository.findAll();
        return AjJSONResult.ok(list);
    }

//新增aa
    @PostMapping(value = "/add")
    public AjJSONResult girlAdd(@RequestParam("cupSize") String cupSize, @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);

        Girl girl1 = girlRepository.save(girl);
        if (girl1.getId() == null) {
            return AjJSONResult.errorMsg("新增失败");
        }

        return AjJSONResult.ok();
    }

//    通过ID查询
    @GetMapping(value = "/findById/{id}")
    public AjJSONResult girlfindOne(@PathVariable("id") Integer id){
        return AjJSONResult.ok(girlRepository.findById(id).get());
    }

//    通过年龄查询
    @GetMapping(value = "/findByAge/{age}")
    public AjJSONResult girlfindAge(@PathVariable("age") Integer age){

        return AjJSONResult.ok(girlRepository.findByAge(age));
    }
//更新数据库
    @PostMapping(value = "/update/{id}")
    public AjJSONResult girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return AjJSONResult.ok(girlRepository.save(girl));
    }

//    删除
    @GetMapping(value = "/delete/{id}")
    public AjJSONResult girlDelete(@PathVariable("id") Integer id){
        girlRepository.deleteById(id);
        return AjJSONResult.ok();
    }

    @PostMapping(value = "/addTwo")
    public AjJSONResult girlTwo(){
        girlService.insertTwo();
        return AjJSONResult.ok();
    }
}
