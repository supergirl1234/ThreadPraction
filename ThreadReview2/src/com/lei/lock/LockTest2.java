package com.lei.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*使用 lock锁*/

/*
* synchronized 和 Lock 的区别
1、synchronized 是关键字，Lock 是接⼝。
2、synchronized 通过 JVM 实现锁机制，Lock 通过 JDK 实现锁机制。
3、synchronized ⾃动上锁，⾃动解锁，Lock ⼿动上锁，⼿动解锁。
4、synchronized ⽆法判断是否可以获得锁，Lock 可以判断是否拿到了锁。
5、synchronized 拿不到锁就会⼀直等待，Lock 不⼀定会⼀直等下去。
6、synchronizde 是⾮公平锁，Lock 可以设置是否为公平锁。
*
*
* */
public class LockTest2 {

    public static void main(String[] args) {
        Ticket2 ticket=new Ticket2();
        new Thread(()->{
            for(int i=0;i<40;i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(()->{
            for(int i=0;i<40;i++) {
                ticket.sale();
            }
        },"B").start();
    }
}
class  Ticket2{
    private  Integer saleNum=0;
    private  Integer lastNum=30;
    Lock lock=new ReentrantLock();

    public  void sale(){


           lock.lock();//上锁

        if(lastNum>0){

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            saleNum++;
            lastNum--;
            System.out.println(Thread.currentThread().getName()+"卖出"+saleNum+"张票，还剩"+lastNum+"张票");
        }
        lock.unlock();//解锁

    }
}
