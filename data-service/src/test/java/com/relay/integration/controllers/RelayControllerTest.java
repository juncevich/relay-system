package com.relay.integration.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.relay.db.repository.RelayRepository;
import com.relay.service.RelayService;
import com.relay.web.model.Relay;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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

    @Autowired
    private RelayRepository relayRepository;

    @Before
    public void setUp() {

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper().registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    @After
    public void tearDown() {

        relayRepository.deleteAll();
    }

    @Test
    ////@WithMockUser()
    public void testCreateRelay() throws Exception {

        String createdRelayJson =
                "{\"created\": null,\"updated\": null,\"dateOfManufacture\": [2016,6, 10 ], "
                        + "\"verificationDate\": [2018, 6, 25],\"serialNumber\": \"012345\"}";

//        MockHttpServletResponse response = this.mockMvc
//                .perform(post("/relay").content(createdRelayJson)
//                        .contentType(MediaType.APPLICATION_JSON).with(csrf()))
//                .andExpect(status().isCreated()).andReturn().getResponse();
//        String contentAsString = response.getContentAsString();
//
//        assertNotNull(contentAsString);
    }

    @Test
    //@WithMockUser
    public void testRetrieveAllRelays() throws Exception {

//        MockHttpServletResponse response = this.mockMvc.perform(get("/relays").with(csrf()))
//                .andExpect(status().isOk()).andReturn().getResponse();
//        String contentAsString = response.getContentAsString();
//        assertNotNull(contentAsString);
//
//        JsonNode jsonNode = objectMapper.readTree(contentAsString);
//        JsonNode content = jsonNode.get("content");
//
//        List<Relay> receivedRelayList =
//                objectMapper.readValue(content.toString(), new TypeReference<List<Relay>>() {
//                });
//        assertNotNull(receivedRelayList);
//        assertEquals(0, receivedRelayList.size());
    }

    @Test
    public void testDeleteRelay() throws Exception {

//        relayRepository.save(new Relay());
//        Relay relay = relayService.findAll().getContent().get(0);
//
//        this.mockMvc.perform(delete("/relay/" + relay.getId()).with(csrf()))
//                .andExpect(status().isNoContent()).andReturn().getResponse();
//
//        List<Relay> relayList = relayService.findAll().getContent();
//        assertTrue(CollectionUtils.isEmpty(relayList));
    }

    @Test
    //@WithMockUser
    public void testFindRelayToEqualsVerificationDate() throws Exception {

//        String date = "[2018,6,25]";
//        MockHttpServletResponse response = this.mockMvc
//                .perform(post("/relay/verificationDate").content(date)
//                        .contentType(MediaType.APPLICATION_JSON).with(csrf()))
//                .andExpect(status().isOk()).andReturn().getResponse();
//        String contentAsString = response.getContentAsString();
//        assertNotNull(contentAsString);
//
//        JsonNode jsonNode = objectMapper.readTree(contentAsString);
//        JsonNode content = jsonNode.get("content");
//
//        List<Relay> receivedRelayList =
//                objectMapper.readValue(content.toString(), new TypeReference<List<Relay>>() {
//                });
//        assertNotNull(receivedRelayList);
//        assertEquals(0, receivedRelayList.size());
//
//        receivedRelayList.forEach(
//                relay -> assertEquals("2018-06-25", relay.getVerificationDate().toString()));
    }

    @Test
    //@WithMockUser
    public void testFindRelayById() throws Exception {

//        Relay relay = new Relay();
//        relay.setSerialNumber("serial123");
//        relayRepository.save(relay);
//        Relay createdRelay = relayService.findAll().getContent().get(0);
//        MockHttpServletResponse response =
//                this.mockMvc.perform(get("/relay/" + createdRelay.getId()).with(csrf()))
//                        .andExpect(status().isOk()).andReturn().getResponse();
//
//        String contentAsString = response.getContentAsString();
//        assertNotNull(contentAsString);
//
//        Relay receivedRelay = objectMapper.readValue(contentAsString, Relay.class);
//
//        assertNotNull(receivedRelay);
//        assertEquals(createdRelay.getId(), receivedRelay.getId());

    }

    @Test
    //@WithMockUser
    public void testFindRelayToEqualsDateOfManufacture() throws Exception {

//        String date = "2016-06-10";
//        MockHttpServletResponse response = this.mockMvc
//                .perform(get("/relay/dateOfManufacture").param("date", date).with(csrf()))
//                .andExpect(status().isOk()).andReturn().getResponse();
//        String contentAsString = response.getContentAsString();
//        assertNotNull(contentAsString);
//
//        JsonNode jsonNode = objectMapper.readTree(contentAsString);
//        JsonNode content = jsonNode.get("content");
//
//        List<Relay> receivedRelayList =
//                objectMapper.readValue(content.toString(), new TypeReference<List<Relay>>() {
//                });
//        assertNotNull(receivedRelayList);
//        assertEquals(0, receivedRelayList.size());
//
//        receivedRelayList.forEach(
//                relay -> assertEquals("2016-06-10", relay.getDateOfManufacture().toString()));
    }

    @Test
    //@WithMockUser
    public void testFindRelayToBeforeDateOfManufactureEqualsDate() throws Exception {

//        String date = "2016-06-10";
//        MockHttpServletResponse response = this.mockMvc
//                .perform(get("/relay/dateOfManufacture").param("before", date).with(csrf()))
//                .andExpect(status().isOk()).andReturn().getResponse();
//        String contentAsString = response.getContentAsString();
//        assertNotNull(contentAsString);
//
//        JsonNode jsonNode = objectMapper.readTree(contentAsString);
//        JsonNode content = jsonNode.get("content");
//
//        List<Relay> receivedRelayList =
//                objectMapper.readValue(content.toString(), new TypeReference<List<Relay>>() {
//                });
//        assertNotNull(receivedRelayList);
//        assertEquals(0, receivedRelayList.size());
//
//        receivedRelayList.forEach(
//                relay -> assertEquals("2016-06-10", relay.getDateOfManufacture().toString()));
    }

    @Test
    //@WithMockUser
    public void testFindRelayToBeforeDateOfManufactureWithOlderDate() throws Exception {

//        String date = "2016-06-11";
//        MockHttpServletResponse response = this.mockMvc
//                .perform(get("/relay/dateOfManufacture").param("before", date).with(csrf()))
//                .andExpect(status().isOk()).andReturn().getResponse();
//        String contentAsString = response.getContentAsString();
//        assertNotNull(contentAsString);
//
//        JsonNode jsonNode = objectMapper.readTree(contentAsString);
//        JsonNode content = jsonNode.get("content");
//
//        List<Relay> receivedRelayList =
//                objectMapper.readValue(content.toString(), new TypeReference<List<Relay>>() {
//                });
//        assertNotNull(receivedRelayList);
//        assertEquals(0, receivedRelayList.size());
//
//        receivedRelayList.forEach(
//                relay -> assertEquals("2016-06-10", relay.getDateOfManufacture().toString()));
    }

    @Test
    //@WithMockUser
    public void testFindRelayToAfterDateOfManufactureEqualsDate() throws Exception {

//        String date = "2016-06-10";
//        MockHttpServletResponse response = this.mockMvc
//                .perform(get("/relay/dateOfManufacture").param("after", date).with(csrf()))
//                .andExpect(status().isOk()).andReturn().getResponse();
//        String contentAsString = response.getContentAsString();
//        assertNotNull(contentAsString);
//
//        JsonNode jsonNode = objectMapper.readTree(contentAsString);
//        JsonNode content = jsonNode.get("content");
//
//        List<Relay> receivedRelayList =
//                objectMapper.readValue(content.toString(), new TypeReference<List<Relay>>() {
//                });
//        assertNotNull(receivedRelayList);
//        assertEquals(0, receivedRelayList.size());
//
//        receivedRelayList.forEach(
//                relay -> assertEquals("2016-06-10", relay.getDateOfManufacture().toString()));
    }

    @Test
    //@WithMockUser
    public void testFindRelayToafterDateOfManufactureWithOlderDate() throws Exception {

//        String date = "2016-06-09";
//        MockHttpServletResponse response = this.mockMvc
//                .perform(get("/relay/dateOfManufacture").param("after", date).with(csrf()))
//                .andExpect(status().isOk()).andReturn().getResponse();
//        String contentAsString = response.getContentAsString();
//        assertNotNull(contentAsString);
//
//        JsonNode jsonNode = objectMapper.readTree(contentAsString);
//        JsonNode content = jsonNode.get("content");
//
//        List<Relay> receivedRelayList =
//                objectMapper.readValue(content.toString(), new TypeReference<List<Relay>>() {
//                });
//        assertNotNull(receivedRelayList);
//        assertEquals(0, receivedRelayList.size());
//
//        receivedRelayList.forEach(
//                relay -> assertEquals("2016-06-10", relay.getDateOfManufacture().toString()));
    }

    @Test
    //@WithMockUser
    public void testFindBySerialNumber() throws Exception {

//        Relay relay = new Relay();
//        relay.setSerialNumber("serial123");
//        relayService.save(relay);
//        Relay createdRelay = relayService.findAll().getContent().get(0);
//        MockHttpServletResponse response = this.mockMvc
//                .perform(get("/relay/serialNumber")
//                        .param("serialNumber", createdRelay.getSerialNumber()).with(csrf()))
//                .andExpect(status().isOk()).andReturn().getResponse();
//
//        String contentAsString = response.getContentAsString();
//        assertNotNull(contentAsString);
//
//        Relay receivedRelay = objectMapper.readValue(contentAsString, Relay.class);
//        assertEquals(createdRelay.getId(), receivedRelay.getId());

    }

}
