package com.studentmanager.controllerv1;


import com.studentmanager.restcontroller.ItemController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloWorld_basic() throws Exception {
        //call GET "/api/test/hello-world", application_json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/test/hello-world")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"))
                .andReturn();

        // verify "Hello world"
        // assertEquals("Hello World", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void dummyItem_basic() throws Exception {
        //call GET "/api/test/hello-world", application_json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"Ball\",\n" +
                        "    \"price\": 10.0,\n" +
                        "    \"quantity0\": 100\n" +
                        "}"))
                .andReturn();

        // verify "Hello world"
        // assertEquals("Hello World", mvcResult.getResponse().getContentAsString());
    }
}
