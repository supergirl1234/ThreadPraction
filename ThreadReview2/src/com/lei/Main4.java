package com.lei;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main4 {

    public static void main(String[] args) {

        Set<String> set=new HashSet<>();
        for(int i=0;i<10;i++){

            final int temp=i;
            /*再Lambada表达式中只能访问final修饰的变量*/
            new Thread(()->{
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                set.add(String.valueOf(temp));
                System.out.println(set);
            }).start();
        }
    }
}
