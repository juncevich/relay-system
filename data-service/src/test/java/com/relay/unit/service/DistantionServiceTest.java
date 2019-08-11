package com.relay.unit.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.relay.model.Distantion;
import com.relay.repository.DistantionRepository;
import com.relay.service.DistantionService;

@RunWith(MockitoJUnitRunner.class)
public class DistantionServiceTest {

    @InjectMocks
    private DistantionService distantionService;

    @Mock
    private DistantionRepository distantionRepository;


    @Test
    public void findAll() {

        when(distantionRepository.findAll()).thenReturn(Lists.newArrayList());
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

        distantionService.save(distantion);

    }
}