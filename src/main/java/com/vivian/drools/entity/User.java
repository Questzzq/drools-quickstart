package com.vivian.drools.entity;

import lombok.*;

/**
 * @author Eric Tseng
 * @description User
 * @since 2022/6/28 20:25
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer age;
    private String matCode;
}
