package com.ticy.manage.test;

/**
 * @Author tkk
 * @Time 2021/7/29 11:24
 * @Description todo
 */
public class Student {


    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\''+
                '}';
    }
}
