package com.anjiPlus.test.aop.aspectj;

import com.anjiPlus.aopAspectj.biz.MookBiz;
import com.anjiPlus.api.BizLogic;
import com.anjiPlus.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestAspectj extends UnitTestBase {
    public TestAspectj(){
        super("classpath:spring-aop-aspectj.xml");
    }

    @Test
    public void testSave() {
        MookBiz biz = super.getBean("mookBiz");
        biz.save("this is test ");

    }





}
