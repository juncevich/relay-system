package com.relay.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.relay.model.Relay;

public interface RelayRepository extends CrudRepository<Relay, BigInteger> {

    /**
     * Find Relay by text
     *
     * @param text
     *            text
     * @return relay
     */
    Relay findByText(@Param("text") String text);
}
