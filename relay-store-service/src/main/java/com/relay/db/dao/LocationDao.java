package com.relay.db.dao;

import com.relay.db.entity.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDao extends JpaRepository<Location, Long> {
}
