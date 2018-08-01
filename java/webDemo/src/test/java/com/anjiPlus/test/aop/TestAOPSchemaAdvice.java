package com.anjiPlus.test.aop;

import com.anjiPlus.aopAdvice.Fit;
import com.anjiPlus.aopAdvice.biz.AspectBiz;
import com.anjiPlus.beanannotation.jsr.JsrService;
import com.anjiPlus.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestAOPSchemaAdvice extends UnitTestBase {
    public TestAOPSchemaAdvice(){
        super("classpath:spring-aop-advice.xml");
    }

    @Test
    public void testBiz() {
        AspectBiz aspectBiz = super.getBean("aspectBiz");
        aspectBiz.biz();
    }

    @Test
    public void testInit() {
        AspectBiz aspectBiz = super.getBean("aspectBiz");
        aspectBiz.init("MoocService", 3);
    }

    @Test
    public void testFit() {
        Fit fit = (Fit) super.getBean("aspectBiz");
        fit.filter();
    }




}
