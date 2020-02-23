package com.relay.service;

import com.relay.db.repository.DistantionRepository;
import com.relay.web.model.Distantion;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DistantionService {

    /**
     * Repository for {@link Distantion}
     */
    private final DistantionRepository distantionRepository;

    /**
     * Constructor
     * 
     * @param distantionRepository
     *            injected {@link DistantionRepository}
     */
    public DistantionService(DistantionRepository distantionRepository) {

        this.distantionRepository = distantionRepository;
    }

    /**
     * 
     * @return
     */
    public Iterable<Distantion> findAll() {

//        return distantionRepository.findAll();
    return null;
    }


    public Distantion save(Distantion distantion) {

//        return distantionRepository.save(distantion);
    return null;
    }
}
