package com.relay.controllers;

import com.relay.model.Relay;
import com.relay.service.RelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RelayController {

    private final RelayService relayService;

    @Autowired
    public RelayController(RelayService relayService) {
        this.relayService = relayService;
    }

    @GetMapping("/relays")
    public List<Relay> retrieveAllRelays() {
        return relayService.findAll();
    }
}
