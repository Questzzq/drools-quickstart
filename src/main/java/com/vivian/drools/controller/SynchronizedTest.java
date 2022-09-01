package com.vivian.drools.controller;

import java.util.concurrent.TimeUnit;

/**
 * 当线程请求一个由其他线程持有的对象锁时，该线程会阻塞，而当线程请求由自己持有的对象锁时，
 * 如果该锁是重入锁，请求就会成功，否则阻塞。
 * 或者说，可重入锁是同一个线程重复请求自己持有的锁对象时，可以请求成功而不会发生死锁。
 * sunchronized是可重入锁。
 * 可重入锁实现机制：每一把锁关联一个线程持有者和计数器：synchronized通过monitor计数器实现，
 * 当计数器为0时表示该锁没有被任何线程持有，那么任何线程都可能调用该锁而调用相应的方法，
 * 当某一线程请求成功后，JVM会记下锁的持有线程，并且将计数器置为1；此时其他线程请求该锁，
 * 则必须等待，而持有该锁的线程如果再次请求这把锁，就可以再次拿到这把锁，同时计数器会递增，
 * 当线程退出同步代码块时，计数器会递减，如果计数器为0，则释放该锁。
 * @author Eric Tseng
 * @description SynchronizedTest
 * @since 2022/9/1 23:45
 */
@SuppressWarnings("all")
public class SynchronizedTest {
    private static int count;

    public static synchronized void fun1() {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        System.out.println(Thread.currentThread().getName() + ": count = " + count);
        fun2();
    }

    public static synchronized void fun2() {
        count++;
        System.out.println(Thread.currentThread().getName() + ": count = " + count);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(()->{fun1();}, "A"+(i+1));
            t1.start();
        }
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(()->{fun1();}, "B"+(i+1));
            t1.start();
        }
    }
}
