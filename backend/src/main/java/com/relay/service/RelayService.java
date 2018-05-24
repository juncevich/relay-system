package com.relay.service;

import org.springframework.stereotype.Service;

import com.relay.model.Relay;
import com.relay.repository.RelayRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@link Relay} service
 */
@Service
public class RelayService {

    private RelayRepository relayRepository;

    public RelayService(final RelayRepository relayRepository) {

        this.relayRepository = relayRepository;
    }

    /**
     * Find all relays
     * 
     * @return list with all relays
     */
    public Flux<Relay> findAll() {

        return relayRepository.findAll();
    }

    public Mono<Relay> save(Relay relay) {

        return relayRepository.save(relay);
    }

    public Mono<Relay> findOne(String id) {

        return relayRepository.findById(id);
    }

    public Mono<Void> deleteById(String id) {

        return relayRepository.deleteById(id);
    }

    public Flux<Relay> findByText(String text) {

        return relayRepository.findByText(text);
    }
}
