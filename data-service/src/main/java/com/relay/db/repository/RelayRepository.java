package com.relay.db.repository;

import com.relay.db.entity.Relay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RelayRepository extends PagingAndSortingRepository<Relay, Long> {


    /**
     * Find relay by born date
     *
     * @param creationDate {@link Relay#creationDate}
     * @param pageable     {@link Pageable}
     * @return relay
     */
    Page<Relay> findByCreationDate(LocalDate creationDate, Pageable pageable);

    /**
     * Find relay by born date
     *
     * @param dateOfManufacture {@link Relay#creationDate}
     * @param pageable          {@link Pageable}
     * @return relay
     */
    Page<Relay> findByCreationDateAfter(LocalDate dateOfManufacture, Pageable pageable);

    /**
     * Find list of relays by verification date
     *
     * @param date     {@link Relay#lastCheckDate}
     * @param pageable * {@link Pageable}
     * @return relay page
     */
    Page<Relay> findByLastCheckDate(LocalDate date, Pageable pageable);

    /**
     * Find relay by serial number
     *
     * @param serialNumber {@link Relay#serialNumber}
     * @return relay
     */
    Relay findBySerialNumber(String serialNumber);

    /**
     * Find relay before born date
     *
     * @param dateOfManufacture {@link Relay#creationDate}
     * @param pageable          {@link Pageable}
     * @return relay
     */
    Page<Relay> findByCreationDateBefore(LocalDate dateOfManufacture, Pageable pageable);
}
