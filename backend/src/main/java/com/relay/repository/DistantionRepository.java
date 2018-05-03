package com.relay.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.relay.model.Distantion;

public interface DistantionRepository extends ReactiveCrudRepository<Distantion, String> {
}
