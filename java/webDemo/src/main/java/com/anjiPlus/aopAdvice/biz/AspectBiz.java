package com.anjiPlus.aopAdvice.biz;

public class AspectBiz {
    public void biz() {
        System.out.println("AspectBiz biz.");
//        指定抛出异常
//        throw  new RuntimeException();
    }

    public void init(String bizName, int times) {
        System.out.println("AspectBiz init " + bizName + "  " + times);
    }

}
