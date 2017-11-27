package com.relay.controllers;

import com.relay.model.places.Station;
import com.relay.repository.StationRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StationControllerTest extends AbstractControllerTest {


    @Autowired
    private StationRepository stationRepository;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        stationRepository.deleteAllInBatch();
        stationRepository.save(new Station("testName1"));
        stationRepository.save(new Station("testName2"));
    }

    @Test
    public void should200Status() throws Exception {

        mockMvc.perform(get("/stations")).andExpect(status().isOk());
    }

    @Test
    public void testContent() throws Exception {

        mockMvc.perform(get("/stations")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }
}