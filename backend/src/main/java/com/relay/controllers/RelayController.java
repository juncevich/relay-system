package com.relay.controllers;

import com.relay.exeptions.RelayNotFoundException;
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
        Relay relay = relayService.findOne(id);
        if (relay == null)
            throw new RelayNotFoundException("id - " + id);
        return relay;
    }

    @PostMapping("/relays")
    public ResponseEntity<Object> createRelay(@RequestBody Relay relay) {
        Relay savedRelay = relayService.save(relay);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedRelay.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/relays/{id}")
    public Relay deleteRelay(@PathVariable int id) {
        Relay relay = relayService.deleteById(id);
        if (relay == null)
            throw new RelayNotFoundException("id - " + id);
        return relay;
    }
}
