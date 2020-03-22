package com.lei.threadpool;

import java.util.concurrent.*;

public class ThreadPoolTest4 {

    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService
                =new ThreadPoolExecutor(
                2,
                3,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()   //DiscardOldestPolicy()拒绝策略
        );

        /*在DiscardPolicy()拒绝策略中，由于任务数量多于线程池中的最大线程数，所以会产生竞争，抛弃一个任务，
          只是抛弃的那个任务不确定，可能是最后一个业务，可能是等待队列中的第一个业务，不会抛出异常*/
        for(int i=0;i<6;i++){
            executorService.execute(()->{
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"->办理业务");
            });
        }
        executorService.shutdown();//关闭线程池
    }
}
