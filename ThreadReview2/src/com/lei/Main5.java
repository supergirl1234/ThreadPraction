package com.lei;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Main5 {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        for(int i=0;i<10;i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*UUID.randomUUID()是产生一个随机数*/
            map.put(UUID.randomUUID().toString().substring(0, 3), UUID.randomUUID().toString().substring(0, 3));
            System.out.println(map);
        }
    }
}
