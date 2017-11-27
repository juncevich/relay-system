package com.relay.controllers;

import com.relay.model.Relay;
import com.relay.service.RelayService;
import com.relay.service.StationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RelayControllerTest {
//public class RelayControllerTest extends AbstractControllerTest {

    MockMvc mockMvc;
    RelayController relayController;

    @Mock
    private RelayService relayService;

    @Mock
    private StationService stationService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        relayController = new RelayController(relayService, stationService);
//        relayRepository.deleteAllInBatch();
//        relayRepository.save(new Relay("some text"));
    }

    @Test
    public void should200Status() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(relayController).build();
        List<Relay> relays = new ArrayList<>();
        relays.add(new Relay("Test relay"));

        when(relayService.findAll()).thenReturn(relays);
        mockMvc.perform(get("/relays")).andExpect(status().isOk());
    }

//    @Test
//    public void testContent() throws Exception {
//
//        mockMvc.perform(get("/relays")).andExpect(status().isOk())
//                .andExpect(content().contentType(contentType));
//    }
//
//    @Test
//    public void testAmount() throws Exception {
//
//        mockMvc.perform(get("/relays")).andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$", hasSize(4)));
//    }
//
//    @Test
//    public void testFirstRecord() throws Exception {
//
//        mockMvc.perform(get("/relays")).andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$[0].text", is("first")));
//    }




}
