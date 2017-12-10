package com.relay.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.relay.exeptions.RelayNotFoundException;
import com.relay.model.Relay;
import com.relay.service.RelayService;
import com.relay.service.StationService;

// public class RelayControllerTest {
public class RelayControllerTest extends AbstractControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RelayService relayService;

    @Mock
    private StationService stationService;

    @InjectMocks
    private RelayController relayController;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Before
    public void setUp() throws Exception {

        super.setUp();

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

    @Test
    public void testNotFoundStatusByFindNotExistRelay() throws Exception {

        mockMvc.perform(get("/relays/5")).andExpect(status().isNotFound());
    }

    @Test(expected = RelayNotFoundException.class)
    public void testExceptionByFindNotExistRelay() throws Exception {

        relayController.retrieveRelay(5);
    }

}
