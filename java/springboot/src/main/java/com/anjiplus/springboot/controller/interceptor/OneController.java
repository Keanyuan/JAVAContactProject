package com.anjiplus.springboot.controller.interceptor;

import com.anjiplus.springboot.pojo.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("one")
public class OneController {

	@RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "imooc22");
        return "thymeleaf/index";
    }
	
	@RequestMapping("center")
    public String center() {
        return "thymeleaf/center/center";
    }

	@RequestMapping("test")
    public String test(ModelMap map) {
		
		SysUser user = new SysUser();
		user.setAge(18);
		user.setNickname("manager");
		user.setPassword("123456");
		user.setRegistTime(new Date());
		
		map.addAttribute("user", user);


		SysUser u1 = new SysUser();
		u1.setAge(19);
		u1.setNickname("imooc");
		u1.setPassword("123456");
		u1.setRegistTime(new Date());

		SysUser u2 = new SysUser();
		u2.setAge(17);
		u2.setNickname("LeeCX");
		u2.setPassword("123456");
		u2.setRegistTime(new Date());
		
		List<SysUser> userList = new ArrayList<>();
		userList.add(user);
		userList.add(u1);
		userList.add(u2);
		
		map.addAttribute("userList", userList);
		
        return "thymeleaf/test";
    }
	
	@PostMapping("postform")
    public String postform(SysUser user) {
		System.out.println(user.getNickname());
        return "redirect:/th/test";
    }
}