package com.anji.plus.mystudy.thread;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/26 10:48
 * @Description:
 */
public class ThreadDemo {
    public static void main(String[] args) {
        threadTest();
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