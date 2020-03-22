package com.lei.lock;

import java.util.concurrent.TimeUnit;


//使用synchronized
public class LockTest1 {

    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        new Thread(()->{
            for(int i=0;i<40;i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(()->{
            for(int i=0;i<40;i++) {
                ticket.sale();
            }
        },"B").start();
    }
}

class  Ticket{
    private  Integer saleNum=0;
    private  Integer lastNum=30;

    public synchronized void sale(){
        if(lastNum>0){

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            saleNum++;
            lastNum--;
            System.out.println(Thread.currentThread().getName()+"卖出"+saleNum+"张票，还剩"+lastNum+"张票");
        }
    }
}
