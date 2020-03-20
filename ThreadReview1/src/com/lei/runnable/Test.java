package com.lei.runnable;

public class Test {

    public static void main(String[] args) {

        Acount acount=new Acount();
        new Thread(acount,"A").start();
        new Thread(acount,"B").start();
    }
}

/*
* 统计程序的访问量
* */

/*下面的这种写法耦合度太高，将Runnable和Account耦合起来了，实际开发中，要做到解耦合*/
class Acount implements  Runnable{

    public static int num;
    @Override
    public void run() {
        num++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"是第"+num+"位访客");
    }
}
