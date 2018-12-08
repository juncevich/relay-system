package com.relay.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.relay.model.Relay;
import com.relay.repository.RelayRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link Relay} service
 */
@Service
@Slf4j
public class RelayService {

    /**
     * Relay repository
     */
    private RelayRepository relayRepository;

    /**
     * 
     * @param relayRepository
     *            {@link RelayRepository}
     */
    public RelayService(final RelayRepository relayRepository) {

        this.relayRepository = relayRepository;
    }

    /**
     * Find all relays
     * 
     * @return list with all {@link Relay}
     */
    public Page<Relay> findAll() {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        return relayRepository.findAll(pageable);
    }

    /**
     * Save relay method
     * 
     * @param relay
     *            {@link Relay}
     * @return {@link Relay}
     */
    public Relay save(Relay relay) {

        return relayRepository.save(relay);
    }

    /**
     * Find relay by id
     * 
     * @param id
     *            {@link Relay#id}
     * @return optional of {@link Relay}
     */
    public Optional<Relay> findOne(BigInteger id) {

        return relayRepository.findById(id);
    }

    /**
     * Delete relay by id
     * 
     * @param id
     *            {@link Relay#id}
     */
    public void deleteById(BigInteger id) {

        relayRepository.deleteById(id);
    }

    /**
     * Find relay by verification date
     * 
     * @param date
     *            {@link Relay#verificationDate}
     * @return Page of {@link Relay}
     */
    public Page<Relay> findByVerificationDate(LocalDate date) {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        return relayRepository.findByVerificationDate(date, pageable);
    }

    /**
     * Find relay after date of manufacture
     * 
     * @param date
     *            {@link Relay#dateOfManufacture}
     * @return List of {@link Relay}
     */
    public Page<Relay> findByDateOfManufactureAfter(LocalDate date) {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        return relayRepository.findByDateOfManufactureAfter(date, pageable);
    }

    /**
     * Find relay by date of manufacture
     *
     * @param date
     *            {@link Relay#dateOfManufacture}
     * @return List of {@link Relay}
     */
    public Page<Relay> findByDateOfManufacture(LocalDate date) {

        log.info("Try to find relay with date {}", date);
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        return relayRepository.findByDateOfManufacture(date, pageable);
    }

    /**
     * Find relay by serial number
     * 
     * @param serialNumber
     *            {@link Relay#serialNumber}
     * @return {@link Relay}
     */
    public Relay findBySerialNumber(String serialNumber) {

        return relayRepository.findBySerialNumber(serialNumber);
    }

    /**
     * Find relay before date of manufacture
     *
     * @param date
     *            {@link Relay#dateOfManufacture}
     * @return List of {@link Relay}
     */
    public Page<Relay> findByDateOfManufactureBefore(LocalDate date) {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        return relayRepository.findByDateOfManufactureBefore(date, pageable);
    }
}
