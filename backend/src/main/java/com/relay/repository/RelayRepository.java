package com.relay.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

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

    /**
     * Find relay by born date
     * 
     * @param dateOfManufacture
     *            {@link Relay#getDateOfManufacture()}
     * @return relay
     */
    List<Relay> findByDateOfManufacture(LocalDate dateOfManufacture);

    /**
     * Find relay by born date
     *
     * @param dateOfManufacture
     *            {@link Relay#getDateOfManufacture()}
     * @return relay
     */
    List<Relay> findByDateOfManufactureAfter(LocalDate dateOfManufacture);
}
