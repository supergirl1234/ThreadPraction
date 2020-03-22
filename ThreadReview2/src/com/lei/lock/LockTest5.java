package com.lei.lock;

import java.util.concurrent.TimeUnit;

/*
* 死锁
* */
public class LockTest5 {

    public static void main(String[] args) {

        DeadLock deadLock1=new DeadLock(1);
        DeadLock deadLock2=new DeadLock(2);

        /*在下面的代码中，
        A线程获取到data1的锁，没有释放，准备去获取data2的锁；
        B线程获取到data2的锁，没有释放，准备去获取data1的锁；
        但因为对方都没有释放各自想要的那个对象的锁，所以就死锁在这里了*/
        new Thread(()->{
            deadLock1.lock();
        },"A").start();

        new Thread(()->{
            deadLock2.lock();
        },"B").start();

    }
}
class Data{

}
class  DeadLock{

    private  Integer num;
    public  static Data data1=new Data();
    public  static Data data2=new Data();

    public DeadLock(Integer num) {
        this.num = num;
    }

    public void lock(){
        if(num==1){

            synchronized (data1){
                System.out.println(Thread.currentThread().getName()+"获取到data1，等待data2");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (data2){
                    System.out.println(Thread.currentThread().getName()+"获取到data2了");
                }
            }
        }

        if(num==2){

            synchronized (data2){
                System.out.println(Thread.currentThread().getName()+"获取到data2，等待data2");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (data1){
                    System.out.println(Thread.currentThread().getName()+"获取到data1了");
                }
            }
        }
    }
}
