package com.anji.plus.mystudy.thread;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/26 10:48
 * @Description:
 */
public class ThreadDemo {
    public static String obj1 = "obj1";
    public static final Semaphore a1 = new Semaphore(1);
    public static String obj2 = "obj1";
    public static final Semaphore a2 = new Semaphore(1);

    public static void main(String[] args) {
        deadlock1();
    }

    //死锁及解决方法2
    private static void deadlock1() {
        LockAa la = new LockAa();
        new Thread(la).start();
        LockBb lb = new LockBb();
        new Thread(lb).start();
    }
    //死锁及解决方法
    //解决死锁问题的方法是：
    // 一种是用synchronized，
    // 一种是用Lock显式锁实现。
    private static void deadlock(){
        LockA la = new LockA();
        new Thread(la).start();
        LockB lb = new LockB();
        new Thread(lb).start();
    }

    //线程优先级设置
    private static void threadSetPriorities(){
        new SimplePriorities(Thread.MAX_PRIORITY);
        for (int i = 0; i < 5; i++) {
            new SimplePriorities(Thread.MIN_PRIORITY);
        }
    }

    //使用 currentThread.getName() 方法来监测线程的状态
    private static void threadStatus(){
        try {
            //新建状态
            ThreadActive thread = new ThreadActive();

            thread.setName("MyThread ##1");
            showThreadStatus(thread);
            //就绪状态
            thread.start();
            Thread.sleep(50);
            showThreadStatus(thread);
            thread.waiting = false;
            //其他阻塞
            Thread.sleep(50);
            showThreadStatus(thread);
            thread.notice();
            Thread.sleep(50);
            showThreadStatus(thread);
            while (thread.isAlive()){
                System.out.println("alive");
            }
            showThreadStatus(thread);
            //终止线程
//            thread.interrupt();
            //线程挂起
//            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private static void showThreadStatus(Thread thread){
        System.out.println(thread.getName() + " Alive: " + thread.isAlive() + "State: = " + thread.getState());
    }

    //使用currentThread()方法来检测一个线程是否存活
    private static void catchThreadAclive(){
        ThreadActive tt = new ThreadActive();
        tt.setName("Thread");
        System.out.println("before start(), tt.isAlive() = " + tt.isAlive());
        tt.start();
        System.out.println("just after start(), tt.isAlive() = " + tt.isAlive());
        for (int i = 0; i < 10; i++) {
            tt.printMsg();
        }
        System.out.println("the end of main(), tt.isAlive() = " + tt.isAlive());

    }
    //中断线程
    private static void threadTest(){
        MyThread si = new MyThread();
        Thread t = new Thread(si);
        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in main() - 中断其他线程");
        t.interrupt();
        System.out.println("in main() - 离开");
    }


}

class ThreadActive extends Thread{
    boolean waiting= true;
    boolean ready= false;

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "  starting.");
        while (waiting) {
            System.out.println("waiting: " + waiting);
        }
        System.out.println("waiting...");
        startWait();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(threadName + " interrupted.");
        }
        System.out.println(threadName + " termainating");

        return;
//        for (int i = 0; i < 10; i++) {
//            printMsg();
//        }
    }

    synchronized void startWait(){
        while (!ready) {
            try {
                //等待阻塞
                wait();
            } catch (InterruptedException e) {
                System.out.println("wait() interrupted");
            }
        }
    }

    synchronized void notice(){
        ready = true;
        notify();
    }

    public void printMsg(){
        Thread t = Thread.currentThread();
        //使用 getName() 方法来获取当前线程名称
        String name = t.getName();
        System.out.println("name = " + name);

    }
}

class MyThread implements Runnable{

    public void work2() throws InterruptedException {
        while (true){
            if (Thread.currentThread().isInterrupted()){
                System.out.println("C isInterrupted() = " + Thread.currentThread().isInterrupted());
                Thread.sleep(2000);
                System.out.println("D isInterrupted() = " + Thread.currentThread().isInterrupted());

            }
        }
    }

