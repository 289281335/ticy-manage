package com.ticy.manage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author tkk
 * @Time 2021/8/18 10:10
 * @Description todo
 */
public class TaskPoolConfig {


    /**
     *   默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
     *    当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
     *  当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
     */
    @Value("${task.pool.corePoolSize}")
    private Integer corePoolSize;//设置核心线程数
    @Value("${task.pool.maxPoolSize}")
    private Integer maxPoolSize;//设置最大线程数
    @Value("${task.pool.keepAliveSeconds}")
    private Integer keepAliveSeconds;//设置线程活跃时间（秒）
    @Value("${task.pool.queueCapacity}")
    private Integer queueCapacity;//设置队列容量

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        //创建一个线程池
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数10：线程池创建时候初始化的线程数
        executor.setCorePoolSize(corePoolSize);
        // 最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(maxPoolSize);
        // 缓冲队列200：用来缓冲执行任务的队列
        executor.setQueueCapacity(queueCapacity);
        // 允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("taskExecutor-");
        // 线程池对拒绝任务的处理策略：
        // 这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，
        // 该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；
        // 如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
