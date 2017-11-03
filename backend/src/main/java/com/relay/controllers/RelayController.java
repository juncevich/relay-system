package com.relay.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.relay.exeptions.RelayNotFoundException;
import com.relay.model.Relay;
import com.relay.model.places.Station;
import com.relay.repository.RelayRepository;
import com.relay.repository.StationRepository;
import com.relay.service.RelayService;

@RestController
public class RelayController {

    /**
     * id exception message
     */
    private static final String ID_EXCEPTION_MESSAGE = "id - ";

    /**
     * Relay service
     */
    private final RelayService relayService;

    /**
     * Station repository
     */
    private final StationRepository stationRepository;

    /**
     * Relay repository
     */
    private RelayRepository relayRepository;

    /**
     * Init beans
     * 
     * @param relayService
     *            relay service
     * @param stationRepository
     *            station repository
     */
    @Autowired
    public RelayController(RelayService relayService, StationRepository stationRepository) {

        this.relayService = relayService;
        this.stationRepository = stationRepository;
    }

    /**
     * Retrieve relay by id
     * 
     * @param id
     *            relay id
     * @return relay
     */
    @GetMapping("/relays/{id}")
    public Resource<Relay> retrieveRelay(@PathVariable int id) {

        Relay relay = relayService.findOne(id);
        if (relay == null)
            throw new RelayNotFoundException(ID_EXCEPTION_MESSAGE + id);

        Resource<Relay> resource = new Resource<>(relay);

        ControllerLinkBuilder linkTo = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllRelays());
        resource.add(linkTo.withRel("all-relays"));
        return resource;
    }

    @GetMapping("/relays")
    public List<Relay> retrieveAllRelays() {

        return relayService.findAll();
    }

    /**
     * Create ralay
     * 
     * @param id
     *            relay id
     * @param relay
     *            relay
     * @return response status
     */
    @PostMapping("/stations/{id}/relay")
    public ResponseEntity<Object> createRelay(@PathVariable Long id,
            @Valid @RequestBody Relay relay) {

        Optional<Station> stationOptional = stationRepository.findById(id);

        if (!stationOptional.isPresent()) {
            throw new RelayNotFoundException(ID_EXCEPTION_MESSAGE + id);
        }

        Station station = stationOptional.get();

        relay.setStation(station);
        relayRepository.save(relay);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(relay.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/relays/{id}")
    public Relay deleteRelay(@PathVariable int id) {

        Relay relay = relayService.deleteById(id);
        if (relay == null)
            throw new RelayNotFoundException(ID_EXCEPTION_MESSAGE + id);
        return relay;
    }
}
