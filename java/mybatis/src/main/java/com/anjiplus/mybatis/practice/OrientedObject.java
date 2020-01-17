package com.anjiplus.mybatis.practice;

/**
 * @Auther: kean_qi
 * @Date: 2019/2/21 17:38
 * @Description:
 */
public class OrientedObject {

    public static void main(String[] args){
        extendsTest();
    }


    public static void a(){
    }

    public static void extendsTest(){
        Animal a = new Animal();
        Mammal m = new Mammal();
        Dog d = new Dog();
        d.move();
        d.bark();
        d.bark(" 并且声音响亮");
        System.out.println(m instanceof  Animal);
        System.out.println(d instanceof  Animal);
        System.out.println(d instanceof  Mammal);
        System.out.println(m instanceof  Biology);

    }
}

interface Biology{}

class Speed{}
class Animal {
    private int i;
    protected int j;
    public void move(){
        System.out.println("动物可以移动");
    }
}

//哺乳动物
class Mammal extends Animal implements Biology{
    private Speed speed;

    @Override
    public void move() {
        super.move();
        System.out.println("哺乳动物可以移动");
    }
}

//爬行动物
class Reptitle extends Animal{

}

class Dog extends Mammal{
    @Override
    public void move() {
        super.move();
        System.out.println("狗可以跑和走");
    }


    public void bark(){
        System.out.println("狗可以吠叫");
    }

    //重载
    public void bark(String s){
        System.out.println("狗可以吠叫" + s);
    }
}