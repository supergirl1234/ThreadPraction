package com.lei.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
* ReentrantLock 还具备限时性的特点，可以判断某个线程在⼀定时间内能否获取到锁，tryLock 返回⼀
*   个 boolean 的值，true 表示可以拿到锁，false 表示拿不到锁。
*
* */
public class LockTest4 {

    public static void main(String[] args) {

        TryLock object=new TryLock();
        new Thread(()->{
            object.tryLock();
        },"A").start();
        new Thread(()->{
            object.tryLock();
        },"B").start();
    }
}

class TryLock{
    private ReentrantLock lock=new ReentrantLock();
    public void tryLock(){

        try {
            /*3秒之内能否获取到锁*/
            if(lock.tryLock(3,TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName()+"拿到了锁");
                TimeUnit.SECONDS.sleep(2);//休不休眠，休眠时间长短，会导致程序的结果不一样
                //TimeUnit.SECONDS.sleep(5);//休不休眠，休眠时间长短，会导致程序的结果不一样
            }else {
                System.out.println(Thread.currentThread().getName()+"没有拿到锁");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {

            /*如果不释放锁，第一个线程拿到锁之后，其他线程不可能拿到锁*/
           /* 如果持有锁*/
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }
}