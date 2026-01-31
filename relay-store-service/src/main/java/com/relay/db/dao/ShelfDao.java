package com.relay.db.dao;

import com.relay.db.entity.storage.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfDao extends JpaRepository<Shelf, Long> {
}
