package com.vivian.drools.cache;

import com.vivian.drools.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Eric Tseng
 * @description InitDataCache
 * @since 2022/8/6 13:25
 */
@Component
public class InitDataCache {
    private Map<String, Object> map = new ConcurrentHashMap<>();

    @Autowired
    private TestMapper testMapper;

    public Integer getOrSet(String key) {
        if (!map.containsKey(key)) {
            map.put(key, testMapper.countSize() + Integer.valueOf(key));
        }
        return (Integer) map.get(key);
    }

    @Scheduled(fixedRate = 7200000)
    public void test() {
        map.put("1", testMapper.countSize());
        System.out.println("Scheduled: " + testMapper.countSize());
    }
}
