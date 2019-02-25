package com.anji.plus.mystudy.operation;

import org.omg.PortableInterceptor.INACTIVE;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.util.*;


/**
 * @auther: kean_qi
 * @Date: 2019/2/25 14:57
 * @Description: 运算 数据结构
 */
public class OperationDemo {

    public static void main(String[] args) {
        vector();
    }

    //队列（Queue）用法
    private static void queue(){
        //add（）和remove（）方法在失败的时候回抛出异常
        Queue<String> queue = new LinkedList<String>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("pull = " + queue.poll());//返回第一个元素，并在队列中删除
        for (String q : queue) {
            System.out.println(q);
        }
        System.out.println("===");
        System.out.println("element="+queue.element()); //返回第一个元素

        for (String q : queue) {
            System.out.println(q);
        }

        System.out.println("===");
        System.out.println("peek="+queue.peek()); //返回第一个元素

        for(String q : queue){
            System.out.println(q);
        }


    }
    //压栈出栈的方法实现字符串反转
    private static void copyReverserStack(){
        try {
            String input = "my name is li li";
            String output;
            StringReverserThroughStack throughStack = new StringReverserThroughStack(input);
            output  = throughStack.doRev();
            System.out.println("反转前： " + input);
            System.out.println("反转后： " + output);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取向量元素的索引值
    private static void vector(){
        Vector v = new Vector();
        v.add("X");
        v.add("M");
        v.add("D");
        v.add("A");
        v.add("P");
        v.add("O");
        Collections.sort(v);
        System.out.println(v);
        int index = Collections.binarySearch(v, "D");
        System.out.println("元素索引值为 : " + index);

        Object o = Collections.max(v);
        System.out.println("最大元素是： " + o);

        System.out.println("旋转前的向量 " + v);

        Collections.swap(v, 0, 4);

        System.out.println("旋转后的向量 " + v);

    }

    private static void linkedList(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("2");
        linkedList.add("5");

        System.out.println(linkedList);
        linkedList.addFirst("0");
        System.out.println(linkedList);
        linkedList.addLast("6");
        System.out.println(linkedList);
        
        System.out.println("链表的第一个元素是：" + linkedList.getFirst());
        System.out.println("链表的最后一个元素是：" + linkedList.getLast());
        System.out.println("元素 2 第一次出现的位置：" + linkedList.indexOf("2"));
        System.out.println("元素 2 最后一次出现的位置：" + linkedList.lastIndexOf("2"));



        linkedList.subList(2, 4).clear();
        System.out.println("删除链表中的元素： " + linkedList);

        linkedList.subList(2,3).clear();
        System.out.println("删除链表中的元素： " + linkedList);

        linkedList.set(2, "M");
        System.out.println("修改后的元素 " + linkedList);




    }

    private static void stack(){
        String input = "1+2*4/5-7+3/6";
        String output;
        IntoPost theTrans = new IntoPost(input);
        output = theTrans.doTrans();
        System.out.println("Postfix is " + output);
    }
    private static void sum(){
        int limit = 100;
        int sum = 0;
        int i = 1;
        do {
            sum = sum + i;
            i++;
        }while (i <= limit);
        System.out.println(sum);
    }
}

class IntoPost{
    private Stack theStack;
    private String input;
    private String output = "";

    public IntoPost(String input) {
        this.input = input;
        int stackSize = input.length();
        theStack = new Stack(stackSize);
    }

    public String doTrans(){
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            switch (ch){
                case '+':
                case '-':
                    gotOper(ch, 1);
                    break;
                case '*':
                case '/':
                    gotOper(ch, 2);
                    break;
                case '(':
                    theStack.push(ch);
                    break;
                case ')':
                    gotParen(ch);
                    break;
                default:
                    output = output + ch;
                    break;
            }
        }

        while (!theStack.isEmpty()){
            output = output + theStack.pop();
        }
        System.out.println(output);
        return output;
    }

    public void gotOper(char opThis, int prec1){
        while (!theStack.isEmpty()){
            char opTop = theStack.pop();
            if (opTop == '('){
                theStack.push(opTop);
                break;
            } else {
                int prec2;
                if (opTop == '+' || opTop == '-') {
                   prec2 = 1;
                } else {
                    prec2 = 2;
                }
                if (prec2 < prec1){
                    theStack.push(opTop);
                    break;
                } else {
                    output = output + opTop;
                }
            }
        }
        theStack.push(opThis);
    }

    public void gotParen(char ch){
        while (!theStack.isEmpty()){
            char chx = theStack.pop();
            if (chx == '('){
                break;
            } else {
                output = output + chx;
            }
        }
    }

    private class  Stack{
        private int maxSize;
        private char[] stackArray;
        private int top;
        public Stack(int max){
            maxSize = max;
            stackArray = new char[maxSize];
            top = -1;
        }

        public void push(char j){
            stackArray[++top] = j;
        }
        public char pop(){
            return stackArray[top--];
        }
        public char peek(){
            return stackArray[top];
        }
        public boolean isEmpty(){
            return (top == -1);
        }
        public boolean isFull() {
            return (top == maxSize - 1);
        }
    }
}


class StringReverserThroughStack{
    
    private String input;
    private String output;
    public StringReverserThroughStack(String in){
        input = in;
    }
    public String doRev(){
        int stackSize = input.length();
        Stack theStack = new Stack(stackSize);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            theStack.push(ch);
        }
        output = "";
        while (!theStack.isEmpty()){
            char ch = theStack.pop();
            //将出栈的值赋值给字符串
            output = output + ch;
        }
        return output;
    }

    private class  Stack{
        private int maxSize;
        private char[] stackArray;
        private int top;
        public Stack(int max){
            maxSize = max;
            stackArray = new char[maxSize];
            top = -1;
        }

        public void push(char j){
            stackArray[++top] = j;
        }
        public char pop(){
            return stackArray[top--];
        }
        public char peek(){
            return stackArray[top];
        }
        public boolean isEmpty(){
            return (top == -1);
        }
        public boolean isFull() {
            return (top == maxSize - 1);
        }
    }
}


