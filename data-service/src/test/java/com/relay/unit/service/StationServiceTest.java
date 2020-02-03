package com.relay.unit.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.relay.web.model.places.Station;
import com.relay.db.repository.StationRepository;
import com.relay.service.StationService;

@RunWith(MockitoJUnitRunner.class)
public class StationServiceTest {

    @InjectMocks
    private StationService stationService;

    @Mock
    private StationRepository stationRepository;

    @Test
    public void testFindAll() {

//        Station station = new Station();
//        List<Station> stationList = Lists.newArrayList(station);
//        Page<Station> stationPage = new PageImpl<>(stationList);
//        when(stationRepository.findAll((Pageable) any())).thenReturn(stationPage);
//        assertEquals(1, stationService.findAll().getContent().size());
    }
}
