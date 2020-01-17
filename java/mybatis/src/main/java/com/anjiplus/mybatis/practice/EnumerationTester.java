package com.anjiplus.mybatis.practice;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/22 09:45
 * @Description:
 */
public class EnumerationTester {

    public void changeEnumeration() {
        Enumeration days;
        Vector dayNames = new Vector();
        dayNames.add("Sunday");
        dayNames.add("Monday");
        dayNames.add("Tuesday");
        dayNames.add("Wednesday");
        dayNames.add("Thursday");
        dayNames.add("Friday");
        dayNames.add("Saturday");
        days = dayNames.elements();
        while (days.hasMoreElements()){
            System.out.println(days.nextElement());
        }
    }

    Signal color = Signal.GREEN;

    public void change() {
        switch (color){
            case RED:
                color = Signal.RED;
                break;
            case GREEN:
                color = Signal.GREEN;
                break;
            case YELLOW:
                color = Signal.YELLOW;
                break;
            default:
                color = Signal.RED;
                break;

        }
    }

    public static void changeColor() {
        System.out.println(Color.getName(1));
        System.out.println(Color.BLANK.toString());
        Color color = Color.RED;
        System.out.println(color.toString());
        color.print();
    }

    public static void testImplementInterface() {
        for (Food.DessertEnum dessertEnum : Food.DessertEnum.values()) {
            System.out.print(dessertEnum + "  ");
        }
        System.out.println();

        for (Food.CoffeeEnum coffeeEnum : Food.CoffeeEnum.values()){
            System.out.print(coffeeEnum + "   ");
        }
        System.out.println();

        Food food = Food.DessertEnum.CAKE;
        System.out.println(food);

        food = Food.CoffeeEnum.BLACK_COFFEE;
        System.out.println(food);
    }


    /**
     * 循环枚举,输出ordinal属性；若枚举有内部属性，则也输出。(说的就是我定义的TYPE类型的枚举的typeName属性)
     */
    private static void forEnum(){
        for (SimpleEnum simpleEnum : SimpleEnum.values()){
            System.out.println(simpleEnum + " ordinal " + simpleEnum.ordinal());
        }
        System.out.println("-----------------");
        for (TypeEnum type : TypeEnum.values()) {
            System.out.println("type = " + type +
                    "  type.name = " + type.name() +
                    "  typeName = " + type.getTypeName() +
                    " ordinal " + type.ordinal());
        }
    }

    private static void testSwitchEnumCase(){
        String typeName = "f5";
        TypeEnum typeEnum = TypeEnum.fromTypeName(typeName);
        if (typeEnum == null){
            return;
        }
        switch (typeEnum){
            case FIREWALL:
                break;
            case SECRET:
                break;
            case BALANCE:
                break;
            default:
                System.out.println("defaylt");
                break;
        }
        System.out.println("枚举名称(即默认自带的属性 name 的值)是：" + typeEnum.name());
        System.out.println("排序值(默认自带的属性 ordinal 的值)是：" + typeEnum.ordinal());
        System.out.println("枚举的自定义属性 typeName 的值是：" + typeEnum.getTypeName());
    }

    /**
     * 在Java代码使用枚举
     */
    private static void useEnumInJava(){
        String typeName = "f5";
        TypeEnum type = TypeEnum.fromTypeName(typeName);
        if (TypeEnum.BALANCE.equals(type)) {
            System.out.println("根据字符串获得的枚举类型实例跟枚举常量一致");
        } else {
            System.out.println("代码错误");
        }
        
    }

    /*enum*/
    /**
     * 季节枚举
     * Ordinal属性，对应的配列顺序从0开始
     */
    private enum SimpleEnum {
        SPRING,
        SUMMER,
        AUTUMN,
        WINTER
    }

    /*常用类型(带参数的枚举常量)*/
    private enum TypeEnum {
        FIREWALL("firewall"),
        SECRET("secretMac"),
        BALANCE("f5");
        private String typeName;

        TypeEnum(String typeName) {
            this.typeName = typeName;
        }

        /*根据类型名称，返回类型的枚举实例*/
        public static TypeEnum fromTypeName(String typeName) {
            for (TypeEnum type: TypeEnum.values()){
                if (type.getTypeName().equals(typeName)){
                    return  type;
                }
            }
            return null;
        }

        public String getTypeName() {
            return typeName;
        }
    }


    public static void main(String[] args) {
        forEnum();
        useEnumInJava();
        testSwitchEnumCase();
    }
}

interface Behaviour {
    void print();
    String getInfo();
}

enum Color implements Behaviour{

    RED("红色", 1),
    GREEN("绿色", 2),
    BLANK("白色", 3),
    YELLOW("黄色", 4);

    private String name;
    private int index;

    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public static String getName(int index){
        for (Color c : Color.values()) {
            if (c.getIndex() == index){
                return c.name;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return this.index + " " + this.name;
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String getInfo() {
        return this.name;
    }
}

enum Signal{
    GREEN, YELLOW, RED
}


interface Food {
    enum CoffeeEnum implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }

    enum DessertEnum implements Food {
        FRUIT, CAKE, GELATO
    }
}


