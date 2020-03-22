package com.lei.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
* 加了读写锁的
* 写入锁也叫独占锁，只能被一个线程占用，读取锁也叫共享锁，多个线程可以同时占用
* */
public class ReadWriteLockTest2 {

    public static void main(String[] args) {

        Cache2 cache2=new Cache2();


        /*解决同时写入数据的问题的方法是：使用ReadWriteLock锁*/
        /*写线程*/
        for(int i=0;i<5;i++){
            final int temp=i;
            new Thread(()->{
                cache2.write(temp,String.valueOf(temp));
            }).start();
        }
        /*读线程*/
        for(int i=0;i<5;i++){
            final int temp=i;
            new Thread(()->{
                cache2.read(temp);
            }).start();
        }
    }
}
class Cache2{

    private Map<Integer,String> map=new HashMap<>();
    ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    /*
     * 写
     * */
    public void write(Integer key,String value){
        readWriteLock.writeLock().lock();//加写锁
        System.out.println(key+"开始写入....");
        map.put(key,value);
        System.out.println(key+"写入完毕....");
        readWriteLock.writeLock().unlock();//解锁
    }
    /*
     * 读
     * */
    public void read(Integer key){
        /*读锁可以不加，因为是允许同时读的*/
        readWriteLock.readLock().lock();//加读锁
        System.out.println(key+"开始读取....");
        String value=map.get(key);
        System.out.println(key+"读取完毕....");
        readWriteLock.readLock().unlock();//解锁
    }
}