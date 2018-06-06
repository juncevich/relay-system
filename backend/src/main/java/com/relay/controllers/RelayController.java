package com.relay.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.relay.model.Relay;
import com.relay.service.RelayService;
import com.relay.service.StationService;

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
    private final StationService stationService;

    /**
     * Init beans
     *
     * @param relayService
     *            relay service
     * @param stationService
     *            station service
     */
    public RelayController(RelayService relayService, StationService stationService) {

        this.relayService = relayService;
        this.stationService = stationService;
    }

    // /**
    // * Retrieve relay by id
    // *
    // * @param id
    // * relay id
    // * @return relay
    // */
    // @GetMapping("/relays/{id}")
    // public Mono<Relay> retrieveRelay(@PathVariable String id) {
    //
    // Mono<Relay> relay = relayService.findOne(id);
    // if (relay == null) {
    // throw new RelayNotFoundException(ID_EXCEPTION_MESSAGE + id);
    // }
    // return relay;
    // }

    /**
     * Retrieve all relays
     * 
     * @return list with all relays
     */
    @GetMapping("/relays")
    public Iterable<Relay> retrieveAllRelays() {

        return relayService.findAll();
    }

    // /**
    // * Create ralay
    // *
    // * @param id
    // * relay id
    // * @param relay
    // * relay
    // * @return response status
    // */
    // @PostMapping("/stations/{id}/relay")
    // public ResponseEntity<Object> createRelay(@PathVariable Long id,
    // @Valid @RequestBody Relay relay) {
    //
    // // Station station = stationService.findOne(id);
    // //
    // // relay.setStation(station);
    // // relayService.save(relay);
    // // URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    // // .buildAndExpand(relay.getId()).toUri();
    // // return ResponseEntity.created(uri).build();
    // return null;
    // }

    // /**
    // * Delete current relay
    // *
    // * @param id
    // * id relay to delete
    // * @return deleted relay
    // */
    // @DeleteMapping("/relays/{id}")
    // public Mono<Void> deleteRelay(@PathVariable String id) {
    //
    // Mono<Void> relay = relayService.deleteById(id);
    // if (relay == null) {
    // throw new RelayNotFoundException(ID_EXCEPTION_MESSAGE + id);
    // }
    // return relay;
    // }

    /**
     * Create relay
     * 
     * @param relay
     *            relay to create
     * @return created relay
     */
    @PostMapping("/relays")
    public Relay createRelay(@Valid @RequestBody Relay relay) {

        // URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        // .buildAndExpand(savedRelay.getId()).toUri();
        // return ResponseEntity.created(uri).build();
        return relayService.save(relay);
    }

    // @GetMapping("/users/{id}")
    // public Resource<User> retrieveRelay(@PathVariable int id) {
    //
    // Optional<User> user = userRepository.findById(id);
    // if (!user.isPresent())
    // throw new RelayNotFoundException("id - " + id);
    //
    // Resource<User> resource = new Resource<>(user.get());
    //
    // ControllerLinkBuilder linkTo = ControllerLinkBuilder
    // .linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllRelays());
    // resource.add(linkTo.withRel("all-users"));
    // return resource;
    // }

}
