package com.relay.controllers;

import com.relay.service.StationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StationControllerTest {

    private StationController stationController;

    @Mock
    private StationService stationService;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stationController = new StationController(stationService);
    }

    @Test
    public void should200Status() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(stationController).build();
        mockMvc.perform(get("/stations")).andExpect(status().isOk());
    }

    @Test
    public void testContent() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(stationController).build();
        mockMvc.perform(get("/stations")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }
}