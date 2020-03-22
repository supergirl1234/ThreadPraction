package com.lei.forkjoin;

import java.util.concurrent.RecursiveTask;
/*
 * Forkjoin是JDK1.7提供的多线程并发处理框架，本质上是对线程池的一种补充，
 *  它的核心思想就是将一个大型任务拆分成很多个小任务，分别执行，最后再将小任务的结果进行汇总
 *  本质就是：一个线程任务拆分成多个线程并发执行
 *
 * 工作窃取：
 *  A、B两个线程同时执行，A的任务比较多，B先执行完了，此时B将A的一部分拿过来，替A执行，从而提升效率；
 *
 * Forkjoin框架的使用需要用到两个类：
 *  ForkjoinTask:任务
 *  ForkjoinPool:线程池
 * ForkjoinTask拆分任务，ForkjoinPool提供线程对象来执行任务，之后合并
 *
 * 重点是搞定ForkjoinTask如何拆分任务？
 *  ForkjoinTask使用的是递归的思想
 *  递归：一个函数直接或间接调用自身，就是递归
 * */

/*使用ForkJoin框架需要继承RecursiveTask<Long>接口*/
public class ForkJoinTest2  extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private Long temp=100_0000L;//临界值

    public ForkJoinTest2(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if((end-start)<temp){//如果(end-start)<temp，达到临界值，则不能再拆分了
            Long sum=0L;
            for(Long i=start;i<=end;i++){
                sum+=i;
            }
            return  sum;
        }else{
            /*拆分的过程*/
            Long avg=(start+end)/2;
            ForkJoinTest2 task1=new ForkJoinTest2(start,avg);
            task1.fork();//递 就又去执行comput()方法了
            ForkJoinTest2 task2=new ForkJoinTest2(avg+1,end);
            task2.fork();//递
            return  task1.join()+task2.join();//归
        }

    }
}
