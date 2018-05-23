package com.relay.repository;

import com.relay.model.places.Station;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.Objects;

public class StationRepositoryTest extends AbstractMongoDBTest {

    @Autowired
    private StationRepository stationRepository;

    @Test
    public void assertEmptyStationList() {

        Flux<Station> all = stationRepository.findAll();
        assertEquals(0, Objects.requireNonNull(all.count().block()).intValue());
    }
}
