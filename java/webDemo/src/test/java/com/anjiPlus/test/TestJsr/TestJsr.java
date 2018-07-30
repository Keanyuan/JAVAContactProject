package com.anjiPlus.test.TestJsr;

import com.anjiPlus.beanannotation.javabased.MyDirverManager;
import com.anjiPlus.beanannotation.javabased.Store;
import com.anjiPlus.beanannotation.javabased.StringStore;
import com.anjiPlus.beanannotation.jsr.JsrService;
import com.anjiPlus.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestJsr extends UnitTestBase {
    public TestJsr(){
        super("classpath:spring-beanannotation.xml");
    }

    @Test
    public void testSave() {
        JsrService jsrService = super.getBean("jsrService");
        jsrService.save();
    }
    


}
