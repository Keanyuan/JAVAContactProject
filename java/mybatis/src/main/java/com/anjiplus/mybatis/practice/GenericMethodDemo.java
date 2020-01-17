package com.anjiplus.mybatis.practice;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/22 17:15
 * @Description: 泛型案例
 */
public class GenericMethodDemo {

    public static <T> void printArray(T[] inputArray) {
        for (T element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    public static  <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x;// 假设x是初始最大值
        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0){
            max = z;
        }
        return  max;
    }

    //比较大小
    public static  <T extends Comparable<T>> T maximum(T... values) {
        T max = values[0];// 假设x是初始最大值
        for (T value : values) {
            if (value.compareTo(max) > 0) {
                max = value;
            }
        }
        return  max;
    }



    private static void test3(){
        Box<Integer> integerBox = new Box<Integer>();
        Box<String> stringBox = new Box<String>();

        integerBox.add(new Integer(10));
        stringBox.add(new String("hello world"));

        System.out.printf("Integer Value :%d\n\n", integerBox.get());
        System.out.printf("String Value :%s\n", stringBox.get());

    }

    private static void test2(){


        System.out.printf("Max of %d, %d and %d is %d \n\n", 3,4,5,maximum(3,4,5));
        System.out.printf("Max is %d \n\n",maximum(3,4,5,6,7));
        System.out.printf( "Maxm of %.1f,%.1f and %.1f is %.1f\n\n",
                6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ) );

        System.out.printf( "Max of %s, %s and %s is %s\n","pear",
                "apple", "orange", maximum( "pear", "apple", "orange" ) );
        System.out.printf("Max is %s \n\n",maximum("adf","ccc","sfer","sfv","rde"));
        System.out.printf("Max is %s \n\n",maximum("adf"));

    }
    private static void test1(){
        // 创建不同类型数组： Integer, Double 和 Character
        Integer[] intArray = {1, 2, 3, 4, 5, 6};
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

        System.out.println( "Array integerArray contains:" );
        printArray(intArray);

        System.out.println( "\nArray doubleArray contains:" );
        printArray(doubleArray);

        System.out.println( "\nArray characterArray contains:" );
        printArray(charArray);


    }

    public static void main(String[] args) {

        test3();

    }
}

/**
 * 类泛型
 * @param <T> 泛型
 */
class Box<T> {
    private T t;
    public void add(T t){
        this.t = t;
    }

    public T get(){
        return t;
    }
}