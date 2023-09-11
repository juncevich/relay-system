package com.relay.integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.relay.core.service.RelayService;
import com.relay.db.entity.items.Relay;
import com.relay.db.entity.items.RelayType;
import com.relay.db.repository.RelayRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.time.OffsetDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase
class RelayControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RelayService relayService;

    @Autowired
    private RelayRepository relayRepository;

    @BeforeEach
    public void setUp() {

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    @AfterEach
    public void tearDown() {

        relayRepository.deleteAll();
    }

    @Test
        ////@WithMockUser()
    void testCreateRelay() throws Exception {

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
    void testRetrieveAllRelays() throws Exception {
        relayRepository.save(new Relay(
                        12345L,
                        1L,
                        "test_serial_number",
                        RelayType.NMSH_400,
                        OffsetDateTime.MIN,
                        OffsetDateTime.MAX,
                        OffsetDateTime.MAX
                )
        );

        this.mockMvc.perform(
                        get("/relays")
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].serialNumber").value("test_serial_number"))
                .andExpect(jsonPath("$[0].dateOfManufacture").value("-999999999-01-01T00:00:00+18:00"));
//        String contentAsString = response.getContentAsString();
//        Assertions.assertNotNull(contentAsString);

//        JsonNode jsonNode = objectMapper.readTree(contentAsString);
//        JsonNode content = jsonNode.get("content");
//
//        List<Relay> receivedRelayList =
//                objectMapper.readValue(content.toString(), new TypeReference<List<Relay>>() {
//                });
//        Assertions.assertNotNull(receivedRelayList);
//        Assertions.assertEquals(0, receivedRelayList.size());
    }

    @Test
    void testDeleteRelay() throws Exception {

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
    void testFindRelayToEqualsVerificationDate() throws Exception {

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
    void testFindRelayById() throws Exception {

        Relay relay = new Relay();
        relay.setSerialNumber("serial123");
        relayRepository.save(relay);
        Relay createdRelay = relayRepository.findAll().iterator().next();
        MockHttpServletResponse response =
                this.mockMvc.perform(get("/relay/" + createdRelay.getId()).with(csrf()))
                        .andExpect(status().isOk()).andReturn().getResponse();

        String contentAsString = response.getContentAsString();
        Assertions.assertNotNull(contentAsString);

        com.relay.web.model.Relay receivedRelay = objectMapper.readValue(contentAsString, com.relay.web.model.Relay.class);

        Assertions.assertNotNull(receivedRelay);
//        Assertions.assertEquals(createdRelay.getId(), receivedRelay.getId());

    }

    @Test
        //@WithMockUser
    void testFindRelayToEqualsDateOfManufacture() throws Exception {

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
    void testFindRelayToBeforeDateOfManufactureEqualsDate() throws Exception {

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
    void testFindRelayToBeforeDateOfManufactureWithOlderDate() throws Exception {

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
    void testFindRelayToAfterDateOfManufactureEqualsDate() throws Exception {

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
    void testFindRelayToafterDateOfManufactureWithOlderDate() throws Exception {

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
    void testFindBySerialNumber() throws Exception {

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


