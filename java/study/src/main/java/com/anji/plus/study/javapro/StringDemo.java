package com.anji.plus.study.javapro;

import java.util.Locale;

/**
 * @Auther: Kean
 * @Date: 2019/2/24 4:33 PM
 * @Description:
 */
public class StringDemo {

    public static void main(String[] args) {
        //1 strCompareTo z字符串比较
//        strCompareTo();
        //2、查找字符串最后一次出现的位置
//        searchLastStr();
        //删除字符串中的一个字符
//        removeChar();
        //字符串替换
//        strReplaceChar();
        //字符串反转
//        strReverse();
        //字符串查找
//        strFind();
        //字符串分割
//        strSplit();
        //字符串大小写转化
//        strUpperCase();
        //测试两个字符串区域是否相等
//        strRegionMatches();
        //字符串性能比较测试
//        strperformance();
        //字符串优化
//        strOptimize();

        //字符串格式化
//        strFormat();
        ////字符串连接
        strAppend();
    }

    private static void searchLastStr() {
        String strOrig = "Hello world, Hello Reader";
        int lastIndex = strOrig.lastIndexOf("Hello");
        if (lastIndex == -1) {
            System.out.println("Hello not found");
        } else {
            System.out.println("Last occurrence of Hello is at index " + lastIndex);
        }
    }

    private static void strCompareTo() {
        String s1 = "Hello world";
        String s2 = "hello world";
        Object objectStr = s1;
        System.out.println(s1.compareTo(s2));
        System.out.println(s1.compareToIgnoreCase(s2));
        System.out.println(s1.compareTo(objectStr.toString()));
    }

    private static void removeChar() {
        String s = "this is Java";
        System.out.println(removeCharAt(s, 3));
    }

    private static void strReplaceChar() {
        String str = "Hello World Hello";
        System.out.println(str.replace('H', 'W'));
        System.out.println(str.replaceFirst("He", "Wa"));
        System.out.println(str.replaceAll("He", "Ha"));
    }

    private static void strReverse() {
        String str = "abcdef";
        String reverse = new StringBuffer(str).reverse().toString();
        System.out.println("String before reverse: " + str);
        System.out.println("String after reverse: " + reverse);
    }

    private static void strFind() {
        String strOrig = "h Hello readers";
        int index = strOrig.indexOf("Hello");
        if (index == -1) {
            System.out.println("str not found");
        } else {
            System.out.println("Found Hello at index " + index);
        }
    }

    private static void strSplit() {
        String str = "www-youj-com";
        String[] temp;
        String delimeter = "-";// 指定分割字符
        temp = str.split(delimeter);// 分割字符串
        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i]);
            System.out.println();
        }


        System.out.println("------java for each循环输出的方法-----");
        String str1 = "www.w3cschool.cn";
        String[] temp1;
        String delimeter1 = "\\.";// 指定分割字符， . 号需要转义
        temp1 = str1.split(delimeter1);// 分割字符串
        for (String x : temp1) {
            System.out.println(x);
            System.out.println();

        }
    }

    private static void strUpperCase() {
        String str = "string abc touppercase";
        String strUpper = str.toUpperCase();//小写转大写
        String str1 = strUpper.toLowerCase();//大写转小写
        System.out.println("Original String: " + str);
        System.out.println("String change to upper case: " + strUpper);
        System.out.println("String change to lower case: " + str1);
    }

    //测试两个字符串区域是否相等
    private static void strRegionMatches() {
        String first_str = "Welcome to Microsoft";
        String second_str = "I work with microsoft";
        //表示将 first_str 字符串从第11个字符"M"开始
        // 和 second_str 字符串的第12个字符"M"开始逐个比较，
        // 共比较 9 对字符，由于字符串区分大小写，所以结果为false。
        boolean match1 = first_str.regionMatches(11, second_str, 12, 9);
        //第一个参数 true 表示忽略大小写区别
        boolean match2 = first_str.regionMatches(true, 11, second_str, 12, 9);
        System.out.println("区分大小写返回值：" + match1);
        System.out.println("不区分大小写返回值：" + match2);

    }

    //字符串性能比较测试
    private static void strperformance() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            String s1 = "Hello";
            String s2 = "Hello";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("通过 String 关键词创建字符串 : "
                + (endTime - startTime) + "毫秒");
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            String s3 = new String("Hello");
            String s4 = new String("Hello");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("通过 String 对象创建字符串 : "
                + (endTime1 - startTime1) + "毫秒");
    }

    //字符串优化
    private static void strOptimize() {
        String vaviables[] = new String[500000];
        for (int i = 0; i < 500000; i++) {
            vaviables[i] = "s" + 1;
        }
        long startTime0 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            vaviables[i] = "hello";
        }
        long endTime0 = System.currentTimeMillis();
        System.out.println("Create time of String literals : "
                + (endTime0 - startTime0) + " ms");

        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            vaviables[i] = new String("hello");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("Creation time of"
                + " String objects with 'new' key word : "
                + (endTime1 - startTime1)
                + " ms");
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            vaviables[i] = new String("hello");
            vaviables[i] = vaviables[i].intern();
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("Creation time of"
                + " String objects with intern(): "
                + (endTime2 - startTime2)
                + " ms");
    }

    //字符串格式化
    private static void strFormat() {
        double e = Math.E;
        System.out.format("%f%n", e);
        System.out.format(Locale.GERMANY, "字符串格式化4位 %-10.4f%n%n", e);

    }

    //字符串连接
    private static void strAppend() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            String result = "This is"
                    + "testing the"
                    + "difference" + "between"
                    + "String" + "and" + "StringBuffer";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("字符串连接"
                + " - 使用 + 操作符 : "
                + (endTime - startTime) + " ms");

        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            StringBuffer result = new StringBuffer();
            result.append("This is");
            result.append("testing the");
            result.append("difference");
            result.append("between");
            result.append("String");
            result.append("and");
            result.append("StringBuffer");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("字符串连接"
                + " - 使用 StringBuffer : "
                + (endTime1 - startTime1) + " ms");

    }


    private static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }


}
