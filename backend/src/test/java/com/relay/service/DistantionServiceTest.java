package com.relay.service;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Distantion;
import com.relay.repository.AbstractDBTest;
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
        Collections.singletonList(emptyDistantionList);
        assertEquals(1, Collections.singletonList(emptyDistantionList).size());
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
