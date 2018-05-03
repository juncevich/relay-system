package com.relay.service;

import static junit.framework.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.relay.model.Distantion;
import com.relay.repository.AbstractRepositoryTest;

public class DistantionServiceTest extends AbstractRepositoryTest {

    @Autowired
    private DistantionService distantionService;

    @Test
    public void findAll() {

        List<Distantion> emptyDistantionList =
                distantionService.findAll().collect(Collectors.toList()).block();
        assertTrue(emptyDistantionList.isEmpty());
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
