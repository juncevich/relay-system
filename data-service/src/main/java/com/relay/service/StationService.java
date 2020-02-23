package com.relay.service;

import com.relay.db.repository.StationRepository;
import com.relay.web.model.places.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
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
//        return stationRepository.findAll(pageable);
    return null;
    }

    /**
     * Save station
     * 
     * @param station
     *            Saved {@link Station}
     * @return {@link Station}
     */
    public Station save(Station station) {

//        return stationRepository.save(station);
        return null;
    }

    /**
     * Find Station by id
     * 
     * @param id
     *            {@link Station#id}
     * @return Optional of {@link Station}
     */
    public Optional<Station> findOne(long id) {

//        return stationRepository.findById(id);
        return null;
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
