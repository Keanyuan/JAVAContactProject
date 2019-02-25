package com.anji.plus.study.javapro;

/**
 * 我的数组帮助类
 * 定义一个用于操作数组的工具类。
 * 比如：获取最值，排序，折半。
 *
 * @version V1.0
 * @author: Kean
 */
public class ArrayTool {
    /**
     * 该类的空参数构造函数。
     */
    private ArrayTool() {
    }

    /**
     * 获取int数组的最大值。
     *
     * @param arr 用于接收一个int类型的数组。
     * @return 返回该数组中的最大值。
     */
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > max)
                max = arr[x];
        }

        return max;
    }

    /**
     * 对int数组进行从小打到的排序。
     *
     * @param arr 用于接收一个int类型的数组。
     */
    public static void bubbleSort(int[] arr) {
        //javadoc -d /Users/Kean/Desktop/DOC  ArrayTool.java -encoding UTF-8 -charset UTF-8
    }
}
