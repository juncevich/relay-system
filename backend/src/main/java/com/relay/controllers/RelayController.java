package com.relay.controllers;

import com.relay.model.Relay;
import com.relay.service.RelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/relays/{id}")
    public Relay retrieveRelay(@PathVariable int id) {
        return relayService.findOne(id);
    }

    @PostMapping("/relays")
    public ResponseEntity<Object> createRelay(@RequestBody Relay relay) {
        Relay savedRelay = relayService.save(relay);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedRelay.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
