package com.relay.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.relay.model.places.Station;

public interface StationRepository extends PagingAndSortingRepository<Station, Long> {
}
