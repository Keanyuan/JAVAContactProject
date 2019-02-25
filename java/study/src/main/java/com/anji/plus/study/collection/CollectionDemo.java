package com.anji.plus.study.collection;

import java.util.*;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/25 16:33
 * @Description:
 */
public class CollectionDemo {
    public static void main(String[] args) {
        collection();
    }

    private static void collection() {
        List lnkLst = new LinkedList();
        lnkLst.add("element1");
        lnkLst.add("element2");
        lnkLst.add("element3");
        lnkLst.add("element4");
        displayAll(lnkLst, "LinkedList");
        List aryLst = new ArrayList();
        aryLst.add("x");
        aryLst.add("y");
        aryLst.add("z");
        aryLst.add("w");
        displayAll(aryLst, "ArrayList");
        Set hashSet = new HashSet();
        hashSet.add("set1");
        hashSet.add("set2");
        hashSet.add("set3");
        hashSet.add("set4");
        displayAll(hashSet, "HashSet");
        SortedSet treeSet = new TreeSet();
        treeSet.add("1");
        treeSet.add("2");
        treeSet.add("3");
        treeSet.add("4");
        displayAll(treeSet, "TreeSet");
        LinkedHashSet lnkHashset = new LinkedHashSet();
        lnkHashset.add("one");
        lnkHashset.add("two");
        lnkHashset.add("three");
        lnkHashset.add("four");
        displayAll(lnkHashset, "LinkedHashSet");
        Map map1 = new HashMap();
        map1.put("key1", "J");
        map1.put("key2", "K");
        map1.put("key3", "L");
        map1.put("key4", "M");
        displayAll(map1.keySet(), "HashMap keySet");
        displayAll(map1.values(), "HashMap values");
        SortedMap map2 = new TreeMap();
        map2.put("key1", "JJ");
        map2.put("key2", "KK");
        map2.put("key3", "LL");
        map2.put("key4", "MM");
        displayAll(map2.keySet(), "TreeMap TreeMap");
        displayAll(map2.values(), "TreeMap TreeMap");
        LinkedHashMap map3 = new LinkedHashMap();
        map3.put("key1", "JJJ");
        map3.put("key2", "KKK");
        map3.put("key3", "LLL");
        map3.put("key4", "MMM");
        displayAll(map3.keySet(), "LinkedHashMap");
        displayAll(map3.values(), "LinkedHashMap");
    }

    static void displayAll(Collection col, String s) {
        Iterator itr = col.iterator();
        System.out.println(s+":");
        while (itr.hasNext()) {
            String str = (String) itr.next();
            System.out.print(str + " ");
        }
        System.out.println();
        System.out.println();

    }

    //集合打乱顺序
    private static void arr(){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++)
            list.add(new Integer(i));
        System.out.println("打乱前:");
        System.out.println(list);

        for (int i = 1; i < 6; i++) {
            System.out.println("第" + i + "次打乱：");
            Collections.shuffle(list);
            System.out.println(list);
        }
    }

    //HashMap遍历
    private static void hashmap(){
        HashMap<String, String> hMap = new HashMap<String, String>();
        hMap.put("1", "1st");
        hMap.put("2", "2nd");
        hMap.put("3", "3rd");
        Collection cl = hMap.values();
        Iterator iterator = cl.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    //使用 Java Util 类的 Arrays.asList(name) 方法将数组转换为集合
    private static void arrToCollection(){
        int n = 5;
        String[] name = new String[n];
        for (int i = 0; i < n; i++) {
            name[i] = String.valueOf(i);
        }

        List<String> list = Arrays.asList(name);
        System.out.println(list);
        System.out.println();
        for (String li : list){
            String str = li;
            System.out.print(str + " ");
        }
    }

    //将字符串转换为集合并使用 Collection 类的 Collection.min() 和 Collection.max() 来比较集合中的元素
    private static void collectComapre(){
        String[] coins = {"Penny", "nickel", "dime", "Quarter", "dollar"};
        Set set = new TreeSet();
        for (int i = 0; i < coins.length; i++) {
            set.add(coins[i]);
        }
        System.out.println(Collections.min(set));
        System.out.println(Collections.min(set, String.CASE_INSENSITIVE_ORDER));
        System.out.println("-----------");
        System.out.println(Collections.max(set));
        System.out.println(Collections.max(set, String.CASE_INSENSITIVE_ORDER));
    }
}
