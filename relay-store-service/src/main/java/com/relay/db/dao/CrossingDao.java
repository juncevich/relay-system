package com.relay.db.dao;

import com.relay.db.entity.location.Crossing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrossingDao extends JpaRepository<Crossing, Long> {
}
