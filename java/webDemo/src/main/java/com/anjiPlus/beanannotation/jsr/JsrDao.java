package com.anjiPlus.beanannotation.jsr;

import org.springframework.stereotype.Repository;

@Repository
public class JsrDao {
    
    public void save() {
        System.out.println("JsrDao invoked");
    }
}
