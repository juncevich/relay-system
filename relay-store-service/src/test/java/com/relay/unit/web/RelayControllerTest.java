package com.relay.unit.web;

import com.relay.core.service.RelayService;
import com.relay.db.repository.RelayRepository;
import com.relay.web.controllers.RelayController;
import com.relay.web.model.Relay;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RelayController.class)
class RelayControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockitoBean
  private RelayService relayService;

  @MockitoBean
  private RelayRepository relayRepository;

  @Test
  void successResponse() throws Exception {

    given(
            relayService.findAll(PageRequest.of(0, 10))).willReturn(
            List.of(
                    new Relay(
                            OffsetDateTime.of(2023, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(3)),
                            OffsetDateTime.of(2023, 7, 1, 0, 0, 0, 0, ZoneOffset.ofHours(3)),
                            "test_serial_number"
                    )
            )
    );

    mvc.perform(get("/relays"))
            .andDo(print())
            .andExpect(content().contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].serialNumber", is("test_serial_number")))
            .andExpect(jsonPath("$[0].createdAt", is("2023-01-01T00:00:00+03:00")))
            .andExpect(jsonPath("$[0].verificationDate", is("2023-07-01T00:00:00+03:00")));
  }

  @Test
  void emptyResponseTest() throws Exception {
    relayService.findAll(Pageable.unpaged());

    mvc.perform(get("/relays"))
            .andDo(print())
            .andExpect(content().contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.empty()));
  }
}
