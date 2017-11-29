package com.relay.controllers;

import com.relay.model.Relay;
import com.relay.service.RelayService;
import com.relay.service.StationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class RelayControllerTest {
//public class RelayControllerTest extends AbstractControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RelayService relayService;

    @Mock
    private StationService stationService;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        RelayController relayController = new RelayController(relayService, stationService);

        mockMvc = MockMvcBuilders.standaloneSetup(relayController).build();
        List<Relay> relays = new ArrayList<>();
        relays.add(new Relay("First"));

        when(relayService.findAll()).thenReturn(relays);
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
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testFirstRecord() throws Exception {

        mockMvc.perform(get("/relays")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].text", is("First")));
    }




}
