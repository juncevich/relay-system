package com.relay.db.repository;

import com.relay.db.entity.storage.RelayCabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelayCabinetRepository extends JpaRepository<RelayCabinet, Long> {
}
