package com.lei.synchron;

import java.util.concurrent.TimeUnit;

public class Test4 {

    public static void main(String[] args) {
        User user=new User();
        new Thread(()->{
            user.count();
        },"A").start();
        new Thread(()->{
            user.count();
        },"B").start();
        /*输出结果为2 2*/
    }
}
class User{
    private  Integer num=0;
    private  Integer id=0;
    public void  count(){
        synchronized (num){//如果将该处的变成 synchronized (id)，则能完成同步
            num++;//因为这个操作，导致几个线程的num不是同一个对象了，所以导致无法完成同步
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"是第"+num+"位访客");
        }
    }
}
