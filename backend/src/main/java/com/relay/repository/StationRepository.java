package com.relay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.relay.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
