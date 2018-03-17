package com.relay.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.relay.model.Relay;
import reactor.core.publisher.Flux;

public interface RelayRepository extends ReactiveCrudRepository<Relay, String> {

    /**
     * Find Relay by text
     *
     * @param text
     *            text
     * @return relay
     */
    Flux<Relay> findByText(@Param("text") String text);
}
