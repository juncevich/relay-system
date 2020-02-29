package com.relay.service;

import com.relay.db.entity.RelayEntity;
import com.relay.db.repository.RelayRepository;
import com.relay.mappers.RelayMapper;
import com.relay.web.model.Relay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * {@link Relay} service
 */
@Service
@Transactional
@Slf4j
public class RelayService {

    /**
     * Relay repository
     */
    private final RelayRepository relayRepository;

    /**
     * @param relayRepository {@link RelayRepository}
     */
    public RelayService(final RelayRepository relayRepository) {

        this.relayRepository = relayRepository;
    }

    /**
     * Find all relays
     *
     * @return list with all {@link Relay}
     */
    public List<Relay> findAll(Pageable pageable) {
        Page<RelayEntity> relayPage = relayRepository.findAll(pageable);
        return RelayMapper.INSTANCE.mapEntityToModel(relayPage.toList());
    }

    /**
     * Save relay method
     *
     * @param relay {@link Relay}
     * @return {@link Relay}
     */
    public Relay save(Relay relay) {

        RelayEntity entity = RelayMapper.INSTANCE.mapModelToEntity(relay);
        RelayEntity savedEntity = relayRepository.save(entity);
        return RelayMapper.INSTANCE.mapEntityToModel(savedEntity);
    }

    /**
     * Find relay by id
     *
     * @param id {@link Relay#id}
     * @return optional of {@link Relay}
     */
    public Optional<Relay> findOne(BigInteger id) {

//        return relayRepository.findById(id);
        return null;
    }

    /**
     * Delete relay by id
     *
     * @param id {@link Relay#id}
     */
    public void deleteById(BigInteger id) {

//        relayRepository.deleteById(id);

    }

    /**
     * Find relay by verification date
     *
     * @param date {@link Relay#verificationDate}
     * @return Page of {@link Relay}
     */
    public Page<Relay> findByVerificationDate(LocalDate date) {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
//        return relayRepository.findByVerificationDate(date, pageable);
        return null;
    }

    /**
     * Find relay after date of manufacture
     *
     * @param date {@link Relay#dateOfManufacture}
     * @return List of {@link Relay}
     */
    public Page<Relay> findByDateOfManufactureAfter(LocalDate date) {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
//        return relayRepository.findByDateOfManufactureAfter(date, pageable);
        return null;
    }

    /**
     * Find relay by date of manufacture
     *
     * @param date {@link Relay#dateOfManufacture}
     * @return List of {@link Relay}
     */
    public Page<Relay> findByDateOfManufacture(LocalDate date) {

        log.info("Try to find relay with date {}", date);
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
//        return relayRepository.findByDateOfManufacture(date, pageable);
        return null;
    }

    /**
     * Find relay by serial number
     *
     * @param serialNumber {@link Relay#serialNumber}
     * @return {@link Relay}
     */
    public Relay findBySerialNumber(String serialNumber) {

//        return relayRepository.findBySerialNumber(serialNumber);
        return null;
    }

    /**
     * Find relay before date of manufacture
     *
     * @param date {@link Relay#dateOfManufacture}
     * @return List of {@link Relay}
     */
    public Page<Relay> findByDateOfManufactureBefore(LocalDate date) {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
//        return relayRepository.findByDateOfManufactureBefore(date, pageable);
        return null;
    }

    public List<Relay> findByStationName(String stationName) {

        List<RelayEntity> relaysByStationName = relayRepository.findRelaysByStationName(stationName);
        return RelayMapper.INSTANCE.mapEntityToModel(relaysByStationName);
    }
}
