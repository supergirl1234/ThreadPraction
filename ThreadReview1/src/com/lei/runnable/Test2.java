package com.lei.runnable;

import java.util.concurrent.TimeUnit;

public class Test2 {

    public static void main(String[] args) {
        Account2 account2=new Account2();
        /*通过下面这种方式可以将Runnable和资源分开类，不会耦合，任务就是任务，资源就是资源*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                account2.count();
            }
        },"A").start();
      new Thread(()->{
          account2.count();
      },"B").start();
    }
}


/*将资源Account2与Runnable解耦*/
class Account2 {
    private  static  int num;
    public  synchronized void  count(){
        num++;
        try {
            /*实际开发中不会使用sleep进行休眠，而是使用JUC的方式：TimeUnit.SECONDS.sleep(1);但其底层还是使用的Thread.sleep方法*/
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"是第"+num+"位访客");
    }
}