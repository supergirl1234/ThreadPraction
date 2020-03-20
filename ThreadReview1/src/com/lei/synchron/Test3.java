package com.lei.synchron;

import java.util.concurrent.TimeUnit;

public class Test3 {

    public static void main(String[] args) {

        Data3 data=new Data3();
       /* for(int i=0;i<5;i++){
            Integer num=Integer.valueOf(i);
            new Thread(()->{
            *//*    data.func1(num);*//*
              data.func2(num);
            }).start();
        }*/
        for(int i=0;i<5;i++){
            //Integer num=Integer.valueOf(1);
            Integer num=Integer.valueOf(128);
            new Thread(()->{

                data.func2(num);
            }).start();
        }
    }
}
/*
* 1、输出start
* 2、间隔3秒
* 3、输出end
*
* */
class Data3{


    public   void func1(Integer num) {
        synchronized (Data3.class) {//锁的是同一个类
            System.out.println("start....");
            try {
                TimeUnit.SECONDS.sleep(3);//休眠3秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end....");
        }
    }
    public   void func2(Integer num) {
        synchronized (num) {//锁的是同一个num对象
            System.out.println(num+"start....");
            try {
                TimeUnit.SECONDS.sleep(3);//休眠3秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end....");
        }
    }

}
