package com.vivian.drools;

import com.vivian.drools.entity.Order;
import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author Eric Tseng
 * @description MyTest
 * @since 2022/7/6 21:17
 */
public class MyTest {
    @Test
    public void t1() {
        Order order1 = new Order();
        order1.setOriginalPrice(1123d);
        order1.setName("诗经");
        order1.setCountry("chinese");

        Order order2 = new Order();
        order2.setOriginalPrice(1000d);
        order2.setName("chinese");
        order2.setCountry("usa");

        KieServices kieServices = KieServices.Factory.get();
        // 获取Kie容器对象
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        // 从Kie容器对象中获取会话对象
        KieSession session = kieContainer.newKieSession();
        // 将 order 对象插入到工作内存中
        session.insert(order1);
        session.insert(order2);
        session.fireAllRules(new RuleNameEndsWithAgendaFilter("chinese"));
        // 关闭会话
        session.dispose();
    }
}
