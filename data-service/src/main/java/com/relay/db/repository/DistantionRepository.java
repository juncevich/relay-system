package com.relay.db.repository;

import com.relay.db.entity.location.Distantion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistantionRepository extends CrudRepository<Distantion, Long> {
}
