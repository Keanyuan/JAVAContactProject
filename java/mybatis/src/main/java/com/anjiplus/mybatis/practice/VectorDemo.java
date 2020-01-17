package com.anjiplus.mybatis.practice;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/22 11:02
 * @Description:
 */
public class VectorDemo {
    public static void main(String[] args) {
        vectortest();
    }
    
    private static void vectortest(){
        Vector v = new Vector(3, 2);
        System.out.println("Initial size0: " + v.size());
        System.out.println("Initial capacity0: " + v.capacity());

        v.addElement(new Integer(1));
        v.addElement(new Integer(2));
        v.addElement(new Integer(3));
        v.addElement(new Integer(4));
        v.addElement(new Integer(5));
        v.addElement(new Integer(6));
        System.out.println("additions size1: " + v.size());
        System.out.println("additions capacity1: " + v.capacity());

        v.addElement(new Double(5.45));
        System.out.println("additions size2: " + v.size());
        System.out.println("additions capacity2: " + v.capacity());

        v.addElement(new Double(6.08));
        v.addElement(new Integer(7));
        System.out.println("additions size3: " + v.size());
        System.out.println("additions capacity3: " + v.capacity());

        v.addElement(new Float(9.4));
        v.addElement(new Integer(10));
        System.out.println("additions size4: " + v.size());
        System.out.println("additions capacity4: " + v.capacity());

        v.addElement(new Integer(11));
        v.addElement(new Integer(12));
        System.out.println("additions size5: " + v.size());
        System.out.println("additions capacity5: " + v.capacity());
        System.out.println("First element: " +
                (Integer)v.firstElement());
        System.out.println("Last element: " +
                (Integer)v.lastElement());
        
        if (v.contains(new Integer(3))){
            System.out.println("Vector contains 3.");
        }

        Enumeration vEnum = v.elements();
        System.out.println("\nElements in vector:");
        while (vEnum.hasMoreElements()){
            System.out.print(vEnum.nextElement() + " ");
        }
        System.out.println();

    }


}
