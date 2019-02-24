package com.anjiplus.springboothelloword.repository.javapro;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Auther: Kean
 * @Date: 2019/2/24 2:02 PM
 * @Description:
 */
public class CallableThreadDemo implements Callable<Integer> {
    
    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " s--> " + i);
        }
        
        return i;
    }


    public static void main(String[] args) {
        CallableThreadDemo ctt = new CallableThreadDemo();
        FutureTask<Integer> ft = new FutureTask<>(ctt);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+  "的循环变量i的值 " + i);
            if (i == 20) {
                new Thread(ft, "有返回值的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值：" + ft.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
