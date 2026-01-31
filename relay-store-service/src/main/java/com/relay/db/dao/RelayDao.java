package com.relay.db.dao;

import com.relay.db.entity.items.Relay;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface RelayDao extends JpaRepository<@NotNull Relay, @NotNull Long> {

    /**
     * Find relay by born date
     *
     * @param creationDate {@link Relay#getCreatedAt()}
     * @param pageable     {@link Pageable}
     * @return relay
     */
    @Query(value = "SELECT * FROM Relay WHERE DATE_TRUNC('day', created_at) = :creationDate", nativeQuery = true)
    Page<@NotNull Relay> findByCreationDate(@Param("creationDate") LocalDate creationDate, Pageable pageable);

    /**
     * Find list of relays by last check date
     *
     * @param lastCheckDate {@link Relay#getLastCheckDate()}
     * @param pageable      * {@link Pageable}
     * @return relay page
     */
    @Query(value = "SELECT * FROM Relay WHERE DATE_TRUNC('day', last_check_date) = :lastCheckDate", nativeQuery = true)
    Page<@NotNull Relay> findByLastCheckDate(@Param("lastCheckDate") LocalDate lastCheckDate, Pageable pageable);

    /**
     * Find relay by serial number
     *
     * @param serialNumber {@link Relay#getSerialNumber()}
     * @return relay
     */
    Relay findBySerialNumber(String serialNumber);
}
