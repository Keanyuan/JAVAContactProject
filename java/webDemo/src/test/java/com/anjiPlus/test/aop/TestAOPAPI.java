package com.anjiPlus.test.aop;

import com.anjiPlus.aopAdvice.schema.advisors.service.InvokeService;
import com.anjiPlus.api.BizLogic;
import com.anjiPlus.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestAOPAPI extends UnitTestBase {
    public TestAOPAPI(){
        super("classpath:spring-aop-api.xml");
    }

    @Test
    public void testSave() {
        BizLogic logic = super.getBean("bizLogicImpl");
        logic.save();

    }





}
