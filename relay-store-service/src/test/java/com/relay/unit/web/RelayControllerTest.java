package com.relay.unit.web;

import com.relay.core.service.RelayService;
import com.relay.web.controllers.RelayController;
import com.relay.web.model.Relay;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RelayController.class)
class RelayControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RelayService relayService;

    @Test
    void successResponse() throws Exception {


        given(this.relayService.findAll(PageRequest.of(0, 100))).willReturn(
//                new SliceImpl<>(
                        Collections.singletonList(
                                new Relay(
                                        OffsetDateTime.of(2023, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(1)),
                                        OffsetDateTime.of(2023, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(1)),
                                        "test"
                                )
                        )
//                )
//                Collections.singletonList(
//                        new Relay(
//                                OffsetDateTime.of(2023, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(1)),
//                                OffsetDateTime.of(2023, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(1)),
//                                "test"
//                        )
//                )
        );

        this.mvc.perform(get("/relays"))
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }
}
