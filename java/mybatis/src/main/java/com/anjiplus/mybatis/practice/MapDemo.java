package com.anjiplus.mybatis.practice;

import org.omg.CORBA.MARSHAL;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/22 15:15
 * @Description:
 */
public class MapDemo {

    static int hashMapW = 0;
    static int hashMapR = 0;

    static int linkMapW = 0;
    static int linkMapR = 0;

    static int treeMapW = 0;
    static int treeMapR = 0;

    static int hashTableW = 0;
    static int hashTableR = 0;

    public static void main(String[] args) {
//        mapTest2();
        hashMapSortTest();
        treeMapSort();
    }

    private static void mapTest() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        System.out.println(map.toString());
        
        System.out.println("key1 : " + map.get("key1"));;
        
        map.remove("key1");
        
        System.out.println(map.toString());
        
        map.clear();
        
        System.out.println(map.toString());

    }


    private static void mapTest2(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        System.out.println("使用keySet()增强for循环遍历");
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }

        System.out.println();
        System.out.println("使用entrySet()增强for循环遍历");
        
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println();
        System.out.println("使用keySet()迭代器遍历");
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            System.out.println(key + " : " + map.get(key));
        }

        System.out.println();
        System.out.println("使用entrySet()迭代器遍历");
        Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator1.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }


    /**
     * HashMap四种便利方式性能比较
     */
    private static void hashMapTraverseTest(){
        // 初始化，10W次赋值
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 10000000; i++)
            map.put(i, i);

        /** 增强for循环，keySet迭代 */
        long start = System.currentTimeMillis();
        for (Integer key : map.keySet()) {
            map.get(key);
        }
        long end = System.currentTimeMillis();
        System.out.println("增强for循环，keySet迭代 -> " + (end - start) + " ms");

        /** 增强for循环，entrySet迭代 */
        start = System.currentTimeMillis();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();
        System.out.println("增强for循环，entrySet迭代 -> " + (end - start) + " ms");

        /** 迭代器，keySet迭代 */
        start = System.currentTimeMillis();
        Iterator<Integer> iterator = map.keySet().iterator();
        Integer key;
        while (iterator.hasNext()) {
            key = iterator.next();
            map.get(key);
        }
        end = System.currentTimeMillis();
        System.out.println("迭代器，keySet迭代 -> " + (end - start) + " ms");

        /** 迭代器，entrySet迭代 */
        start = System.currentTimeMillis();
        Iterator<Map.Entry<Integer, Integer>> iterator1 = map.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        while (iterator1.hasNext()) {
            entry = iterator1.next();
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();

        System.out.println("迭代器，entrySet迭代 -> " + (end - start) + " ms");

        System.out.println("\n\n总结\n" +
                "\n" +
                "1、增强for循环使用方便，但性能较差，不适合处理超大量级的数据。\n" +
                "\n" +
                "2、迭代器的遍历速度要比增强for循环快很多，是增强for循环的2倍左右。\n" +
                "\n" +
                "3、使用entrySet遍历的速度要比keySet快很多，是keySet的1.5倍左右。");
    }

    /**
     * Map 排序
     * 通过ArrayList构造函数把map.entrySet()转换成list
     * 通过比较器实现比较排序
     */
    private static void hashMapSortTest(){
        System.out.println("HashMap降序排序");

        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "c");
        map.put("b", "b");
        map.put("c", "a");
        // 通过ArrayList构造函数把map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 通过比较器实现比较排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
                return mapping1.getKey().compareTo(mapping2.getKey());
            }
        });

        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey()  + " : " + mapping.getValue());
        }
    }

    /**
     * TreeMap排序
     * TreeMap默认按key进行升序排序，如果想改变默认的顺序，可以使用比较器
     */
    private static void treeMapSort(){
        Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String obj1, String obj2) {
                return obj2.compareTo(obj1);//降序排列
            }
        });
        map.put("a", "c");
        map.put("b", "b");
        map.put("c", "a");
        System.out.println("TreeMap升序排序");
        for (String key : map.keySet()){
            System.out.println(key + " ：" + map.get(key));
        }

        treeMapValueSort();
    }

    /**
     * 按value排序(通用)
     */
    private static void treeMapValueSort(){
        System.out.println("按value排序(通用)");
        Map<String, String> map = new TreeMap<String, String>();
        map.put("a", "f");
        map.put("b", "b");
        map.put("c", "e");
        // 通过ArrayList构造函数把map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 通过比较器实现比较排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }
        });
        for (String key : map.keySet()) {
            System.out.println(key + " ：" + map.get(key));
        }
    }


    /**
     * 测试map子类插入读取性能
     */
    private static void mapTest1(){
        for (int i = 0; i < 10; i++) {
            MapDemo map = new MapDemo();
            map.mapSubclassTest(100 * 1000);
            System.out.println();
        }
        System.out.println("平均值：");
        System.out.println("hashMapW = " + hashMapW / 10);
        System.out.println("hashMapR = " + hashMapR / 10);
        System.out.println("linkMapW = " + linkMapW / 10);
        System.out.println("linkMapR = " + linkMapR / 10);
        System.out.println("treeMapW = " + treeMapW / 10);
        System.out.println("treeMapR = " + treeMapR / 10);
        System.out.println("hashTableW = " + hashTableW / 10);
        System.out.println("hashTableR = " + hashTableR / 10);

    }

    /**
     * 测试map子类插入读取性能
     * @param size
     */
    private void mapSubclassTest(int size){
        int index;
        Random random = new Random();
        String[] key = new String[size];

        // HashMap 插入
        Map<String, String> map = new HashMap<String, String>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        long end = System.currentTimeMillis();
        hashMapW += (end - start);
        System.out.println("HashMap插入耗时 = " + (end -start) + "ms");

        // HashMap 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        hashMapR += (end - start);
        System.out.println("HashMap读取耗时 = " + (end - start) + " ms");

        // LinkedHashMap 插入
        map = new LinkedHashMap<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        linkMapW += (end - start);
        System.out.println("LinkedHashMap插入耗时 = " + (end - start) + " ms");

        // LinkedHashMap 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        linkMapR += (end - start);
        System.out.println("LinkedHashMap读取耗时 = " + (end - start) + " ms");

        // TreeMap 插入
        key = new String[size];
        map = new TreeMap<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        treeMapW += (end - start);
        System.out.println("TreeMap插入耗时 = " + (end - start) + " ms");

        // TreeMap 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        treeMapR += (end - start);
        System.out.println("TreeMap读取耗时 = " + (end - start) + " ms");

        // Hashtable 插入
        key = new String[size];
        map = new Hashtable<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        hashTableW += (end - start);
        System.out.println("Hashtable插入耗时 = " + (end - start) + " ms");

        // Hashtable 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        hashTableR += (end - start);
        System.out.println("Hashtable读取耗时 = " + (end - start) + " ms");
    }

}
