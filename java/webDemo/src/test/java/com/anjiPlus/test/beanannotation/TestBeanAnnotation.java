package com.anjiPlus.test.beanannotation;

import com.anjiPlus.beanannotation.BeanAnnotation;
import com.anjiPlus.resource.MoocResource;
import com.anjiPlus.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanAnnotation extends UnitTestBase {
    public TestBeanAnnotation(){
        super("classpath:spring-beanannotation.xml");
    }

    @Test
    public void testSay() {
        BeanAnnotation beanAnnotation = super.getBean("beanAnnotation");
        beanAnnotation.say("this is bean annation");
    }

    @Test
    public void testScope() {
        BeanAnnotation beanAnnotation = super.getBean("beanAnnotation");
        beanAnnotation.myHashCode();

        beanAnnotation = super.getBean("beanAnnotation");
        beanAnnotation.myHashCode();

    }
}
