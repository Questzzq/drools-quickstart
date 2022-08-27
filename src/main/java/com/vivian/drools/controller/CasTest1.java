package com.vivian.drools.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric Tseng
 * @description CasTest
 * @since 2022/8/27 10:30
 */
@SuppressWarnings("all")
public class CasTest1 {
    volatile static int count = 0;
    // 模拟访问的方法

    /**
     * Q：synchronized修饰方法，导致程序耗时时间长的原因：
     * A：request方法使用synchronzed关键字修饰，保证了并发情况下，
     * request方法同一时刻还允许一个线程进入，request加锁相当于串行运行了。
     * 优化点：只加锁必要的代码。
     * count++操作，有三步操作
     * 1. 获取count的值A     (第一步)
     * 2. 将A值+1得到B       (第二步)
     * 3. 将B值赋值给count   (第三步)
     * 升级第三步的实现：
     *      ①：获取锁
     *      ②：获取 count 的最新值，记作 LV
     *      ③：判断 LV 是否等于 A，如果相等，则将 B 的值赋值给 count，并返回 TRUE，否则返回 FALSE。
     *      ④：释放锁。
     *      ⑤：如果是返回 FALSE。则从头执行第一步。
     *
     * @throws InterruptedException
     */
    public static void request() throws InterruptedException {
        // 模拟耗时5ms
        TimeUnit.MILLISECONDS.sleep(5);
        int expectCount;
        while(!compareAndSwap(expectCount = getCount(), expectCount + 1)){

        }
    }

    private static int getCount() {
        return count;
    }

    /**
     * @param expectCount 期望值
     * @param newCount 需要给 count 赋值的新值
     * @return 成功返回TRUE，失败返回FALSE
     */
    public static synchronized boolean compareAndSwap(int expectCount, int newCount) {
        // 判断count当前值是否和期望值expectCount一致，
        // 如果一致，则将newCount赋值给count。
        if(getCount() == expectCount) {
            count = newCount;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for(int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 每个用户访问10次网站
                    try{
                        for (int j = 0; j < 10; j++) {
                            request();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
            thread.start();
        }
        // 保证100个线程执行完之后，再执行后面的代码
        countDownLatch.await();
        System.out.println("count = " + count);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时: " +(endTime - startTime));
    }
}
