package com.lei.jucPack;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
* Semaphore：实际开发中主要用来做限流操作，即限制可以访问某些资源的线程数量
*
* 初始化
* 获取许可
* 释放许可
* */
public class SemaphoreTest {

    public static void main(String[] args) {
        /*初始化*/
        Semaphore  semaphore=new Semaphore(5);//同时只能进五个人
        for(int i=0;i<15;i++){
            new Thread(()->{
                try {
                    /*获得许可*/
                    semaphore.acquire();//acquire()：等待获取许可，如果没有名额，则等待
                    System.out.println(Thread.currentThread().getName()+"进入门店");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"离开门店");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    /*释放许可*/
                    semaphore.release();//release()：释放许可，并且唤醒等待的线程
                }
            }).start();
        }
    }
}
