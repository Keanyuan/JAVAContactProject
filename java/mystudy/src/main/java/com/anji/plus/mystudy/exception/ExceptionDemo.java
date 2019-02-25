package com.anji.plus.mystudy.exception;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/25 13:45
 * @Description:
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        customException();
    }

    //自定义异常
    private static void customException(){
        try {
            new InputException().method();
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }

    //多线程异常处理
    private static void threadException(){

        try {
            MyThread t = new MyThread();
            t.start();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException " + e);
        } catch (Exception e){
            System.out.println("Caught it " + e);
//            try {
//                throw  new NumberFormatException();
//            } catch (NumberFormatException x){
//                System.out.println("this is NumberFormatException" + x);
//            } catch (Exception e1){
//                System.out.println("this is Exception" + e1);
//            }
        }
        System.out.println("Exiting main");
    }

    private static void exceptTest(){
        try {
            throw new Exception("my Exception test");
        } catch (Exception e) {
            System.err.println("Caugth Exception");
            System.err.println("getMessage() " + e.getMessage());
            System.err.println("getLocalizedMessage() " + e.getLocalizedMessage());
            System.err.println("toString() " + e);
            System.err.println("printStackTrace() ");
            e.printStackTrace();
        }
    }

    //多个异常处理（多个catch）
    private static void exceptTest1() {
        ExDemo exDemo = new ExDemo();
        try {
            int x = exDemo.div(4, 0);
            System.out.println("x = " + x);
        }catch (ArithmeticException e){
            System.out.println(e.toString());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.toString());
        } catch (Exception e){
            System.out.println(e.toString());
        } finally {
            System.out.println("执行完毕");
        }
        System.out.println("over");

    }



    static class ExDemo{
        //在功能上通过throws的关键字声明该功能可能出现问题
        int div(int a, int b) throws ArithmeticException, ArrayIndexOutOfBoundsException {
            int[] arr = new int[a];
            System.out.println(arr[4]);//制造的第一处异常
            //制造的第二处异常
            return a/b;
        }
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Throwing in MyThtrad");
            throw new RuntimeException();
        }
    }
}


class WrongInputException extends Exception {
    public WrongInputException(String s) {
        super(s);
    }
}

class InputException{
    void method() throws WrongInputException {
        throw new WrongInputException("Wrong input");
    }
}