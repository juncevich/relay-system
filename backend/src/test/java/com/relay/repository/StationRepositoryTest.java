package com.relay.repository;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.places.Station;

public class StationRepositoryTest extends AbstractDBTest {

    @Autowired
    private StationRepository stationRepository;

    @Test
    public void assertEmptyStationList() {

        List<Station> all = Lists.newArrayList(stationRepository.findAll());
        assertEquals(0, all.size());
    }
}
