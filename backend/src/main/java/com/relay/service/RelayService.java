package com.relay.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.relay.model.Relay;
import com.relay.repository.RelayRepository;

/**
 * {@link Relay} service
 */
@Service
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
    public Iterable<Relay> findAll() {

        return relayRepository.findAll();
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
    public Optional<Relay> findOne(String id) {

        return relayRepository.findById(BigInteger.valueOf(Long.parseLong(id)));
    }

    /**
     * Delete relay by id
     * 
     * @param id
     *            {@link Relay#id}
     */
    public void deleteById(String id) {

        relayRepository.deleteById(BigInteger.valueOf(Long.parseLong(id)));
    }

    public Relay findByText(String text) {

        return relayRepository.findByText(text);
    }

    /**
     * Find relay by verification date
     * 
     * @param date
     *            {@link Relay#verificationDate}
     * @return List of {@link Relay}
     */
    public List<Relay> findByVerificationDate(LocalDate date) {

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
    public List<Relay> findByDateOfManufactureAfter(LocalDate date) {

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
    public List<Relay> findByDateOfManufacture(LocalDate date) {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        return relayRepository.findByDateOfManufacture(date, pageable);
    }

    /**
     * Save list of relay
     * 
     * @param relayList
     *            list of relays to save
     */
    public void saveAll(List<Relay> relayList) {

        relayRepository.saveAll(relayList);
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
}
