package com.relay.db.repository;

import com.relay.db.entity.location.TrackPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackPointRepository extends JpaRepository<TrackPoint, Long> {
}
