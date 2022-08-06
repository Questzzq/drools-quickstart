package com.vivian.drools.service;

import com.vivian.drools.cache.InitDataCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Eric Tseng
 * @description TestService
 * @since 2022/8/6 14:05
 */
@Service
public class TestService {
    @Autowired
    private InitDataCache initDataCache;

    public Integer test01(String key) {
        return initDataCache.getOrSet(key);
    }
}
