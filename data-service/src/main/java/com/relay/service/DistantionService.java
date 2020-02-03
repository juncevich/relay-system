package com.relay.service;

import org.springframework.stereotype.Service;

import com.relay.web.model.Distantion;
import com.relay.db.repository.DistantionRepository;

@Service
public class DistantionService {

    /**
     * Repository for {@link Distantion}
     */
    private final DistantionRepository distantionRepository;

    /**
     * Constructor
     * 
     * @param distantionRepository
     *            injected {@link DistantionRepository}
     */
    public DistantionService(DistantionRepository distantionRepository) {

        this.distantionRepository = distantionRepository;
    }

    /**
     * 
     * @return
     */
    public Iterable<Distantion> findAll() {

//        return distantionRepository.findAll();
    return null;
    }


    public Distantion save(Distantion distantion) {

//        return distantionRepository.save(distantion);
    return null;
    }
}
