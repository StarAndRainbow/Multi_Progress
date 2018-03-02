package com.gzgamut.multi_progress;

import java.io.Serializable;

/**
 * 2018/3/1
 * guanbp@gzgamut.com
 */
public class UserSerializableTest  implements Serializable {


    private static final long serialVersionUID = 8711368828010083044L;


   private  String name;
   private int age;


    public UserSerializableTest(String name, int age) {
        this.name = name;
        this.age = age;
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
}
