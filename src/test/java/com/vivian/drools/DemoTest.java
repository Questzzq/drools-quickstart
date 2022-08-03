package com.vivian.drools;

import com.vivian.drools.entity.DemoFromFactory;
import org.apache.catalina.core.ApplicationContext;
import org.apache.naming.factory.BeanFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Eric Tseng
 * @description MyTest
 * @since 2022/7/6 21:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class DemoTest {
    @Autowired
    private DemoFromFactory demoFromFactory;

    @Test
    public void t1() {
        System.out.println(demoFromFactory.getInitStr());
    }
}
