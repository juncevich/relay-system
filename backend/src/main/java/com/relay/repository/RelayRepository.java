package com.relay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.relay.model.Relay;

@Repository
public interface RelayRepository extends JpaRepository<Relay, Long> {
}
