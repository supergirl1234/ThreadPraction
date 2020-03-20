package com.lei.synchron;

import java.util.concurrent.TimeUnit;

public class Test2 {

    public static void main(String[] args) {

        /*下面这种情况，先1后2*/
       /* Data2 data1=new Data2();
        Data2 data2=new Data2();
        new  Thread(()->{
            data1.func1();
        }).start();
        new  Thread(()->{
            data2.func2();
        }).start();*/


       /*下面这种情况先3后1*/
        Data2 data1=new Data2();
        new  Thread(()->{
            data1.func1();
        }).start();
        new  Thread(()->{
            data1.func3();
        }).start();
    }
}

class Data2 {

    /*synchronized修饰静态方法，锁的是class本身，本代码中锁的是Data2这个类本身，锁定的不是对象*/
    public synchronized static void func1() {
        try {
            TimeUnit.SECONDS.sleep(3);//休眠3秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1....");
    }

    public synchronized static void func2() {

        System.out.println("2....");
    }
    public  synchronized void func3() {

        System.out.println("3....");
    }
}
