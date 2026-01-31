package com.relay.db.dao;

import com.relay.db.entity.storage.Stand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandDao extends JpaRepository<Stand, Long> {
}
