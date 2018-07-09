package com.relay.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.AbstractDBTest;
import com.relay.repository.StationRepository;
import com.relay.util.TestUtils;

public class StationServiceTest extends AbstractDBTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private StationService stationService;

    @Autowired
    private StationRepository stationRepository;

    @Before
    public void setUp() {

        stationRepository.deleteAll();
    }

    @Test
    public void testFindAll() {

        testUtils.generateEmptyStation(15);
        assertEquals(10, stationService.findAll().getContent().size());
    }
}
