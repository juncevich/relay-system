package com.relay.db.repository;

import com.relay.db.entity.location.Crossing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrossingRepository extends JpaRepository<Crossing, Long> {
}
