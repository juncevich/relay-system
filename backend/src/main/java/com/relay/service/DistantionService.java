package com.relay.service;

import org.springframework.stereotype.Service;

import com.relay.model.Distantion;
import com.relay.repository.DistantionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DistantionService {

    private DistantionRepository distantionRepository;

    public DistantionService(DistantionRepository distantionRepository) {

        this.distantionRepository = distantionRepository;
    }

    public Flux<Distantion> findAll() {

        return distantionRepository.findAll();
    }

    public Mono<Distantion> save(Distantion distantion) {

        return distantionRepository.save(distantion);
    }
}
