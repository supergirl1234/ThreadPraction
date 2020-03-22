package com.lei.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/*计算0-20亿的和*/

public class ForkJoinTest {

    public static void main(String[] args) {

        /*普通方法*/
      /*  Long sum=0L;
        Long startTime=System.currentTimeMillis();
        for(Long i=0L;i<=20_0000_0000L;i++){
            sum+=i;
        }
        Long endTime=System.currentTimeMillis();
        System.out.println(sum);
        System.out.println("花费时长："+(endTime-startTime));*/



      /*使用ForkJoin框架*/
        Long startTime=System.currentTimeMillis();
        ForkJoinPool forkJoinPool=new ForkJoinPool();//池
        ForkJoinTask<Long> task=new ForkJoinTest2(0L,20_0000_0000L);//总的大任务
        forkJoinPool.execute(task);//执行
        Long sum=0L;
        try {
            sum=task.get();//get()方法获取返回值，结果
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Long endTime=System.currentTimeMillis();
        System.out.println(sum);
        System.out.println("耗时："+(endTime-startTime));

    }
}
