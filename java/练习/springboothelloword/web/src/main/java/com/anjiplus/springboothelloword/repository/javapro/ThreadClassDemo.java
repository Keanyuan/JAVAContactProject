package com.anjiplus.springboothelloword.repository.javapro;

/**
 * @Auther: Kean
 * @Date: 2019/2/24 1:48 PM
 * @Description:
 */
public class ThreadClassDemo {
    public static void main(String[] args) {
        Runnable hello = new DisplayMessage("hello");
        Thread thread1 = new Thread(hello);
        thread1.setDaemon(true);
        thread1.setName("hello");
        System.out.println("Starting hello thread...");
        thread1.start();
        
        Runnable by = new DisplayMessage("Goodbye");
        Thread thread2 = new Thread(by);
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread2.setDaemon(true);
        System.out.println("Starting goobye thread...");
        thread2.start();
        
        System.out.println("Starting thread3...");
        Thread thread3 = new GuessANumber(27);
        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
        System.out.println("Starting thread4...");
        Thread thread4 = new GuessANumber(28);
        thread4.start();
        System.out.println("main() is ending...");
    }
}

//// 通过实现 Runnable 接口创建线程
class DisplayMessage implements Runnable {
    private String message;

    public DisplayMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(message);
        }
    }
}

// 通过继承 Thread 类创建线程
class GuessANumber extends Thread {
    private int number;

    public GuessANumber(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        int counter = 0;
        int guess = 0;
        do {
            guess = (int)(Math.random() * 100 + 1);
            System.out.println(this.getName() + " guess " + guess);
            counter ++;
        }while (guess != number);
        System.out.println("** Correct! " + this.getName() + " in " + counter + " guesses.**" );
        
    }
}
