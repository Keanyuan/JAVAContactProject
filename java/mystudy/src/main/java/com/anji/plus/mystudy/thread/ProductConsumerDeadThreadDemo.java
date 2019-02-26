package com.anji.plus.mystudy.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Kean
 * @Date: 2019/2/26 3:56 PM
 * @Description:
 */
public class ProductConsumerDeadThreadDemo {
    public static void main(String[] args) {
//        CubbyHole c = new CubbyHole();
//        Producer producer = new Producer(c, 1);
//        Consumer consumer = new Consumer(c, 1);
//        //获取当前线程名称
//        System.out.println(producer.getName());
//        System.out.println(consumer.getName());
//        producer.start();
//        consumer.start();

        //获取所有线程
//        ThreadMain t = new ThreadMain();
//        t.setName("mainThread");
//        System.out.println(t.getName());
//        t.start();
//        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
//        int noThreads = currentGroup.activeCount();
//        Thread[] lstThreads = new Thread[noThreads];
//        currentGroup.enumerate(lstThreads);
//        for (int i = 0; i < noThreads; i++) {
//            System.out.println("线程号： " + i + " = " + lstThreads[i].getName() + " state:" + lstThreads[i].getState());
//        }



        List names = new ArrayList();

        names.add("Google");
        names.add("W3CSchool");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);

    }
}

class ThreadMain extends Thread {

}


//生产者和消费者问题是线程模型中的经典问题：
// 生产者和消费者在同一时间段内共用同一个存储空间，
// 如下图所示，生产者向空间里存放数据，而消费者取用数据，
// 如果不加以协调可能会出现以下情况：
//存储空间已满，而生产者占用着它，
// 消费者等着生产者让出空间从而去除产品，
// 生产者等着消费者消费产品，从而向空间中添加产品。
// 互相等待，从而发生死锁。
// Product ---write--->> CubbyHole <<---read--- Consumer

//存储空间
class CubbyHole {

    //存储空间
    private int contents;
    //是否可用
    private boolean available = false;
    
    public synchronized int get(){
        while (available == false){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = false;
        notifyAll();
        return contents;
    }

    public synchronized void put(int value) {
        while (available == true){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        contents = value;
        available = true;
        notifyAll();
    }


}

class Consumer extends Thread {
    private CubbyHole cubbyHole;
    private int number;

    public Consumer(CubbyHole cubbyHole, int number) {
        this.cubbyHole = cubbyHole;
        this.number = number;
    }

    @Override
    public void run() {
        int value = 0;
        for (int i = 0; i < 10; i++) {
            value = cubbyHole.get();
            System.out.println("消费者 #" + this.number + " got: " + value);
        }
    }
}

class Producer extends Thread {
    private CubbyHole cubbyhole;
    private int number;

    public Producer(CubbyHole c, int number) {
        cubbyhole = c;
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            cubbyhole.put(i);
            System.out.println("生产者 #" + this.number + "put: " + i);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
