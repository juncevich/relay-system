package com.relay.web.controllers;

import com.relay.service.StationService;
import com.relay.web.exeptions.RelayNotFoundException;
import com.relay.web.model.Relay;
import com.relay.web.model.places.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {

        this.stationService = stationService;
    }

    @GetMapping("/stations/{id}/relays")
    public List<Relay> retrieveAllRelaysOnStation(@PathVariable Long id) {

        return stationService.findOne(id).map(Station::getRelay)
                .orElseThrow(() -> new RelayNotFoundException("id- " + id));
    }

    @GetMapping("/stations")
    public Page<Station> retrieveAllStation() {

        return stationService.findAll();
    }

}
