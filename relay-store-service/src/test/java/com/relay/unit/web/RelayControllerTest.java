package com.relay.unit.web;

import com.relay.core.service.RelayService;
import com.relay.web.controllers.RelayController;
import com.relay.web.mappers.RelayResponseMapper;
import com.relay.web.model.Relay;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
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
    private RelayResponseMapper relayResponseMapper;

  @Test
  void successResponse() throws Exception {
      var coreRelay = new com.relay.core.model.Relay(
              1L,
              "test_serial_number",
              null,
              OffsetDateTime.of(2023, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(3)),
              OffsetDateTime.of(2023, 7, 1, 0, 0, 0, 0, ZoneOffset.ofHours(3)),
              0,
              null,
              null
      );
      var webRelay = new Relay(
              1L,
              "test_serial_number",
              null,
              OffsetDateTime.of(2023, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(3)),
              OffsetDateTime.of(2023, 7, 1, 0, 0, 0, 0, ZoneOffset.ofHours(3)),
              0,
              null,
              null
    );

      given(relayService.findAll(PageRequest.of(0, 10)))
              .willReturn(new PageImpl<>(List.of(coreRelay), PageRequest.of(0, 10), 1));
      given(relayResponseMapper.mapToResponseList(any())).willReturn(List.of(webRelay));

    mvc.perform(get("/relays"))
            .andDo(print())
            .andExpect(content().contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.relays[0].serialNumber", is("test_serial_number")))
            .andExpect(jsonPath("$.relays[0].createdAt", is("2023-01-01T00:00:00+03:00")))
            .andExpect(jsonPath("$.relays[0].verificationDate", is("2023-07-01T00:00:00+03:00")))
            .andExpect(jsonPath("$.totalElements", is(1)))
            .andExpect(jsonPath("$.totalPages", is(1)))
            .andExpect(jsonPath("$.size", is(10)))
            .andExpect(jsonPath("$.number", is(0)));
  }

  @Test
  void emptyResponseTest() throws Exception {
      given(relayService.findAll(PageRequest.of(0, 10)))
              .willReturn(new PageImpl<>(List.of(), PageRequest.of(0, 10), 0));
      given(relayResponseMapper.mapToResponseList(any())).willReturn(List.of());

    mvc.perform(get("/relays"))
            .andDo(print())
            .andExpect(content().contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.relays", Matchers.empty()))
            .andExpect(jsonPath("$.totalElements", is(0)));
  }
}
