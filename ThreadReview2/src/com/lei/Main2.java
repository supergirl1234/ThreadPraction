package com.lei;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* 生产者、消费者
* Lock的方式
* Lock的Condition接口也提供了类似Object的监视方法，与Lock配合可以实现等待/通知模式，Condition对象依赖于Lock对象的
*
* */
public class Main2 {

    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                data.product();
            }
        }, "生产者").start();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                data.consumer();
            }
        }, "消费者").start();
    }


}
class Data2{
    private  Integer num=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    /*生产者*/
    public  void product(){
        try {
            lock.lock();//加锁
            while (num >= 20) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;
            condition.signal();//唤醒consumer
            System.out.println(Thread.currentThread().getName() + "--" + num);
        }catch (Exception e){
          e.printStackTrace();
        }finally {
            lock.unlock();//释放锁
        }
    }
    /*消费者*/
    public  void consumer(){
        try {
            lock.lock();//加锁
            while (num == 0) {//如果num==0，则阻塞，不让他再减了
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num--;
            condition.signal();//唤醒product
            System.out.println(Thread.currentThread().getName() + "--" + num);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();//释放锁
        }

    }
}

