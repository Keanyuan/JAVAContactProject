package com.anjiplus.springboot.controller;

import com.anjiplus.springboot.Interface.GirlRepository;
import com.anjiplus.springboot.dao.Girl;
import com.anjiplus.springboot.service.GirlService;
import com.anjiplus.springboot.utils.AjJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

//    查询
    @GetMapping(value = "/girls")
    public AjJSONResult girlList(){
        return AjJSONResult.ok(girlRepository.findAll());
    }

//新增
    @PostMapping(value = "/girls")
    public AjJSONResult girlAdd(@Valid Girl girl, BindingResult bindingResult) {
//    public Girl girlAdd(@RequestParam("cupSize") String cupSize, @RequestParam("age") Integer age) {
        if (bindingResult.hasErrors()){
            return AjJSONResult.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        if (girl.getAge() == null){
            return AjJSONResult.errorMsg("数据不能为空");

        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());

        return AjJSONResult.ok(girlRepository.save(girl));
    }

//    通过ID查询
    @GetMapping(value = "/girls/{id}")
    public Girl girlfindOne(@PathVariable("id") Integer id){
        return girlRepository.findById(id).get();
    }

//    通过年龄查询
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlfindAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }
//更新数据库
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);

        return girlRepository.save(girl);
    }

//    删除
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.deleteById(id);
    }

    @PostMapping(value = "girls/two")
    public void girlTwo(){
        girlService.insertTwo();
    }

    @GetMapping(value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        girlService.getAge(id);
    }
}
