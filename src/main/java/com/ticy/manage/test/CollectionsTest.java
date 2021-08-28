package com.ticy.manage.test;

import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author tkk
 * @Time 2021/7/29 11:24
 * @Description todo
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
        Collections.addAll(list,
                new Student(18, "mimi"),
                new Student(20, "lisi"),
                new Student(20, "aisi"),
                new Student(19, "wangwu"),
                new Student(21, "zhangsan"));

        Collections.sort(list, new Comparator<Student>(

        ) {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() == o2.getAge() ? o1.getName().compareTo(o2.getName()) : o1.getAge() - o2.getAge();
            }
        });


        for (Student s : list) {
            System.out.println(s);
        }
    }


}
