package com.anjiPlus.beanannotation.javabased;

public class StringStore implements Store<String> {
    public void init() {
        System.out.println("init");
    }
    public void destory() {
        System.out.println("destory");
    }
}
