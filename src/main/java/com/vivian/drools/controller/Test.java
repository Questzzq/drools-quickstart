package com.vivian.drools.controller;

import com.vivian.drools.common.InvalidRuleException;
import com.vivian.drools.service.RuleKieService;
import com.vivian.drools.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eric Tseng
 * @description Test
 * @since 2022/6/28 20:28
 */
@RestController
@RequestMapping("/1")
public class Test {
    @Autowired
    private RuleKieService ruleKieService;
    @Autowired
    private TestService testService;

    @RequestMapping("/0")
    public Object test00(String slotCode, String expression) throws InvalidRuleException {
        return ruleKieService.test01(slotCode, expression);
    }

    @RequestMapping("/1")
    public Object test01(String key) {
        return testService.test01(key);
    }
}
