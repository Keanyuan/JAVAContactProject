package com.anjiplus.springboot.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class GirlControllerTest {

    @Autowired
    private MockMvc mvc;
    @Test
    ResultActions girlList() throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/girls")).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void girlAdd() {
    }

    @Test
    void girlfindOne() {
    }

    @Test
    void girlfindAge() {
    }

    @Test
    void girlUpdate() {
    }

    @Test
    void girlDelete() {
    }

    @Test
    void girlTwo() {
    }

    @Test
    void getAge() {
    }
}