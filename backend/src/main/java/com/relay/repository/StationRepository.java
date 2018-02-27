package com.relay.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.relay.model.places.Station;

public interface StationRepository extends ReactiveCrudRepository<Station, Long> {
}
