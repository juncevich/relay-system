package com.relay.repository;

import org.springframework.data.repository.CrudRepository;

import com.relay.model.places.Station;

public interface StationRepository extends CrudRepository<Station, Long> {
}
