package com.relay.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.relay.model.Relay;

@Repository
public interface RelayRepository extends MongoRepository<Relay, Long> {

    /**
     * Find Relay by text
     * 
     * @param text
     *            text
     * @return relay
     */
    List<Relay> findByText(@Param("text") String text);
}
