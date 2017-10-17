package com.relay.controllers;

import com.relay.exeptions.RelayNotFoundException;
import com.relay.model.Relay;
import com.relay.model.Station;
import com.relay.repository.RelayRepository;
import com.relay.repository.StationRepository;
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
import java.util.Optional;

@RestController
public class RelayController {

    private final RelayService relayService;

    private final StationRepository stationRepository;
    private RelayRepository relayRepository;

    @Autowired
    public RelayController(RelayService relayService, StationRepository stationRepository) {
        this.relayService = relayService;
        this.stationRepository = stationRepository;
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

    @PostMapping("/stations/{id}/relay")
    public ResponseEntity<Object> createRelay(@PathVariable Long id, @Valid @RequestBody Relay relay) {
        Optional<Station> stationOptional = stationRepository.findById(id);

        if (!stationOptional.isPresent()) {
            throw new RelayNotFoundException("id - " + id);
        }

        Station station = stationOptional.get();

        relay.setStation(station);
        relayRepository.save(relay);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(relay.getId()).toUri();
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
