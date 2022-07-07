package com.vivian.drools;

import com.vivian.drools.entity.ComparisonOperatorEntity;
import com.vivian.drools.entity.Order;
import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric Tseng
 * @description Test
 * @since 2022/6/26 15:14
 */
public class DroolsTest {
    @Test
    public void test1() {
        KieServices kieServices = KieServices.Factory.get();
        // 获取Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();
        // Fact对象，事实对象
        Order order1 = new Order();
        order1.setOriginalPrice(98d);
        Order order2 = new Order();
        order2.setOriginalPrice(123d);
        Order order3 = new Order();
        order3.setOriginalPrice(234d);
        Order order4 = new Order();
        order4.setOriginalPrice(398d);
        // 将 order 对象插入到工作内存中
        session.insert(order1);
        session.insert(order2);
        session.insert(order3);
        session.insert(order4);
        // 激活规则，由Drools框架自动进行规则匹配。
        // 如果匹配成功，则执行当前规则
        session.fireAllRules();
        // 关闭会话
        session.dispose();
    }

    @Test
    public void test2() {
        KieServices kieServices = KieServices.Factory.get();
        // 获取Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();
        // Fact对象，事实对象
        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        entity.setNames("张三");
        session.insert(entity);
        session.fireAllRules(new RuleNameEndsWithAgendaFilter("_not"));
        session.dispose();
    }
}
