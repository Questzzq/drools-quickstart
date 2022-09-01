package com.vivian.drools.controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Eric Tseng
 * @description LockTest
 * @since 2022/8/28 12:36
 */
@SuppressWarnings("all")
public class LockTest {
    private int count = 10;
    private final Lock lock = new ReentrantLock(true);


    private void request() {
        lock.lock();
        try {
            if (count > 0) {
                count--;
                System.out.println("顾客" + Thread.currentThread().getName() + "成功购票，剩余票数为：" + count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        for (int i = 0; i < 4000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    lockTest.request();
                }
            }, "小A—" + i);
            /**
             * 调用 thread.start()方法不一定会立即创建线程
             * 原因：底层调用一个 native 方法：private native void start0();
             * 即最终是操作系统进行创建线程的。
             */
            thread.start();
        }
    }
}
