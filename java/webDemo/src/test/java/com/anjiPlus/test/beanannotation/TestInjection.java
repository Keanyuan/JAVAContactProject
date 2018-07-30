package com.anjiPlus.test.beanannotation;

import com.anjiPlus.beanannotation.BeanAnnotation;
import com.anjiPlus.beanannotation.multibean.BeanInvoker;
import com.anjiPlus.beanannotation.service.InjectionService;
import com.anjiPlus.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestInjection extends UnitTestBase {
    public TestInjection(){
        super("classpath:spring-beanannotation.xml");
    }

    @Test
    public void testAutowried() {
        InjectionService injectionService = super.getBean("injectionServiceImpl");
        injectionService.save("this is  Autowried");
    }

    @Test
    public void testMultiBean() {
        BeanInvoker beanInvoker = super.getBean("beanInvoker");
        beanInvoker.say();
    }

}
