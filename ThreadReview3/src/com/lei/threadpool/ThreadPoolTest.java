package com.lei.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* 线程池：
* 预先创建好一定数量的线程对象，存入缓冲池中，需要用的时候直接从缓冲池中取出，用完之后不要销毁，还回到缓冲池中；
*
* 好处：
*   1、提高线程的利用率
*   2、提高响应速度
*   3、便于统一管理线程对象
*   4、可控制最大并发数
*
* */
public class ThreadPoolTest {

    public static void main(String[] args) {
    /*    *//*单例线程池，缓冲池中只有一个线程读写*//*
        ExecutorService executorService=Executors.newSingleThreadExecutor();*/

     /*   *//*指定线程个数的线程池*//*
        ExecutorService executorService=Executors.newFixedThreadPool(5);*/

        /*缓存线程池，线程池中的线程个数不固定，线程个数由电脑硬件配置决定*/
        ExecutorService executorService=Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            final  int temp=i;
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+":"+temp);
            });
        }
        executorService.shutdown();
    }
}
