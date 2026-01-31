package com.relay.db.dao;

import com.relay.db.entity.location.TrackPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackPointDao extends JpaRepository<TrackPoint, Long> {
}
