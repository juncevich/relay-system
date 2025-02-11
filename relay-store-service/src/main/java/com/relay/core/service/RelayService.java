package com.relay.core.service;

import com.relay.db.mappers.RelayMapper;
import com.relay.db.repository.RelayRepository;
import com.relay.web.model.Relay;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * {@link Relay} service
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RelayService {

    /**
     * Relay repository
     */
    private final RelayRepository relayRepository;

    private final RelayMapper relayMapper;

    /**
     * Find all relays
     *
     * @return list with all {@link Relay}
     */
    public List<Relay> findAll(Pageable pageable) {

        Slice<com.relay.db.entity.items.Relay> relaySlice = relayRepository.findAll(pageable);
        if (relaySlice.hasContent()) {
            return relaySlice.getContent()
                    .stream()
                    .map(relayMapper::mapEntityToModel).toList();
        } else {
            return emptyList();
        }
    }

    /**
     * Save relay method
     *
     * @param relay {@link Relay}
     * @return {@link Relay}
     */
    public Relay save(Relay relay) {

        com.relay.db.entity.items.Relay entity = relayMapper.mapModelToEntity(relay);
        com.relay.db.entity.items.Relay savedEntity = relayRepository.save(entity);
        return relayMapper.mapEntityToModel(savedEntity);
    }

    /**
     * Find relay by id
     *
     * @param id {@link Relay#getSerialNumber()}
     * @return optional of {@link Relay}
     */
    public Relay findOne(Long id) {

        var foundedEntity = relayRepository.findById(id).orElse(null);
        return relayMapper.mapEntityToModel(foundedEntity);
    }

    /**
     * Delete relay by id
     *
     * @param id
     */
    public void deleteById(BigInteger id) {

        // relayRepository.deleteById(id);

    }

    /**
     * Find relay by verification date
     *
     * @param date {@link Relay#getVerificationDate()}
     * @return Page of {@link Relay}
     */
    public Page<Relay> findByVerificationDate(LocalDate date) {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        // return relayRepository.findByVerificationDate(date, pageable);
        return null;
    }

    /**
     * Find relay after date of manufacture
     *
     * @param date {@link Relay#getCreatedAt()}
     * @return List of {@link Relay}
     */
    public Page<Relay> findByDateOfManufactureAfter(LocalDate date) {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        // return relayRepository.findByDateOfManufactureAfter(date, pageable);
        return null;
    }

    /**
     * Find relay by date of manufacture
     *
     * @param date {@link Relay#getCreatedAt()}
     * @return List of {@link Relay}
     */
    public List<Relay> findByDateOfManufacture(LocalDate date) {

        log.info("Try to find relay with date {}", date);
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");

        final var relaysByCreationDate = relayRepository.findByCreationDate(date, pageable);
        return relayMapper.mapEntityToModel(relaysByCreationDate.getContent());
    }

    /**
     * Find relay by serial number
     *
     * @param serialNumber {@link Relay#getSerialNumber()}
     * @return {@link Relay}
     */
    public Relay findBySerialNumber(String serialNumber) {

        final var relayBySerialNumber = relayRepository.findBySerialNumber(serialNumber);
        return relayMapper.mapEntityToModel(relayBySerialNumber);
    }

    /**
     * Find relay before date of manufacture
     *
     * @param date {@link Relay#getCreatedAt()}
     * @return List of {@link Relay}
     */
    public Page<Relay> findByDateOfManufactureBefore(LocalDate date) {

        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        // return relayRepository.findByDateOfManufactureBefore(date, pageable);
        return null;
    }

}
