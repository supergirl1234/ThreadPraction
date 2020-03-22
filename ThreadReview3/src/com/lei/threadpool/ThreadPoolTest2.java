package com.lei.threadpool;

import java.util.concurrent.*;

public class ThreadPoolTest2 {

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
                new ThreadPoolExecutor.AbortPolicy()  //AbortPolicy()拒绝策略
        );


        /*开始营业*/
        /*结果为：一个线程办理业务*/
       /* for(int i=0;i<1;i++){
            executorService.execute(()->{
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"->办理业务");
            });
        }*/


       /*结果为：线程池中的两个线程办理业务这两个业务*/
      /*  for(int i=0;i<2;i++){
            executorService.execute(()->{
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"->办理业务");
            });
        }*/

      /*结果为：线程池中的两个线程来办理这三个业务*/
        /*for(int i=0;i<3;i++){
            executorService.execute(()->{
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"->办理业务");
            });
        }*/

        /*结果为：线程池中的全部线程（三个）来办理这5个业务*/
      /*  for(int i=0;i<5;i++){
            executorService.execute(()->{
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"->办理业务");
            });
        }*/

      /*结果为：线程池中的三个线程来办理5个业务，有一个业务被被用于拒绝策略AbortPolicy，抛出异常了*/
        /*AbortPolicy()拒绝策略中，多余的那个任务，会被抛出异常*/
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
