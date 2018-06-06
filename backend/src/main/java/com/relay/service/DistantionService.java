package com.relay.service;

import org.springframework.stereotype.Service;

import com.relay.model.Distantion;
import com.relay.repository.DistantionRepository;

@Service
public class DistantionService {

    private DistantionRepository distantionRepository;

    public DistantionService(DistantionRepository distantionRepository) {

        this.distantionRepository = distantionRepository;
    }

    public Iterable<Distantion> findAll() {

        return distantionRepository.findAll();
    }

    public Distantion save(Distantion distantion) {

        return distantionRepository.save(distantion);
    }
}
