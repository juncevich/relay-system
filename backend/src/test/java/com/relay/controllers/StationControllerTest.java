package com.relay.controllers;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.relay.service.StationService;

public class StationControllerTest extends AbstractControllerTest {

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Mock
    private StationService stationService;

    @InjectMocks
    private StationController stationController;

    private WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {

        super.setUp();
    }

    @Test
    public void should200Status() throws Exception {

        super.setUp();

        webTestClient = WebTestClient.bindToController(stationController).build();
        webTestClient.get().uri("/stations").exchange().expectStatus().isOk();
    }

    @Test
    public void testContent() {

        webTestClient = WebTestClient.bindToController(stationController).build();
        webTestClient.get().uri("/stations").accept(MediaType.APPLICATION_JSON_UTF8).exchange()
                .expectBody().json("[]");
    }
}
