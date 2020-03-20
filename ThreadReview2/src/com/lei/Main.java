package com.lei;

/*
 * 生产者消费者模型
 * synchronized、wait/notify的方式
 * */
public class Main {
    public static void main(String[] args) {
        Data data = new Data();
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

class Data {
    private Integer num = 0;

    /*生产者*/
    public synchronized void product() {
        /*判断的时候，必须使用while循环，而不能使用if，因为使用if会存在线程虚假唤醒的问题，虚假唤醒是指wait
         * 方法会在除了notify以外的情况被唤醒，而此时是不应该被唤醒的,使用while可以多次检测，避免虚假唤醒的问题*/
        /*  while (num!=0){*/
        while (num >= 20) {//如果num的超过某个值，即容器满了之后，则阻塞，不让他再生产了
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        this.notify();//唤醒consumer
        System.out.println(Thread.currentThread().getName() + "--" + num);
    }

    /*消费者*/
    public synchronized void consumer() {
        while (num == 0) {//如果num==0，则阻塞，不让他再减了
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        this.notify();//唤醒product
        System.out.println(Thread.currentThread().getName() + "--" + num);

    }
}
