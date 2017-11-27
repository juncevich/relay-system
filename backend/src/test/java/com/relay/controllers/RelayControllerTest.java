package com.relay.controllers;

import com.relay.model.Relay;
import com.relay.repository.RelayRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class RelayControllerTest extends AbstractControllerTest {

    @Autowired
    private RelayRepository relayRepository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        relayRepository.deleteAllInBatch();
        relayRepository.save(new Relay("some text"));
    }

    @Test
    public void should200Status() throws Exception {

        mockMvc.perform(get("/relays")).andExpect(status().isOk());
    }

    @Test
    public void testContent() throws Exception {

        mockMvc.perform(get("/relays")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void testAmount() throws Exception {

        mockMvc.perform(get("/relays")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testFirstRecord() throws Exception {

        mockMvc.perform(get("/relays")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].text", is("first")));
    }




}
