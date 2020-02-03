package com.relay.db.repository;

import java.math.BigInteger;

import com.relay.db.entity.Shelve;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelveRepository extends PagingAndSortingRepository<Shelve, BigInteger> {
}
