package com.relay.service;

import org.springframework.stereotype.Component;

import com.relay.model.Relay;
import com.relay.repository.RelayRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@link Relay} service
 */
@Component
public class RelayService {

    private RelayRepository relayRepository;

    public RelayService(final RelayRepository relayRepository) {

        this.relayRepository = relayRepository;
    }

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

}
