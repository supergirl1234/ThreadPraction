package com.lei.threadpool;

import java.util.concurrent.*;

public class ThreadPoolTest3 {

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
                new ThreadPoolExecutor.DiscardPolicy()   //DiscardPolicy()拒绝策略
        );

        /*在DiscardPolicy()拒绝策略中，多余的那个最后一个任务被抛弃，不会抛出异常*/
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
