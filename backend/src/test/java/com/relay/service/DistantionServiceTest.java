package com.relay.service;

import static junit.framework.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Distantion;
import com.relay.repository.AbstractRepositoryTest;
import com.relay.repository.DistantionRepository;

public class DistantionServiceTest extends AbstractRepositoryTest {

    @Autowired
    private DistantionService distantionService;

    @Autowired
    private DistantionRepository distantionRepository;

    @Override
    @Before
    public void setUp() {

        distantionRepository.deleteAll().subscribe();
    }

    @Test
    public void findAll() {

        List<Distantion> emptyDistantionList =
                distantionService.findAll().collect(Collectors.toList()).block();
        assertEquals(0, emptyDistantionList.size());
    }

    @Test
    public void testSuccessSaveDistantion() {

        Distantion distantion = new Distantion();
        distantion.setName("Test_distance");
        // District district = new District();
        // district.setName("Test_district");
        // distantion.setDistricts(Arrays.asList(district));

        Distantion savedDistantion = distantionService.save(distantion).block();

    }
}
