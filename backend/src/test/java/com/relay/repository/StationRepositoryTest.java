package com.relay.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.places.Station;

import reactor.core.publisher.Flux;

public class StationRepositoryTest extends AbsrtactRepositoryTest {

    @Autowired
    private StationRepository stationRepository;

    @Test
    public void assertEmptyStationList() {

        Flux<Station> all = stationRepository.findAll();
        assertEquals(0, all.count());
    }
}
