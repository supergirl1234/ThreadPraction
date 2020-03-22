package com.lei.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
* 未加读写锁的：
* */
public class ReadWriteLockTest {

    public static void main(String[] args) {

        Cache cache=new Cache();

        /*下面会导致:同时写入的现象，但这种现象在实际情况中是不允许的*/
        /*解决此问题的方法是：使用ReadWriteLock锁*/
        /*写线程*/
        for(int i=0;i<5;i++){
            final int temp=i;
            new Thread(()->{
                cache.write(temp,String.valueOf(temp));
            }).start();
        }
        /*读线程*/
        for(int i=0;i<5;i++){
            final int temp=i;
            new Thread(()->{
                cache.read(temp);
            }).start();
        }
    }
}
class Cache{

    private Map<Integer,String> map=new HashMap<>();
    ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    /*
    * 写
    * */
    public void write(Integer key,String value){

        System.out.println(key+"开始写入....");
        map.put(key,value);
        System.out.println(key+"写入完毕....");

    }
    /*
     * 读
     * */
    public void read(Integer key){

        System.out.println(key+"开始读取....");
        String value=map.get(key);
        System.out.println(key+"读取完毕....");

    }
}