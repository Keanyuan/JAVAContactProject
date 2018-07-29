package com.anjiPlus.test.bean;

import com.anjiPlus.bean.BeanScope;
import com.anjiPlus.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanScope extends UnitTestBase {
    public TestBeanScope(){
        super("classpath:spring-beanScope.xml");
    }

    @Test
    public void testSay(){
        BeanScope beanScope = super.getBean("beanScope");
        beanScope.say();
    }
}
