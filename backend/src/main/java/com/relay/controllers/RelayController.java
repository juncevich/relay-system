package com.relay.controllers;

import com.relay.exeptions.RelayNotFoundException;
import com.relay.model.Relay;
import com.relay.service.RelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public Resource<Relay> retrieveRelay(@PathVariable int id) {
        Relay relay = relayService.findOne(id);
        if (relay == null)
            throw new RelayNotFoundException("id - " + id);

        Resource<Relay> resource = new Resource<>(relay);

        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllRelays());
        resource.add(linkTo.withRel("all-relays"));
        return resource;
    }

    @PostMapping("/relays")
    public ResponseEntity<Object> createRelay(@Valid @RequestBody Relay relay) {
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
