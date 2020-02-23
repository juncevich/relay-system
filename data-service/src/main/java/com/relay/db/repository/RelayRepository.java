package com.relay.db.repository;

import com.relay.db.entity.RelayEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RelayRepository extends PagingAndSortingRepository<RelayEntity, Long> {


    /**
     * Find relay by born date
     *
     * @param creationDate {@link RelayEntity#getCreationDate()}
     * @param pageable     {@link Pageable}
     * @return relay
     */
    Page<RelayEntity> findByCreationDate(LocalDate creationDate, Pageable pageable);

    /**
     * Find list of relays by last check date
     *
     * @param dateTime {@link RelayEntity#getLastCheckDate()}
     * @param pageable * {@link Pageable}
     * @return relay page
     */
    Page<RelayEntity> findByLastCheckDate(LocalDateTime dateTime, Pageable pageable);

    /**
     * Find relay by serial number
     *
     * @param serialNumber {@link RelayEntity#getSerialNumber()}
     * @return relay
     */
    RelayEntity findBySerialNumber(String serialNumber);


    @Query("from RelayEntity as r where r.station.name=:stationName")
    List<RelayEntity> findRelaysByStationName(@Param("stationName") String stationName);

    @Query("from RelayEntity as r where r.station.name=:stationName and r.creationDate=:creationDate")
    List<RelayEntity> findRelaysByStationNameAndCreationDate(@Param("stationName") String stationName, @Param("creationDate") LocalDate creationDate);
}
