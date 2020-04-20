package com.relay.unit.db;

import com.google.common.collect.Lists;
import com.relay.db.entity.place.Station;
import com.relay.repository.StationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
@DataJpaTest
public class StationRepositoryTest {

    //    @Autowired
//    private TestEntityManager entityManager;
    @Autowired
    private StationRepository stationRepository;

    @Test
    public void testFieldsObject() {
        Station station = Station.builder()
                .name("Монетная")
                .build();
//        this.entityManager.persist(station);
        stationRepository.save(station);
        Iterable<Station> savedStations = stationRepository.findAll();
        assertEquals(1, Lists.newArrayList(savedStations).size());
//        assertNotNull(savedStation.getId());
//        assertEquals("Монетная",savedStation.getNa    me());
    }
}
