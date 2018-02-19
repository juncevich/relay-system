package com.relay.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.places.Station;

public class StationRepositoryTest extends AbsrtactRepositoryTest {

    @Autowired
    private StationRepository stationRepository;

    @Test
    public void assertEmptyStationList() {

        List<Station> all = stationRepository.findAll();
        assertEquals(0, all.size());
    }
}
