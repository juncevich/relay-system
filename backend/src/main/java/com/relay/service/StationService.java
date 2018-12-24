package com.relay.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.relay.model.places.Station;
import com.relay.repository.StationRepository;

@Component
public class StationService {

    /**
     * Station repository
     */
    private final StationRepository stationRepository;

    /**
     * 
     * @param stationRepository
     *            {@link StationRepository}
     */
    public StationService(StationRepository stationRepository) {

        this.stationRepository = stationRepository;
    }

    /**
     * Find all stations
     * 
     * @return page with {@link Station}
     */
    public Page<Station> findAll() {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        return stationRepository.findAll(pageable);
    }

    /**
     * Save station
     * 
     * @param station
     *            Saved {@link Station}
     * @return {@link Station}
     */
    public Station save(Station station) {

        return stationRepository.save(station);
    }

    /**
     * Find Station by id
     * 
     * @param id
     *            {@link Station#id}
     * @return Optional of {@link Station}
     */
    public Optional<Station> findOne(long id) {

        return stationRepository.findById(id);
    }

    /**
     * Delete Station by id
     * 
     * @param id
     *            {@link Station#id}
     */
    public void deleteById(long id) {

        stationRepository.deleteById(id);
    }

}
