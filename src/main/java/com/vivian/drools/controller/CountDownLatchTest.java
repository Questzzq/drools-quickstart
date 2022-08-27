package com.vivian.drools.controller;

import java.util.concurrent.CountDownLatch;

/**
 * @author Eric Tseng
 * @description CountDownLatchTest
 * @since 2022/8/27 12:16
 */
@SuppressWarnings("all")
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        int s = 10;
        CountDownLatch countDownLatch = new CountDownLatch(s);
        for (int i = 0; i < s; i++) {
            final int j = i;
            Thread thread = new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                System.out.println(j);
                                if (j == 5) {
                                    throw new IllegalArgumentException();
                                }
                            } finally {
                                countDownLatch.countDown();
                            }
                        }
                    }
            );
            thread.start();
        }
        countDownLatch.await();
        System.out.println("done");
    }
}
