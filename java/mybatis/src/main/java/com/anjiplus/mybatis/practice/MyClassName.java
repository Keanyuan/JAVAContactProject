package com.anjiplus.mybatis.practice;

import sun.rmi.rmic.Generator;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/20 16:03
 * @Description:
 */
public class MyClassName {
    private  boolean myFlag;
    static final double weeks = 9.5;
    protected static final int BOXWIDTH = 42;
    String version = "1.5.1";

    public boolean isMyFlag() {
        return myFlag;
    }

    public void setMyFlag(boolean myFlag) {
        this.myFlag = myFlag;
    }

    public MyClassName(boolean myFlag, String version) {
        this.myFlag = myFlag;
        this.version = version;
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.openSpeaker(true);
    }

    boolean processOrder(){
        return  true;
    }
}

abstract class Player{
    private double price;
    private String model;
    private String year;
    public abstract void goFast();
    public abstract void changeColor();
}

class AudioPlayer extends Player{

    //final类不能被继承，没有类能够继承final类的任何特性。
    public final  void changeName(){

    }

    protected  boolean openSpeaker(boolean sp){
        return sp;
    }

    @Override
    public void goFast() {
    }

    @Override
    public void changeColor() {

    }

    //synchronized关键字声明的方法同一时间只能被一个线程访问。
    // Synchronized修饰符可以应用于四个访问修饰符。
    public synchronized void showDetails(){

    }
}

class  StreamAudioPlayer extends  AudioPlayer{
    @Override
    protected boolean openSpeaker(boolean sp) {
        return super.openSpeaker(sp);
    }
}


class InstanceCounter {
    private static int numInstances = 0;
    final int value = 10;
    public static final int BOXWIDTH = 6;
    static final String TITLE = "Manager";
    

    public transient int limit = 55;// will not persist

//    public void chanegVaule() {
//        value = 12;
//    }

    protected static int getCount(){
        return numInstances;
    }
    private static  void addInstance(){
        numInstances++;
    }

    public InstanceCounter() {
        InstanceCounter.addInstance();
    }

    public static void main(String[] args) {
        boolean result = TITLE instanceof String;
        StreamAudioPlayer streamAudioPlayer = new StreamAudioPlayer();
        boolean result1 = streamAudioPlayer instanceof StreamAudioPlayer;
        System.out.println(result);
        System.out.println(result1);

        System.out.println("Starting with " + InstanceCounter.getCount() + " instances");
        for (int i = 0; i < 500; i++) {
            new InstanceCounter();
        }
        System.out.println("Created " + InstanceCounter.getCount() + " instances");
        int x = 10;

        do {
            System.out.println("value of x: " + x);
            x++;
        }while (x < 20);

        while (x > 10){
            System.out.println("value of x: " + x);
            x--;
        }
        
        int [] numbers = {10, 20, 30, 40, 50};
        for (int num : numbers) {

            if (num == 20){
                continue;
            }
            if(num == 40){
                break;
            }
            System.out.println(num);
        }
        
        
        //Date
//        DateClass.testDate();
//        DateClass.testCalendar();
//        DateClass.testCalendar2();


    }
}


