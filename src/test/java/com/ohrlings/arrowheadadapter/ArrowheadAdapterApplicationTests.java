package com.ohrlings.arrowheadadapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArrowheadAdapterApplicationTests {
    @Autowired
    private AdapterController controller;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void shouldReturnProvidedMessage() throws Exception {
        String testData = "testData";
        mockMvc.perform(post("/adapter").content(testData)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(testData)));
    }

    @Test
    public void shouldReturnProvidedMessageContainingSpecialCharacters() throws Exception {
        String testData = "Älskar & @%#¤";
        mockMvc.perform(post("/adapter").content(testData)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(testData)));
    }

}
