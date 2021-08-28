package com.ticy.manage.service.impl;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * @Author tkk
 * @Time 2021/8/18 15:43
 * @Description todo
 */
public class HandThreadList {

    private ThreadPoolTaskExecutor taskExecutor;

    public HandThreadList(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public int handList(List<Integer> list, int threadNum) {

        try {
            System.out.println("--------主线程开始---------");
            int size = list.size();
            if (threadNum == 0) {
                return 0;
            }
            int group = size % threadNum == 0 ? size / threadNum : (size / threadNum + 1);

            List<Integer> sublist = new ArrayList<>();
            List<Integer> resultList = new ArrayList<>();
            for (int i = 0; i < threadNum; i++) {
                int end = (i + 1) * group;
                int start = i * group;
                if (i == threadNum - 1) {
                    end = size;
                }
                sublist = list.subList(start, end);
                TestCallable callable = new TestCallable(sublist);
                Future<Integer> subResult = taskExecutor.submit(callable);
                resultList.add(subResult.get());

            }
            int sum = 0;
            for (Integer i : resultList) {
                sum = sum + i;
            }
            System.out.println("--------主线程结束---------");
            return sum;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}
