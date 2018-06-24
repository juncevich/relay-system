package com.relay.controllers;

import com.relay.model.Relay;
import com.relay.service.RelayService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController
public class RelayController {

    /**
     * Relay service
     */
    private final RelayService relayService;

    /**
     * Init beans
     *
     * @param relayService
     *            relay service
     */
    public RelayController(RelayService relayService) {

        this.relayService = relayService;
    }

    /**
     * Retrieve all relays
     * 
     * @return list with all relays
     */
    @GetMapping("/relays")
    public Page<Relay> retrieveAllRelays() {

        return relayService.findAll();
    }

    /**
     * Delete current relay
     *
     * @param id
     *            id relay to delete
     */
    @DeleteMapping("/relay/{id}")
    public void deleteRelay(@PathVariable BigInteger id) {

        relayService.deleteById(id);
    }

    /**
     * Create relay
     * 
     * @param relay
     *            relay to create
     * @return created relay
     */
    @PostMapping("/relay")
    public Relay createRelay(@Valid @RequestBody Relay relay) {

        return relayService.save(relay);
    }
}
