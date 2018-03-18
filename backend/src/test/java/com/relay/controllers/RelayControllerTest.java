package com.relay.controllers;

import static org.mockito.Mockito.when;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.relay.model.Relay;
import com.relay.service.RelayService;

import reactor.core.publisher.Flux;

public class RelayControllerTest extends AbstractControllerTest {

    private WebTestClient webTestClient;

    @Mock
    private RelayService relayService;

    @InjectMocks
    private RelayController relayController;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Before
    public void setUp() throws Exception {

        super.setUp();

        webTestClient = WebTestClient.bindToController(relayController).build();
        List<Relay> relays = new ArrayList<>();
        relays.add(new Relay("First"));
        relays.add(new Relay("Second"));
        relays.add(new Relay("Third"));
        Flux<Relay> relayFlux = Flux.fromIterable(relays);
        when(relayService.findAll()).thenReturn(relayFlux);
    }

    @Test
    public void should200Status() {

        webTestClient.get().uri("/relays").exchange().expectStatus().isOk();
    }

    @Test
    public void testContent() {

        webTestClient.get().uri("/relays").accept(MediaType.APPLICATION_JSON_UTF8).exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testReturningRelayListSize() {

        webTestClient.get().uri("/relays").accept(MediaType.APPLICATION_JSON_UTF8).exchange()
                .expectBodyList(Relay.class).hasSize(3);
    }

    @Test
    public void testTextInRecordsArray() {

        webTestClient.get().uri("/relays").exchange().expectStatus().isOk().expectBody()
                .jsonPath("$[0].text").isEqualTo("First");
        webTestClient.get().uri("/relays").exchange().expectStatus().isOk().expectBody()
                .jsonPath("$[1].text").isEqualTo("Second");
        webTestClient.get().uri("/relays").exchange().expectStatus().isOk().expectBody()
                .jsonPath("$[2].text").isEqualTo("Third");
    }
    //
    // @Test
    // public void testNotFoundStatusByFindNotExistRelay() throws Exception {
    //
    // mockMvc.perform(get("/relays/5")).andExpect(status().isNotFound());
    // }
    //
    // @Test(expected = RelayNotFoundException.class)
    // public void testExceptionByFindNotExistRelay() {
    //
    // relayController.retrieveRelay(5);
    // }

}
