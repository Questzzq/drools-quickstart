package com.vivian.drools.common;

/**
 * @author Eric Tseng
 * @description 规则无效异常
 * @since 2022/6/28 20:24
 */
public class InvalidRuleException extends Exception {
    public InvalidRuleException(String errorMsg) {
        super(errorMsg);
    }
}
