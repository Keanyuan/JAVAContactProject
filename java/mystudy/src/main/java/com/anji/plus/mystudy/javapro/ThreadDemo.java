package com.anji.plus.mystudy.javapro;

/**
 * @Auther: Kean
 * @Date: 2019/2/24 1:40 PM
 * @Description:
 */
public class ThreadDemo {

    public static void main(String[] args) {
        new NewThread(); //创建一个新线程
        for (int i = 5; i > 0; i--) {
            try {
                System.out.println("Main Thread: " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }
        }
        System.out.println("Main thread exiting.");
    }

}

// 创建一个新的线程
class NewThread implements Runnable {
    Thread thread;

    public NewThread() {
        // 创建第二个新线程
        thread = new Thread(this, "Demo Thread");
        System.out.println("Child thread: " + thread);
        thread.start();
    }

    // 第二个线程入口
    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                // 暂停线程
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted");
        }

        System.out.println("Exiting child thread");
    }
}