    public void work1() throws InterruptedException {
        while (true){
            for (int i = 0; i < 10000; i++) {
                int j = i * 2;
            }
            System.out.println("A isInterrupted() = " + Thread.currentThread().isInterrupted());
            if (Thread.interrupted()){ //打断
                System.out.println("B isInterrupted() = " + Thread.currentThread().isInterrupted());
                throw new InterruptedException();
            }
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("in run() - 将运行 我work() 方法");
            work2();
            System.out.println("in run() - 从work2()方法回来");

//            System.out.println("in run() - 将运行 我work1() 方法");
//            work1();
//            System.out.println("in run() - 从work1()方法回来");

        } catch (InterruptedException e) {
            System.out.println("in run() - 中断 work2() 方法");
//            System.out.println("in run() - 中断 work1() 方法");

            return;
        }
        System.out.println("in run() - 休眠后执行");
        System.out.println("in run() - 正常离开");
    }
}

class SimplePriorities extends Thread {
    private int countDown = 5;
    private volatile  double d = 0;

    public SimplePriorities(int priority) {
        //设置优先级
        setPriority(priority);
        start();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + countDown;
    }


    @Override
    public void run() {
        while (true){
            for (int i = 0; i < 100000; i++) {
                d = d + (Math.PI + Math.E) / (double)i;
            }
            System.out.println(this);
            if (--countDown == 0){
                return;
            }
        }
    }
}


//--------------------------------------
class  LockA implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println(new Date().toString() + "LockA 开始执行");
            while (true){
                synchronized (ThreadDemo.obj1){
                    System.out.println(new Date().toString() + " LockA 开始执行");
                    Thread.sleep(3000);// 此处等待是给B能锁住机会
                    synchronized (ThreadDemo.obj2){
                        System.out.println(new Date().toString() + " LockA 锁住 obj2");
                        Thread.sleep(60 * 1000);// 为测试，占用了就不放

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class  LockB implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println(new Date().toString() + " LockB 开始执行");
            while (true){
                synchronized (ThreadDemo.obj2){
                    System.out.println(new Date().toString() + " LockB 锁住 obj2");
                    Thread.sleep(3000);
                    synchronized (ThreadDemo.obj1){
                        System.out.println(new Date().toString() + " LockB 锁住 obj1");
                        Thread.sleep(60 * 1000);// 为测试，占用了就不放
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


//-----------------------------
class LockAa implements Runnable{
    @Override
    public void run() {
        try {
            System.out.println(new Date().toString() + " LockA 开始执行");
            while (true){
                if (ThreadDemo.a1.tryAcquire(1, TimeUnit.SECONDS)){
                    System.out.println(new Date().toString() + " LockA 锁住 obj1");
                    if (ThreadDemo.a2.tryAcquire(1, TimeUnit.SECONDS)){
                        System.out.println(new Date().toString() + " LockA 锁住 obj2");
                        Thread.sleep(60 * 1000);
                    } else {
                        System.out.println(new Date().toString() + "LockA 锁 obj2 失败");
                    }
                }else {
                    System.out.println(new Date().toString() + "LockA 锁 obj1 失败");
                }
                ThreadDemo.a1.release();// 释放
                ThreadDemo.a2.release();
                Thread.sleep(1000);// 马上进行尝试，现实情况下do something是不确定的

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class LockBb implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(new Date().toString() + " LockB 开始执行");
            while (true) {
                if (ThreadDemo.a2.tryAcquire(1, TimeUnit.SECONDS)) {
                    System.out.println(new Date().toString() + " LockB 锁住 obj2");
                    if (ThreadDemo.a1.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(new Date().toString() + " LockB 锁住 obj1");
                        Thread.sleep(60 * 1000); // do something
                    }else{
                        System.out.println(new Date().toString() + "LockB 锁 obj1 失败");
                    }
                }else{
                    System.out.println(new Date().toString() + "LockB 锁 obj2 失败");
                }
                ThreadDemo.a1.release(); // 释放
                ThreadDemo.a2.release();
                Thread.sleep(10 * 1000); // 这里只是为了演示，所以tryAcquire只用1秒，而且B要给A让出能执行的时间，否则两个永远是死锁
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


