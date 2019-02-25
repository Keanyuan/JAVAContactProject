package com.anji.plus.mystudy.javapro;

import java.io.*;

/**
 * @Auther: Kean
 * @Date: 2019/2/23 4:03 PM
 * @Description:
 */
public class SerializableDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(23);
        user.setName("zhangsan");
        user.setSex("男");

        System.out.println(user);
        File file = new File("tempfile.txt");

        ObjectOutputStream oos = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(user);
            oos.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectInputStream ois = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ois = new ObjectInputStream(fileInputStream);
            User newUser = (User) ois.readObject();
            System.out.println(newUser);
            ois.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

}

class User implements Serializable {
    private String name;
    private int age;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User { name = " + name + ",age = " + age + ",sex = " + sex + " }";

    }
}