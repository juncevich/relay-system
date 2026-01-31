package com.relay.db.dao;

import com.relay.db.entity.storage.RelayCabinet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelayCabinetDao extends JpaRepository<RelayCabinet, Long> {
}
