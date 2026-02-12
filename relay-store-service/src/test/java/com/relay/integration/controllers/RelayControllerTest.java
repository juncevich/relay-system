package com.relay.integration.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.relay.db.dao.LocationDao;
import com.relay.db.dao.RelayDao;
import com.relay.db.dao.StandDao;
import com.relay.db.entity.items.Relay;
import com.relay.db.entity.items.RelayType;
import com.relay.db.entity.location.Station;
import com.relay.db.entity.storage.Stand;
import com.relay.db.entity.storage.Storage;
import com.relay.web.dto.CreateRelayRequest;
import com.relay.web.dto.CreateRelayResponse;
import com.relay.web.dto.GetAllRelaysResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.time.OffsetDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@SpringBootTest
@AutoConfigureTestDatabase
class RelayControllerTest {

    private MockMvc mockMvc;

    private JsonMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RelayDao relayRepository;

    @Autowired
    private LocationDao locationRepository;

    @Autowired
    private StandDao standRepository;

    private Storage defaultStorage;

    @BeforeEach
    public void setUp() {
        relayRepository.deleteAll();
        standRepository.deleteAll();
        locationRepository.deleteAll();

        Station station = new Station();
        station.setName("Test Station");
        locationRepository.save(station);

        Stand stand = new Stand();
        stand.setName("Test Stand");
        stand.setLocation(station);
        standRepository.save(stand);
        defaultStorage = stand;

        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    }

    @AfterEach
    public void tearDown() {
        relayRepository.deleteAll();
        standRepository.deleteAll();
        locationRepository.deleteAll();
    }

    @Test
    void testCreateRelay() throws Exception {

        var createRelayRequest = new CreateRelayRequest(
                "012345",
                OffsetDateTime.now(),
                defaultStorage.getId()
        );
        MockHttpServletResponse response = this.mockMvc
                .perform(post("/relays").content(objectMapper.writeValueAsString(createRelayRequest))
                        .contentType(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn()
                .getResponse();

        var deserializedResponse = objectMapper.readValue(response.getContentAsString(), CreateRelayResponse.class);
        Assertions.assertEquals("012345", deserializedResponse.serialNumber());
        Assertions.assertNotNull(deserializedResponse.dateOfManufacture());
    }

    @Test
    void testRetrieveAllRelays() throws Exception {
        relayRepository.save(Relay.builder()
                .serialNumber("test_serial_number")
                .relayType(RelayType.NMSH_400)
                .storage(defaultStorage)
                .createdAt(OffsetDateTime.MIN)
                .updatedAt(OffsetDateTime.MAX)
                .lastCheckDate(OffsetDateTime.MAX)
                .build()
        );

      ResultActions response = this.mockMvc.perform(
              get("/relays")
                  .with(csrf())
          )
          .andExpect(status().isOk())
          .andDo(print())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.relays.length()").value(1))
              .andExpect(jsonPath("$.relays[0].serialNumber").value("test_serial_number"))
              .andExpect(jsonPath("$.relays[0].createdAt").value("-999999999-01-01T00:00:00+18:00"));
      String contentAsString = response.andReturn().getResponse().getContentAsString();
        Assertions.assertNotNull(contentAsString);

        GetAllRelaysResponse receivedResponse =
                objectMapper.readValue(contentAsString, GetAllRelaysResponse.class);
        Assertions.assertNotNull(receivedResponse);
        Assertions.assertNotNull(receivedResponse.relays());
        Assertions.assertEquals(1, receivedResponse.relays().size());
    }

}


