package com.anjiplus.springboothelloword.controller;

import com.anjiplus.springboothelloword.repository.UserRepository;
import com.anjiplus.springboothelloword.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Kean
 * @Date: 2018/8/22 下午7:20
 * @Description:
 */
@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @PostMapping("/presion/save")
    public User save(@RequestParam("name") String name){
        User user = new User();
        user.setName(name);
        if (userRepository.save(user)) {
            System.out.println("对象保存成功\n" + user);
        }
        return user;
    }
}
