package com.lei.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* lock锁的可中断特性：允许在某个线程等待时，主动去中断线程，不需要获取锁，但是会抛出异常
* */
public class LockTest3 {

    public static void main(String[] args) {

        Lock lock=new ReentrantLock();
        lock.lock();//上锁，主线程拿到了锁
        Thread thread=new Thread(()->{
            try {
                lock.lockInterruptibly();//去中断锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        thread.start();
        System.out.println(Thread.currentThread().getName()+" interrupted");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();//去中断拿到锁的线程

    }
}
