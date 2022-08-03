package com.vivian.drools.controller;

import com.google.common.hash.BloomFilter;

/**
 * @author Eric Tseng
 * @description BloomFilterTest
 * @since 2022/8/3 20:35
 */
public class BloomFilterTest {
    /**
     * 预估数据量
     */
    private static final int EXPECTED_NUM = 100000;
    /**
     * 冲突率
     */
    private static final double CONFLICT_RATE = 0.00001;

    private static BloomFilter instance;

    public static void main(String[] args) {

    }
}
