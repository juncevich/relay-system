package com.relay.db.dao;

import com.relay.db.entity.storage.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseDao extends JpaRepository<Warehouse, Long> {
}
