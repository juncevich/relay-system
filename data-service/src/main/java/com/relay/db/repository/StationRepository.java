package com.relay.db.repository;

import com.relay.db.entity.location.Station;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends PagingAndSortingRepository<Station, Long> {
}
