package com.relay.service;

import com.relay.db.repository.DistantionRepository;
import com.relay.web.model.Distantion;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

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
     * @return
     */
    public List<Distantion> findAll() {

//        return Lists.newArrayList(distantionRepository.findAll());
        return Collections.EMPTY_LIST;
    }


    public Distantion save(Distantion distantion) {

//        return distantionRepository.save(distantion);
    return null;
    }
}
