package com.anjiPlus.beanannotation.javabased;

public class IntegerStore implements Store<Integer> {
    public void init() {
        System.out.println("init");
    }
    public void destory() {
        System.out.println("destory");
    }
}
