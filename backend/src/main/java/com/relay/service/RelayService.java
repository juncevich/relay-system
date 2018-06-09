package com.relay.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.relay.model.Relay;
import com.relay.repository.RelayRepository;

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
    public Iterable<Relay> findAll() {

        return relayRepository.findAll();
    }

    public Relay save(Relay relay) {

        return relayRepository.save(relay);
    }

    public Optional<Relay> findOne(String id) {

        return relayRepository.findById(BigInteger.valueOf(Long.parseLong(id)));
    }

    public void deleteById(String id) {

        relayRepository.deleteById(BigInteger.valueOf(Long.parseLong(id)));
    }

    public Relay findByText(String text) {

        return relayRepository.findByText(text);
    }
}
