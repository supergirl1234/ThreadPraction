package com.lei.runnable;

import java.util.concurrent.TimeUnit;

public class Test3 {

    public static void main(String[] args) {

        A a=new A();
        /*任务线程*/
        new Thread(()->{
           for(int i=0;i<10;i++){
               a.test(i);
           }

        }).start();
        /*唤醒线程*/
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(7);//7秒钟之后，再调用test2方法唤醒线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.test2();
        }).start();
    }
}
class A{
    /*wait方法和notify方法都要在同步方法或者同步代码块中执行，否则会报错*/
    public synchronized void test(int i){
        if(i==5){
            try {
                this.wait();//使线程阻塞，需要手动唤醒，程序才能继续往下进行
                //this.wait(2000);//指定阻塞的时间，到达时间后，主动唤醒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i+"---------------");

    }

    public synchronized void test2(){
        this.notify();
    }
}
