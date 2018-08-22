package com.anjiplus.springboothelloword.user;

/**
 * @Auther: Kean
 * @Date: 2018/8/22 下午7:12
 * @Description:
 */
public class User {
    private int id;

    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
