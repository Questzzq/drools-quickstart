package com.vivian.drools.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Eric Tseng
 * @description TestHashMap
 * @since 2022/8/18 20:19
 */
public class TestHashMap {
    public static void main(String[] args) {
        String key = "刘德华";
        System.out.println(key.hashCode());
        Map<String, String> map = new HashMap<>();
        map.put(key, "!");
        System.out.println(map.size());
    }
}
