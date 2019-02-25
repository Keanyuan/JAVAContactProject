package com.anji.plus.mystudy.javapro;

import java.util.*;

/**
 * @Auther: Kean
 * @Date: 2019/2/24 7:12 PM
 * @Description:
 */
public class arrayDemo {

    public static void main(String[] args) {
        arrUnion();
    }

    //并集
    private static void arrUnion() {
        String[] arr1 = {"1", "2", "3", "4"};
        String[] arr2 = {"4", "5", "6"};
        String[] result_union = union(arr1, arr2);
        System.out.println("并集的结果如下：");
        for (String str : result_union) {
            System.out.print(str + " ");
        }
        System.out.println();
    }


    //数组是否相等
    private static void arrIsEquals() {
        int[] ary = {1, 2, 3, 4, 5, 6};
        int[] ary1 = {1, 2, 3, 4, 5, 6};
        int[] ary2 = {1, 2, 3, 4};
        System.out.println("数组 ary 是否与数组 ary1相等? ："
                + Arrays.equals(ary, ary1));
        System.out.println("数组 ary 是否与数组 ary2相等? ："
                + Arrays.equals(ary, ary2));
    }

    //数组删除 数组差集、交集
    private static void arrDelete() {
        ArrayList objArray = new ArrayList();
        ArrayList objArray1 = new ArrayList();

        objArray.clear();
        objArray.add(0, "第 0 个元素");
        objArray.add(1, "第 1 个元素");
        objArray.add(2, "第 2 个元素");
        System.out.println("数组删除前的元素： " + objArray);
        objArray.remove(1);
        objArray.remove("0th element");
        System.out.println("数组删除后的元素： " + objArray);
        objArray.clear();
        System.out.println("数组清除后的元素： " + objArray);


        objArray1.clear();
        objArray1.add(0, "common1");
        objArray1.add(1, "common2");
        objArray1.add(2, "notcommon");
        objArray1.add(3, "notcommon1");
        objArray.add(0, "common1");
        objArray.add(1, "common2");
        objArray.add(2, "notcommon2");
        System.out.println("array 的元素 " + objArray);
        System.out.println("array1 的元素 " + objArray1);
        objArray.removeAll(objArray1);
        System.out.println("array 与 array1 数组差集为：" + objArray);

        objArray.add(0, "common1");
        objArray.add(1, "common2");
        objArray.add(2, "notcommon2");
        objArray.remove("notcommon2");
        System.out.println("array 的元素 " + objArray);
        System.out.println("array1 的元素 " + objArray1);
        objArray.retainAll(objArray1);
        System.out.println("array & array1 数组交集为：" + objArray);


    }

    //数组扩容
    private static void arrAddFill() {
        String[] names = new String[]{"A", "B", "c"};
        String[] extended = new String[5];
        extended[3] = "D";
        extended[4] = "E";
        //源数组 、源数组起始位置、目标数组、目标数据中的起始位置、 要复制的数组元素的数量
        System.arraycopy(names, 0, extended, 0, names.length);
        for (String str : extended) {
            System.out.println(str);
        }
    }

    //数组填充
    private static void arrFill() {
        int array[] = new int[6];
        Arrays.fill(array, 100);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        System.out.println();
        //从第三位到第六位填充为50
        Arrays.fill(array, 3, 6, 50);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    //数组合并
    private static void arrAddAll() {
        String a[] = {"A", "E", "I"};
        String b[] = {"O", "U"};
        List list = new ArrayList(Arrays.asList(a));
        list.addAll(Arrays.asList(b));
        Object[] c = list.toArray();
        System.out.println(Arrays.toString(c));
    }

    //数组最大值最小值
    private static void arrMinaAndMax() {
        Integer[] numbers = {8, 3, 2, 1, 4, 7, 6, 5};
        int min = (int) Collections.min(Arrays.asList(numbers));
        int max = (int) Collections.max(Arrays.asList(numbers));
        System.out.println("最小值: " + min);
        System.out.println("最大值: " + max);
    }

    //数组输出
    private static void arrPrint() {
        String[] greeting = new String[3];
        greeting[0] = "This is the greeting";
        greeting[1] = "for all the readers from";
        greeting[2] = "Java Source .";
        for (int i = 0; i < greeting.length; i++) {
            System.out.println(greeting[i]);
        }
    }


    //反转
    private static void arrReverse() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        arrayList.add("e");
        System.out.println("反转前排序: " + arrayList);
        Collections.reverse(arrayList);
        System.out.println("反转后排序: " + arrayList);

    }

    //数组长度
    private static void arrLength() {
        String[][] data = new String[2][5];
        System.out.println("第一维数组长度: " + data.length);
        System.out.println("第二维数组长度: " + data[0].length);
    }

    //数组排序及原元素查找
    private static void arrSortAndFind() {
        int array[] = {2, 5, -2, 6, 3, 10, 0, -3, -7, -9, 4};
        Arrays.sort(array);
        printArray("数组排序的结果： ", array);
        int index = Arrays.binarySearch(array, -1);
        System.out.println("元素 -1 在index第 " + index + " 个位置");

        int newIndex = -index - 1;
        array = insertElement(array, -1, newIndex);
        printArray("数组添加元素 -1", array);
    }

    private static int[] insertElement(int original[],
                                       int element, int index) {
        int length = original.length;
        int destination[] = new int[length + 1];
        System.arraycopy(original, 0, destination, 0, index);
        destination[index] = element;
        System.arraycopy(original, index, destination, index
                + 1, length - index);
        return destination;
    }

    private static void printArray(String message, int array[]) {
        System.out.println(message + " : [length : " + array.length + "]");
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(array[i]);
        }
        System.out.println();
    }

    public static String[] union(String[] arr1, String[] arr2) {
        Set<String> set = new HashSet<String>();
        for (String str : arr1) {
            set.add(str);
        }
        for (String str : arr2) {
            set.add(str);
        }
        String[] result = {};
        return set.toArray(result);
    }
}
