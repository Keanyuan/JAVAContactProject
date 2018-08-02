package com.anjiPlus.aopAspectj.biz;

import com.anjiPlus.aopAspectj.MoocMethod;
import org.springframework.stereotype.Service;

@Service
public class MookBiz {

    @MoocMethod("MookBiz save MoocMethod")
    public String save(String arg) {
        System.out.println("MookBiz save: " + arg);
//        throw new RuntimeException("Save failed");
        return "save success";
    }

}
