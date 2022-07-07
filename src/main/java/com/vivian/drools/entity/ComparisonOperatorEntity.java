package com.vivian.drools.entity;

import lombok.*;

import java.util.List;

/**
 * 用户测试比较操作符
 * @author Eric Tseng
 * @description ComparisonOperationEntity
 * @since 2022/6/26 17:41
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComparisonOperatorEntity {
    private String names;
    private List<String> list;
}
