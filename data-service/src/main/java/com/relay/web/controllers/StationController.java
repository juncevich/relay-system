package com.relay.web.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController {

    // private final StationService stationService;
    //
    // @Autowired
    // public StationController(StationService stationService) {
    //
    // this.stationService = stationService;
    // }
    //
    // @GetMapping("/stations/{id}/relays")
    // public List<Relay> retrieveAllRelaysOnStation(@PathVariable Long id) {
    //
    // Station stationOptional = stationService.findOne(id);
    //
    // // if (!stationOptional.isPresent()) {
    // // throw new RelayNotFoundException("id- " + id);
    // // }
    //
    // return stationOptional.getRelay();
    // }
    //
    // @GetMapping("/stations")
    // public List<Station> retrieveAllStation() {
    //
    // return stationService.findAll();
    // }

}
