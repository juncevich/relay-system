package com.relay.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.relay.exeptions.RelayNotFoundException;
import com.relay.model.Relay;
import com.relay.model.places.Station;
import com.relay.repository.StationRepository;

@RestController
public class StationController {

    private final StationRepository stationRepository;

    @Autowired
    public StationController(StationRepository stationRepository) {

        this.stationRepository = stationRepository;
    }

    @GetMapping("/stations/{id}/relays")
    public List<Relay> retrieveAllRelaysOnStation(@PathVariable Long id) {

        Optional<Station> stationOptional = stationRepository.findById(id);

        if (!stationOptional.isPresent()) {
            throw new RelayNotFoundException("id- " + id);
        }

        return stationOptional.get().getRelay();
    }

    @GetMapping("/stations")
    public List<Station> retrieveAllStation() {
        return stationRepository.findAll();
    }

}
