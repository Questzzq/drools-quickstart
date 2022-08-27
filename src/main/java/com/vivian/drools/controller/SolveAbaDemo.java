package com.vivian.drools.controller;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Eric Tseng
 * @description AbaDemo
 * @since 2022/8/27 14:56
 */
@SuppressWarnings("all")
public class SolveAbaDemo {
    public static AtomicStampedReference<Integer> a =
            new AtomicStampedReference(new Integer(1000), 1);

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer expectedReference = a.getReference();
                Integer newReference = expectedReference + 1;
                Integer expectedStamp = a.getStamp();
                Integer newStamp = expectedStamp + 1;
                System.out.println(Thread.currentThread().currentThread()
                        + "在CAS操作之前取到的数为：" + expectedReference);
                try {
                    // 休眠5秒，让干扰线程执行，制造ABA问题
                    TimeUnit.MILLISECONDS.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean isCasSuccess = a.compareAndSet(expectedReference, newReference, expectedStamp, newStamp);
                System.out.println(Thread.currentThread().currentThread()
                        + "CAS操作是否成功：" + isCasSuccess);
            }
        }, "主线程");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                a.compareAndSet(a.getReference(), a.getReference()+1,
                        a.getStamp(), a.getStamp()+1);
                System.out.println(Thread.currentThread().currentThread()
                        + "把数据+1: " + a.getReference());
                a.compareAndSet(a.getReference(), a.getReference()-1,
                        a.getStamp(), a.getStamp()+1);
                System.out.println(Thread.currentThread().currentThread()
                        + "把数据-1: " + a.getReference());
            }
        }, "干扰线程");

        thread1.start();
        thread2.start();
    }
}
