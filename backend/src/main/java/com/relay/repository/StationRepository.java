package com.relay.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.relay.model.places.Station;

@Repository
public interface StationRepository extends MongoRepository<Station, Long> {
}
