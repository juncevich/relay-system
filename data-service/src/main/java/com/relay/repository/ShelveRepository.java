package com.relay.repository;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.relay.model.shelves.Shelve;

public interface ShelveRepository extends PagingAndSortingRepository<Shelve, BigInteger> {
}
