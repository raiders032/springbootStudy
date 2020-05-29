package org.example.springbootstudy.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello="hello";
        mockMvc.perform(get("/hello"))
              .andExpect(status().isOk())
              .andExpect(content().string(hello));
        /* //given
        String hello = "hello";

        //when
        ResultActions perform = mvc.perform(get("/hello"));

        //then
        perform
                .andExpect(status().isOk())
                .andExpect(content().string(hello));*/
    }

    @Test
    public void helloResponseDto가_리턴된다() throws Exception{
        String name = "nys";
        int amount = 3000;
        mockMvc.perform(get("/hello/dto")
                    .param("name",name)
                    .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}