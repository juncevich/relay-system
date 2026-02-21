package com.relay.db.dao;

import com.relay.db.entity.items.Relay;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface RelayDao extends JpaRepository<@NonNull Relay, @NonNull Long> {

    /**
     * Find relay by creation date.
     * Uses JPQL CAST function for database-agnostic date comparison.
     *
     * @param creationDate {@link Relay#getCreatedAt()}
     * @param pageable     {@link Pageable}
     * @return relay page
     */
    @Query("SELECT r FROM Relay r WHERE CAST(r.createdAt AS DATE) = :creationDate")
    Page<@NonNull Relay> findByCreationDate(@Param("creationDate") LocalDate creationDate, Pageable pageable);

    /**
     * Find list of relays by last check date.
     * Uses JPQL CAST function for database-agnostic date comparison.
     *
     * @param lastCheckDate {@link Relay#getLastCheckDate()}
     * @param pageable      {@link Pageable}
     * @return relay page
     */
    @Query("SELECT r FROM Relay r WHERE CAST(r.lastCheckDate AS DATE) = :lastCheckDate")
    Page<@NonNull Relay> findByLastCheckDate(@Param("lastCheckDate") LocalDate lastCheckDate, Pageable pageable);

    /**
     * Find relay by serial number
     *
     * @param serialNumber {@link Relay#getSerialNumber()}
     * @return relay wrapped in Optional
     */
    Optional<Relay> findBySerialNumber(String serialNumber);
}
