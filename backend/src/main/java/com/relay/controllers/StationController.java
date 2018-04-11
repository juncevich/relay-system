package com.relay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.relay.model.Relay;
import com.relay.model.places.Station;
import com.relay.service.StationService;

@RestController
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {

        this.stationService = stationService;
    }

    @GetMapping("/stations/{id}/relays")
    public List<Relay> retrieveAllRelaysOnStation(@PathVariable Long id) {

        Station stationOptional = stationService.findOne(id);

        // if (!stationOptional.isPresent()) {
        // throw new RelayNotFoundException("id- " + id);
        // }

        return stationOptional.getRelay();
    }

    @GetMapping("/stations")
    public List<Station> retrieveAllStation() {

        return stationService.findAll();
    }

}
