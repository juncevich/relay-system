package com.relay.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.relay.model.Relay;

public interface RelayRepository extends PagingAndSortingRepository<Relay, BigInteger> {


    /**
     * Find relay by born date
     * 
     * @param dateOfManufacture
     *            {@link Relay#dateOfManufacture}
     *
     * @param pageable
     *            {@link Pageable}
     * @return relay
     */
    List<Relay> findByDateOfManufacture(LocalDate dateOfManufacture, Pageable pageable);

    /**
     * Find relay by born date
     *
     * @param dateOfManufacture
     *            {@link Relay#dateOfManufacture}
     * 
     * @param pageable
     *            {@link Pageable}
     * @return relay
     */
    List<Relay> findByDateOfManufactureAfter(LocalDate dateOfManufacture, Pageable pageable);

    /**
     * Find list of relays by verification date
     * 
     * @param date
     *            {@link Relay#verificationDate}
     * 
     * @param pageable
     *            * {@link Pageable}
     * @return relay list
     */
    List<Relay> findByVerificationDate(LocalDate date, Pageable pageable);

    /**
     * Find relay by serial number
     * 
     * @param serialNumber
     *            {@link Relay#serialNumber}
     * @return relay
     */
    Relay findBySerialNumber(String serialNumber);

    /**
     * Find relay before born date
     *
     * @param dateOfManufacture
     *            {@link Relay#dateOfManufacture}
     *
     * @param pageable
     *            {@link Pageable}
     * @return relay
     */
    List<Relay> findByDateOfManufactureBefore(LocalDate dateOfManufacture, Pageable pageable);
}
