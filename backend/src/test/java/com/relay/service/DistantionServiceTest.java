package com.relay.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.relay.AbstractDBTest;
import com.relay.model.Distantion;
import com.relay.repository.DistantionRepository;

public class DistantionServiceTest extends AbstractDBTest {

    @Autowired
    private DistantionService distantionService;

    @Autowired
    private DistantionRepository distantionRepository;

    @Override
    @Before
    public void setUp() {

        distantionRepository.deleteAll();
    }

    @Test
    public void findAll() {

        Iterable<Distantion> emptyDistantionList = distantionService.findAll();
        assertEquals(0, Lists.newArrayList(emptyDistantionList).size());
    }

    @Test
    public void testSuccessSaveDistantion() {

        Distantion distantion = new Distantion();
        distantion.setName("Test_distance");
        // District district = new District();
        // district.setName("Test_district");
        // distantion.setDistricts(Arrays.asList(district));

        Distantion savedDistantion = distantionService.save(distantion);

    }
}
