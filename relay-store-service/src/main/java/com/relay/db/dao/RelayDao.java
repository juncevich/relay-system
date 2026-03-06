package com.relay.db.dao;

import com.relay.db.entity.items.Relay;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;

public interface RelayDao extends JpaRepository<@NonNull Relay, @NonNull Long> {

    default Page<@NonNull Relay> findByCreationDate(@NonNull LocalDate creationDate, Pageable pageable) {
        var rangeStart = creationDate.atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime();
        var rangeEnd = creationDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime();
        return findByCreatedAtGreaterThanEqualAndCreatedAtLessThan(rangeStart, rangeEnd, pageable);
    }

    Page<@NonNull Relay> findByCreatedAtGreaterThanEqualAndCreatedAtLessThan(
            @NonNull OffsetDateTime rangeStart,
            @NonNull OffsetDateTime rangeEnd,
            Pageable pageable
    );

    default Page<@NonNull Relay> findByLastCheckDate(@NonNull LocalDate lastCheckDate, Pageable pageable) {
        var rangeStart = lastCheckDate.atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime();
        var rangeEnd = lastCheckDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime();
        return findByLastCheckDateGreaterThanEqualAndLastCheckDateLessThan(rangeStart, rangeEnd, pageable);
    }

    Page<@NonNull Relay> findByLastCheckDateGreaterThanEqualAndLastCheckDateLessThan(
            @NonNull OffsetDateTime rangeStart,
            @NonNull OffsetDateTime rangeEnd,
            Pageable pageable
    );

    /**
     * Find relay by serial number
     *
     * @param serialNumber {@link Relay#getSerialNumber()}
     * @return relay wrapped in Optional
     */
    Optional<Relay> findBySerialNumber(String serialNumber);
}
