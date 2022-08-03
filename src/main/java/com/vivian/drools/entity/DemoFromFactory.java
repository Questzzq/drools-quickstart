package com.vivian.drools.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Eric Tseng
 * @description DemoFromFactory
 * @since 2022/7/17 16:42
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DemoFromFactory implements FactoryBean<Demo> {
    private String initStr;

    @Override
    public Demo getObject() throws Exception {
        Objects.requireNonNull(initStr);
        String[] split = initStr.split(",");
        Demo p=new Demo();
        p.setAge(Integer.parseInt(split[0]));
        p.setName(split[1]);
        //这里可能需要还要有其他复杂事情需要处理
        return p;
    }

    @Override
    public Class<?> getObjectType() {
        return Demo.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
