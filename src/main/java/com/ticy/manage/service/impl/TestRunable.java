package com.ticy.manage.service.impl;

/**
 * @Author tkk
 * @Time 2021/8/18 10:34
 * @Description todo
 */
public class TestRunable implements Runnable{

    private int count;

    public TestRunable(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i <= count; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId()+":"+i);
        }

    }
}
