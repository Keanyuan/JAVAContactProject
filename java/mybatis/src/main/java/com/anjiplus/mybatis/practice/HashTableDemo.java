package com.anjiplus.mybatis.practice;

import java.util.*;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/22 11:21
 * @Description:
 */
public class HashTableDemo {

    public static void main(String[] args) {

        Hashtable hashtable = new Hashtable();
        Enumeration names;
        String str;
        double bal;
        hashtable.put("Zare", new Double(3232.32));
        hashtable.put("Mahnaz", new Double(123.22));
        hashtable.put("Ayan", new Double(1378.00));
        hashtable.put("Daisy", new Double(99.22));
        hashtable.put("Qadir", new Double(-19.08));

        names = hashtable.keys();
        while (names.hasMoreElements()){
            str = (String)names.nextElement();
            System.out.println(str + ": " +
                    hashtable.get(str));
        }
        
        System.out.println();
        
        bal = ((Double)hashtable.get("Zare")).doubleValue();
        hashtable.put("Zare", new Double(bal + 1000));
        System.out.println("Zare's new balance: " + hashtable.get("Zare"));
    }



}
