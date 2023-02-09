package com.relay.db.repository;

import com.relay.db.entity.items.Relay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RelayRepository extends PagingAndSortingRepository<Relay, Long> {


    /**
     * Find relay by born date
     *
     * @param creationDate {@link Relay#getCreationDate()}
     * @param pageable     {@link Pageable}
     * @return relay
     */
    @Query(value = "SELECT * FROM Relay WHERE DATE_TRUNC('day', creation_date) = :creationDate", nativeQuery = true)
    Page<Relay> findByCreationDate(@Param("creationDate") LocalDate creationDate, Pageable pageable);

    /**
     * Find list of relays by last check date
     *
     * @param lastCheckDate {@link Relay#getLastCheckDate()}
     * @param pageable      * {@link Pageable}
     * @return relay page
     */
    @Query(value = "SELECT * FROM Relay WHERE DATE_TRUNC('day', last_check_date) = :lastCheckDate", nativeQuery = true)
    Page<Relay> findByLastCheckDate(@Param("lastCheckDate") LocalDate lastCheckDate, Pageable pageable);

    /**
     * Find relay by serial number
     *
     * @param serialNumber {@link Relay#getSerialNumber()}
     * @return relay
     */
    Relay findBySerialNumber(String serialNumber);
}
