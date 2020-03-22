package com.lei.jucPack;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
* CyclicBarrier：加法计数器
* */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        /*定义一个计数器，当计数器的值累加到10，则输出“放行”*/
        CyclicBarrier cyclicBarrier=new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("放行");
            }
        });

      /*  for(int i=0;i<10;i++){
            final  int temp=i;
            new Thread(()-> {
                System.out.println("---->" + temp);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }*/


       /* for(int i=0;i<13;i++){
            final  int temp=i;
            new Thread(()-> {
                System.out.println("---->" + temp);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }*/

        for(int i=0;i<30;i++){//CyclicBarrie是每次从0加到10之后，又清零，再重新加
            final  int temp=i;
            new Thread(()-> {
                System.out.println("---->" + temp);
                try {
                    cyclicBarrier.await();//await()执行CyclicBarrier的业务，await()中的Runnable执行一次之后，计数器清零，等待下一次执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
