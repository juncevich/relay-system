package com.relay.service;

import org.springframework.stereotype.Service;

import com.relay.model.Distantion;
import com.relay.repository.DistantionRepository;

@Service
public class DistantionService {

    /**
     * Repository for {@link Distantion}
     */
    private DistantionRepository distantionRepository;

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

        return distantionRepository.findAll();
    }

    public Distantion save(Distantion distantion) {

        return distantionRepository.save(distantion);
    }
}
