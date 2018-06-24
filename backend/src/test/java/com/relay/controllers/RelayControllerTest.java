package com.relay.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.relay.model.Relay;
import com.relay.service.RelayService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class RelayControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RelayService relayService;

    @Before
    public void setUp() {

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper().registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    @Test
    @WithMockUser()
    public void testCreateRelay() throws Exception {

        String createdRelayJson = "{\n" + "    \"id\": 1,\n" + "    \"created\": null,\n"
                + "    \"updated\": null,\n" + "    \"dateOfManufacture\": [\n" + "        2016,\n"
                + "        6,\n" + "        10\n" + "    ],\n" + "    \"verificationDate\": [\n"
                + "        2018,\n" + "        6,\n" + "        25\n" + "    ],\n"
                + "    \"serialNumber\": \"012345\"\n" + "}";
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/relay").content(createdRelayJson)
                        .contentType(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isOk()).andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        assertNotNull(contentAsString);
    }

    @Test
    @WithMockUser
    public void testRetrieveAllRelays() throws Exception {

        generateRelay(15);

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

    @Test
    public void testDeleteRelay() throws Exception {

        generateRelay(1);
        Relay relay = relayService.findAll().getContent().get(0);

        this.mockMvc.perform(delete("/relay/" + relay.getId()).with(csrf()))
                .andExpect(status().isOk()).andReturn().getResponse();

        List<Relay> relayList = relayService.findAll().getContent();
        assertTrue(CollectionUtils.isEmpty(relayList));
    }

    @Test
    @WithMockUser
    public void testRelayWithCurrentDate() throws Exception {

        generateRelay(15);
        String date = "[2018,6,25]";
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/relay/verificationDate").content(date)
                        .contentType(MediaType.APPLICATION_JSON).with(csrf()))
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

    private void generateRelay(Integer amount) {

        Stream.generate(() -> {
            Relay relay = new Relay();
            relay.setDateOfManufacture(LocalDate.of(2016, 6, 10));
            relay.setVerificationDate(LocalDate.of(2018, 6, 25));
            relay.setSerialNumber(String.valueOf(new Random().nextInt(20) + 10000));
            return relay;
        }).limit(amount).forEach(relay -> relayService.save(relay));
    }

}
