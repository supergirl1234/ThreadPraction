package com.lei.jucPack;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;

/* 使用CountDownLatch实现剑法计数器*/
/* new CountDownLatch(10) 、countDown() 、await()这三个方法必须配合使用，
    创建对象时赋的值是多少，countDown()就要执行多少次，否则计数器不会清零，计数器就不会停止，其他线程无法唤醒*/
public class CountDownLatchTest {
    public static void main(String[] args) {
        /*优先执行，执行完毕之后，才能执行 main*/
        /*1、实例化计数器 100*/
        CountDownLatch countDownLatch=new CountDownLatch(10);
        new Thread(()->{
            for(int i=0;i<9;i++){
                System.out.println("++++++Thread");
                countDownLatch.countDown();//countDown()  是计数器减一
            }
        }).start();

        for(int i=0;i<1;i++){
          countDownLatch.countDown();
        }

      /*  2、调用await方法*/
        try {
            countDownLatch.await();//await() 当计数器减为0，计算器停止，唤醒其他线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<10;i++){
            System.out.println("main-----");
        }
    }
}
