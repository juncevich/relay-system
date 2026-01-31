package com.relay.db.repository;

import com.relay.db.entity.items.Relay;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RelayRepository extends JpaRepository<@NotNull Relay, @NotNull Long> {


    /**
     * Find relay by creation date.
     * Uses JPQL CAST function for database-agnostic date comparison.
     *
     * @param creationDate {@link Relay#getCreatedAt()}
     * @param pageable     {@link Pageable}
     * @return relay page
     */
    @Query("SELECT r FROM Relay r WHERE CAST(r.createdAt AS DATE) = :creationDate")
    Page<@NotNull Relay> findByCreationDate(@Param("creationDate") LocalDate creationDate, Pageable pageable);

    /**
     * Find list of relays by last check date.
     * Uses JPQL CAST function for database-agnostic date comparison.
     *
     * @param lastCheckDate {@link Relay#getLastCheckDate()}
     * @param pageable      {@link Pageable}
     * @return relay page
     */
    @Query("SELECT r FROM Relay r WHERE CAST(r.lastCheckDate AS DATE) = :lastCheckDate")
    Page<@NotNull Relay> findByLastCheckDate(@Param("lastCheckDate") LocalDate lastCheckDate, Pageable pageable);

    /**
     * Find relay by serial number
     *
     * @param serialNumber {@link Relay#getSerialNumber()}
     * @return relay
     */
    Relay findBySerialNumber(String serialNumber);
}
