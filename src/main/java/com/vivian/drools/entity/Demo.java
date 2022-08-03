package com.vivian.drools.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author Eric Tseng
 * @description Demo
 * @since 2022/7/17 16:41
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Demo {
    private Integer age;
    private String name;
}
