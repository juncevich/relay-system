package com.relay.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.relay.model.places.Station;

@Repository
public interface StationRepository extends ReactiveCrudRepository<Station, Long> {
}
