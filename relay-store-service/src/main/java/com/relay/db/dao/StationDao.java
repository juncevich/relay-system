package com.relay.db.dao;

import com.relay.db.entity.location.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationDao extends JpaRepository<Station, Long> {
}
