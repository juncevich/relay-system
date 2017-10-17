package com.relay.repository;

import com.relay.model.Relay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelayRepository extends JpaRepository<Relay, Long> {
}
