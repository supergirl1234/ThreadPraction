package com.lei;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/*
* ConcurrentModificationException 异常  是JUC异常
* */
public class Main3 {

    public static void main(String[] args) {
        //List<String> list=new ArrayList<>();//ArrayList线程不安全

        /*那么，如何解决ArrayList线程不安全的问题：
         * 1、将ArrayList替换成线程安全的Vector
         * 2、使用Collections.synchronizedList(new ArrayList<>())
         * 3、JUC接口CopyOnWriteArrayList，实现读写分离，这样就不会发生异常
         *    实现过程：将原来的list复制一份，然后添加元素也是在这个新的复制的list中添加，添加完之后，再让该新复制的list代替原来的list
         *
         * */
        //List<String> list=new Vector<>();
        //List<String> list=Collections.synchronizedList(new ArrayList<>());
        List<String> list=new CopyOnWriteArrayList<>();
        for(int i=0;i<10;i++){

            new Thread(()->{

                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*当下面两行代码同时出现时，可能出现ConcurrentModificationException异常，原因是因为：可能同时进行了读写，*/
                /*ArrayList线程不安全*/
                list.add("a");
                System.out.println(list);
                /*在输出结果中，不是按顺序递增的，原因在于java内存模型（JMM）*/



            }).start();
        }
    }
}
