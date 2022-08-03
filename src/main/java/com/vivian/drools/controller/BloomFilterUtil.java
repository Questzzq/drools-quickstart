package com.vivian.drools.controller;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * @author Eric Tseng
 * @description BloomFilterUtil
 * @since 2022/8/3 20:42
 */
public class BloomFilterUtil {
    private static BloomFilterUtil instance;
    private BloomFilterUtil() {
    }

    public static class BloomFilterUtilInstance {
        private static final BloomFilterUtil INSTANCE = new BloomFilterUtil();
    }

    public static BloomFilterUtil getInstance() {
        return BloomFilterUtilInstance.INSTANCE;
    }

    /**
     * 预估数据量
     */
    private static final int INSERTIONS = 1000000;

    /**
     * 判重错误率
     */
    private static final double FPP = 0.0001;

    private BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), INSERTIONS, FPP);

    public void addElement(String value) {
        bloomFilter.put(value);
    }

    public boolean containsElement(String value) {
        return bloomFilter.mightContain(value);
    }

    public static void main(String[] args) {
        BloomFilterUtil bloomFilterUtil = BloomFilterUtil.getInstance();
        for (int i = 0; i < 100000; i++) {
            bloomFilterUtil.addElement("RUL"+i);
        }
        int count = 0;
        for (int i = 100000; i < 2000000; i++) {
            if(bloomFilterUtil.containsElement("RUL"+i)) {
                count++;
                System.out.println("RUL"+i);
            }
        }


    }
}

