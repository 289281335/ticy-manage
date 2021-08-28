package com.ticy.manage.service.impl;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author tkk
 * @Time 2021/8/18 15:52
 * @Description todo
 */
public class TestCallable implements Callable<Integer> {

    private List<Integer> subList;

    public TestCallable(List<Integer> subList) {
        this.subList = subList;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (Integer i : subList) {
            sum = sum + i;
        }
        Thread.sleep(30);
        System.out.println("线程："+Thread.currentThread().getId()+"的返回值为："+sum);
        System.out.println("线程："+Thread.currentThread().getId() + "结束");
        return sum;
    }
}
