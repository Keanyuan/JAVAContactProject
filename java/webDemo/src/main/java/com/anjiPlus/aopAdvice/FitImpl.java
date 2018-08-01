package com.anjiPlus.aopAdvice;

public class FitImpl implements Fit {

    @Override
    public void filter() {
        System.out.println("FitImpl filter");
    }
}
