package com.vivian.drools.controller;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Eric Tseng
 * @description TestLock
 * @since 2022/8/28 16:02
 */
@SuppressWarnings("all")
public class TestLock {
    public static void main(String[] args) {
        Data01 data = new Data01();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.increment();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.increment();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.decrement();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.decrement();
            }
        }, "D").start();
    }
}

class Data01 {
    private int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    //等待、业务、唤醒
    public void increment() {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "==>" + num);
            //通知其他线程，我+1完毕了
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 等待、业务、唤醒
    public void decrement() {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "==>" + num);
            //通知其他线程，我-1完毕了
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
