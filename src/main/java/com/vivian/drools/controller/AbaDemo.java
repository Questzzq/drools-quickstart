package com.vivian.drools.controller;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Eric Tseng
 * @description AbaDemo
 * @since 2022/8/27 14:56
 */
@SuppressWarnings("all")
public class AbaDemo {
    public static AtomicInteger a = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int expectedNum = 1;
                int newNum = expectedNum+1;
                System.out.println(Thread.currentThread().currentThread()
                        + "在CAS操作之前取到的数为：" + expectedNum);
                try {
                    // 休眠5秒，让干扰线程执行，制造ABA问题
                    TimeUnit.MILLISECONDS.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean isCasSuccess = a.compareAndSet(expectedNum, newNum);
                System.out.println(Thread.currentThread().currentThread()
                        + "CAS操作是否成功：" + isCasSuccess);
            }
        }, "主线程");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int num1 = a.incrementAndGet();
                System.out.println(Thread.currentThread().currentThread()
                        + "把数据+1：" + num1);
                int num2 = a.decrementAndGet();
                System.out.println(Thread.currentThread().currentThread()
                        + "把数据-1：" + num2);
            }
        }, "干扰线程");

        thread1.start();
        thread2.start();
    }
}
