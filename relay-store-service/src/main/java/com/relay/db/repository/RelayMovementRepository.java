package com.relay.db.repository;

import com.relay.db.entity.history.RelayMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelayMovementRepository extends JpaRepository<RelayMovement, Long> {

    Page<RelayMovement> findByRelayId(Long relayId, Pageable pageable);

    List<RelayMovement> findByFromStorageId(Long storageId);

    List<RelayMovement> findByToStorageId(Long storageId);
}