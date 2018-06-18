package com.relay.controllers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relay.model.Relay;
import com.relay.service.RelayService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RelayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RelayService relayService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {

        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser()
    public void testCreateRelay() throws Exception {

        Relay relay = new Relay();
        relay.setId(BigInteger.ONE);
        relay.setDateOfManufacture(LocalDate.of(2016, 6, 10));
        relay.setVerificationDate(LocalDate.of(2018, 6, 25));
        relay.setSerialNumber("012345");

        when(relayService.save(any(Relay.class))).thenReturn(relay);

        MockHttpServletResponse response = this.mockMvc
                .perform(post("/relay").content(objectMapper.writeValueAsString(relay))
                        .with(csrf()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        assertNotNull(contentAsString);
    }

    @Test
    @WithMockUser
    public void testRetrieveAllRelays() throws Exception {

        Relay relay = new Relay();
        relay.setId(BigInteger.ONE);
        relay.setDateOfManufacture(LocalDate.of(2016, 6, 10));
        relay.setVerificationDate(LocalDate.of(2018, 6, 25));
        relay.setSerialNumber("012345");

        when(relayService.save(any(Relay.class))).thenReturn(relay);

        MockHttpServletResponse response = this.mockMvc.perform(get("/relays").with(csrf()))

                .andExpect(status().isOk()).andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        assertNotNull(contentAsString);
    }

}
