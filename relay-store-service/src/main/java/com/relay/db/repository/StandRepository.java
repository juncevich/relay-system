package com.relay.db.repository;

import com.relay.db.entity.storage.Stand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandRepository extends JpaRepository<Stand, Long> {
}
