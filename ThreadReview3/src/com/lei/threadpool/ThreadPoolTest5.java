package com.lei.threadpool;

import java.util.concurrent.*;

public class ThreadPoolTest5 {

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
                new ThreadPoolExecutor.CallerRunsPolicy()   //CallerRunsPolicy()拒绝策略
        );

        /*CallerRunsPolicy()拒绝策略中，由于任务数量多于线程池中的最大线程数，则多余的那个任务，由调用它的那个线程处理，
            即，谁调用，谁处理多余的那个业务，不会抛出异常；
            在本次代码中，是mian线程调用的线程池，则多余的那个业务就由main线程处理
            */
      /*  for(int i=0;i<6;i++){
            executorService.execute(()->{
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"->办理业务");
            });
        }*/

        for(int i=0;i<8;i++){
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
