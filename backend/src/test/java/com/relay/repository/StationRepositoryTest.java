package com.relay.repository;

import com.relay.model.Relay;
import com.relay.model.places.Station;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StationRepositoryTest extends AbsrtactRepositoryTest{

    @Autowired
    private StationRepository stationRepository;

    @Test
    public void assertEmptyStationList() {
        List<Station> all = stationRepository.findAll();
        assertEquals(0, all.size());
    }
}
