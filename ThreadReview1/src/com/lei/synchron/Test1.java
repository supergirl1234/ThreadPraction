package com.lei.synchron;

import java.util.concurrent.TimeUnit;

public class Test1 {

    public static void main(String[] args) {

     /*   Data data=new Data();
        new  Thread(()->{
            data.func1();
        }).start();
        new  Thread(()->{
            data.func2();
        }).start();*/


       /* Data data = new Data();
        Data data2 = new Data();
        new Thread(() -> {
            data.func1();
        }).start();
        new Thread(() -> {
            data2.func2();
        }).start();*/

        Data data=new Data();
        new  Thread(()->{
            data.func1();
        }).start();
        new  Thread(()->{
            data.func3();
        }).start();
    }
}

class Data {

    /*synchronized修饰非静态方法，锁的是即方法的调用者，即调用func1方法的对象*/
    public synchronized void func1() {
        try {
            TimeUnit.SECONDS.sleep(3);//休眠3秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1....");
    }

    public synchronized void func2() {

        System.out.println("2....");
    }

    public void func3() {

        System.out.println("3....");
    }

}
