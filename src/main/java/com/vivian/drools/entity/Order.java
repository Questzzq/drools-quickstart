package com.vivian.drools.entity;

import lombok.*;

/**
 * @author Eric Tseng
 * @description Order
 * @since 2022/6/26 13:47
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String name;
    private String country;
    private Double originalPrice;
    private Double realPrice;

    public static boolean isChinese(String country) {
        return "chinese".equals(country);
    }
}
