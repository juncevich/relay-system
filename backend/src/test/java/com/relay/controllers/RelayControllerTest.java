package com.relay.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.relay.model.Relay;
import com.relay.repository.RelayRepository;
import com.relay.service.RelayService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RelayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RelayService relayService;

    @MockBean
    private RelayRepository relayRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {

        objectMapper = new ObjectMapper().registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
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
                .perform(post("/relay").accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(relay))
                        .contentType(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isOk()).andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        assertNotNull(contentAsString);
    }

    @Test
    @WithMockUser
    public void testRetrieveAllRelays() throws Exception {

        List<Relay> relayList = Stream.generate(() -> {
            Relay relay = new Relay();
            relay.setId(BigInteger.ONE);
            relay.setDateOfManufacture(LocalDate.of(2016, 6, 10));
            relay.setVerificationDate(LocalDate.of(2018, 6, 25));
            relay.setSerialNumber("012345");
            return relay;
        }).limit(10).collect(Collectors.toList());

        Page<Relay> relayPage = new PageImpl<>(relayList);

        when(relayService.findAll()).thenReturn(relayPage);

        MockHttpServletResponse response = this.mockMvc.perform(get("/relays").with(csrf()))
                .andExpect(status().isOk()).andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        assertNotNull(contentAsString);

        JsonNode jsonNode = objectMapper.readTree(contentAsString);
        JsonNode content = jsonNode.get("content");

        List<Relay> receivedRelayList =
                objectMapper.readValue(content.toString(), new TypeReference<List<Relay>>() {
                });
        assertNotNull(receivedRelayList);
        assertEquals(10, receivedRelayList.size());
    }

}
