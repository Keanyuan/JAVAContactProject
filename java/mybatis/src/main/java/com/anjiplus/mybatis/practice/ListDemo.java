package com.anjiplus.mybatis.practice;

import java.util.*;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/22 14:28
 * @Description:
 */
public class ListDemo {
    public static void main(String[] args) {
        treeSetTest();
    }

    private static void arrayListTest() {
        List<String> list = new ArrayList();
        list.add("hello");
        list.add("world");
        list.add("HHHHA");
        //ArrayList遍历的第一种方式
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println();

        //ArrayList遍历的第二种方式
//        String[] strArr = new String[list.size()];
//        list.toArray(strArr);
        String[] strArr = list.toArray(new String[list.size()]);
        for (String str : strArr) {
            System.out.println(str);
        }
        System.out.println();

        //ArrayList遍历的第三种方式
        Iterator<String> ite = list.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }
    }

    private static void linkedListTest() {
        List<String> list = new LinkedList<String>();
        list.add("Hello");
        list.add("world");
        list.add("hhhha");

        //LinkedList遍历的第一种方式使用数组的方式
        String[] strArray = new String[list.size()];
        list.toArray(strArray);
        for (String str: strArray){
            System.out.println(str);
        }
        System.out.println();

        //LinkedList遍历的第二种方式
        for (String str : list){
            System.out.println(str);
        }
        
        
    }

    private static void setTest(){
        Set<String> set = new HashSet<String>();
        set.add("hello");
        set.add("world");
        set.add("hhhh");
        set.add("hhhh");

        System.out.println("set集合的尺寸为： " + set.size()
                + "\nset集合的元素为：" + set.toString() );
        //set集合的尺寸为： 3
        //set集合的元素为：[hhhh, world, hello]


        //遍历集合的第一种方法，使用数组的方法
        String[] strArray = new String[set.size()];
        strArray = set.toArray(strArray);
        for (String str: strArray) {
            System.out.println(str);
        }
        System.out.println();


        //遍历集合的第二中方法，使用set集合直接遍历
        for (String str: set){
            System.out.println(str);
        }
        System.out.println();

        //遍历集合的第三种方法，使用iterator迭代器的方法
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //String实体类中实现Comparable接口，所以在初始化TreeSet的时候，
    //无需传入比较器
    private static void treeSetTest(){
        TreeSet<String> treeSet = new TreeSet<String>();
        treeSet.add("ads");
        treeSet.add("aas");
        treeSet.add("c");
        treeSet.add("a");
        treeSet.add("e");
        treeSet.add("b");
        Iterator<String> iterator = treeSet.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    static  int additiveHash(String key, int prime){
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
        }
        return (hash % prime);
    }

    static int rotatingHash(String key, int prime){
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i ++){
            hash = (hash<<4>>28)^key.charAt(i);
        }
        return (hash % prime);
    }
    


}
