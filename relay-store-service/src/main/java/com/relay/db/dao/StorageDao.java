package com.relay.db.dao;

import com.relay.db.entity.storage.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageDao extends JpaRepository<Storage, Long> {
}
