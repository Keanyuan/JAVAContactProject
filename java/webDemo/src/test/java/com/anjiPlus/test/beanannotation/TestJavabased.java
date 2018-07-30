package com.anjiPlus.test.beanannotation;

import com.anjiPlus.beanannotation.javabased.MyDirverManager;
import com.anjiPlus.beanannotation.javabased.Store;
import com.anjiPlus.beanannotation.javabased.StringStore;
import com.anjiPlus.beanannotation.multibean.BeanInvoker;
import com.anjiPlus.beanannotation.service.InjectionService;
import com.anjiPlus.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestJavabased extends UnitTestBase {
    public TestJavabased(){
        super("classpath:spring-beanannotation.xml");
    }

    @Test
    public void testStore() {
        Store store = super.getBean("stringStore");
        System.out.println(store.getClass().getName());
    }
    
    @Test
    public void testMyDriverManager() {
        MyDirverManager myDirverManager = super.getBean("myDirverManager");
        System.out.println(myDirverManager.getClass().getName());
    }

    @Test
    public void testScope() {
        Store store = super.getBean("stringStore");
        System.out.println(store.hashCode());

        store = super.getBean("stringStore");
        System.out.println(store.hashCode());
    }

    @Test
    public void testG(){
        StringStore store = super.getBean("stringStoreTest");

    }

}
