package com.ticy.manage.test;

import com.ticy.manage.service.impl.HandThreadList;
import com.ticy.manage.service.impl.TestRunable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @Author tkk
 * @Time 2021/8/13 11:17
 * @Description todo
 */

@RestController
public class MyThreadTest {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;


    /*
     * @Author tkk
     * @Description //模拟多线程处理List,使用callable返回值。分段求和，再最终求和
     * @Date  2021/8/18 15:20
     * @Param []
     * @return void
     **/
    @GetMapping("/threadTest")
    public int threadTest() {
        /*TestRunable testRunable1 = new TestRunable(1000);
        TestRunable testRunable2 = new TestRunable(1500);
        executor.execute(testRunable1);
        executor.submit(testRunable2);*/


        long f1 = System.currentTimeMillis();
        //制作list
        List<Integer> list = new ArrayList();
        for (int i = 1; i <= 10000; i++) {
            list.add(i);
        }
        int size = list.size();
        //每个线程处理的list记录数
        int count = 1000;
        int threadNum = size % count == 0 ? size / count : (size / count) + 1;
        HandThreadList handThreadList = new HandThreadList(taskExecutor);
        int sum  = handThreadList.handList(list, threadNum);
        long f2 = System.currentTimeMillis();
        System.out.println("线程数："+threadNum+"   总耗时："+(f2-f1) +"   最终返回结果是："+sum);
        return sum;


    }


}
