package com.todor.api.demo;

import com.todor.api.demo.controllers.MainController;
import com.todor.api.demo.repositories.ContentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class DemoApplicationTests {

    @MockBean
    private ContentRepository repo;
    

    @Autowired
	protected MockMvc mvc;
	
    @Test
    public void test_text_error() throws Exception {
        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.post("/messages/send_text").content("");
        
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status()
        .is4xxClientError());
    } 

    @Test
    public void test_text() throws Exception {
        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.post("/messages/send_text").content("123123123");
        
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status()
        .is(201));
    } 

    @Test
    public void test_emotion() throws Exception {
        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.post("/messages/send_emotion").content("wow");
        
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status()
        .is(201));
    }

    @Test
    public void test_emotion_long() throws Exception {
        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.post("/messages/send_emotion").content("wowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status()
        .is(412));
    }
    
    @Test
    public void test_emotion_error() throws Exception {
        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.post("/messages/send_emotion").content("sdd1223ssdf");
        
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status()
        .is(412));
    }

    @Test
	public void contextLoads() {
	}

}
