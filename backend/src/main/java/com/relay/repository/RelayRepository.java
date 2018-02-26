package com.relay.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.relay.model.Relay;

@Repository
public interface RelayRepository extends ReactiveCrudRepository<Relay, Long> {

    /**
     * Find Relay by text
     * 
     * @param text
     *            text
     * @return relay
     */
    List<Relay> findByText(@Param("text") String text);
}
