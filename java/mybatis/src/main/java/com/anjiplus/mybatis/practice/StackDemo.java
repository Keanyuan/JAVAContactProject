package com.anjiplus.mybatis.practice;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/22 11:13
 * @Description:
 */
public class StackDemo {
    public static void main(String[] args) {
        Stack st = new Stack();
        System.out.println("stack: " + st);
        showpush(st, 42);
        showpush(st, 54);
        showpush(st, 87);

        showpop(st);
        showpop(st);
        showpop(st);

        try {
            showpop(st);
        }catch (EmptyStackException e){
            System.out.println("empty stack");
        }
    }

    private static void showpush(Stack st, int a){
        st.push(new Integer(a));
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + st);
    }
    private static void showpop(Stack st){
        System.out.print("pop -> ");
        Integer a = (Integer) st.pop();
        System.out.println(a);
        System.out.println("stack: " + st);

    }
}
