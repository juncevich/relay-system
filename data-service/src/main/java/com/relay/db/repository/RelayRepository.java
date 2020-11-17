package com.relay.db.repository;

import com.relay.db.entity.items.Relay;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelayRepository extends PagingAndSortingRepository<Relay, Long> {


//    /**
//     * Find relay by born date
//     *
//     * @param creationDate {@link Relay#getCreationDate()}
//     * @param pageable     {@link Pageable}
//     * @return relay
//     */
//    Page<Relay> findByCreationDate(LocalDate creationDate, Pageable pageable);
//
//    /**
//     * Find list of relays by last check date
//     *
//     * @param dateTime {@link RelayEntity#getLastCheckDate()}
//     * @param pageable * {@link Pageable}
//     * @return relay page
//     */
//    Page<Relay> findByLastCheckDate(LocalDateTime dateTime, Pageable pageable);
//
//    /**
//     * Find relay by serial number
//     *
//     * @param serialNumber {@link RelayEntity#getSerialNumber()}
//     * @return relay
//     */
//    Relay findBySerialNumber(String serialNumber);
//
//
//    @Query("from RelayEntity as r where r.station.name=:stationName")
//    List<Relay> findRelaysByStationName(@Param("stationName") String stationName);
//
//    @Query("from RelayEntity as r where r.station.name=:stationName and r.creationDate=:creationDate")
//    List<Relay> findRelaysByStationNameAndCreationDate(@Param("stationName") String stationName, @Param("creationDate") LocalDate creationDate);
}
