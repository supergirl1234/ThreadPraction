package com.lei.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {

    public static void main(String[] args) {

        MyCallable myCallable=new MyCallable();
        FutureTask futureTask=new FutureTask(myCallable);
        Thread thread1=new Thread(futureTask);
        Thread thread2=new Thread(futureTask);
        thread1.start();
        thread2.start();//Callable有缓存
        /*获取Callable的返回值*/
        try {
            System.out.println(futureTask.get());//futureTask的get()方法会阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class  MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("callable");
        return "hello";
    }
}
