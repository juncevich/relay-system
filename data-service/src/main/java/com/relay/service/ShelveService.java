package com.relay.service;

import com.relay.db.repository.ShelveRepository;
import com.relay.web.model.shelves.Shelve;

import javax.transaction.Transactional;

@Transactional
class ShelveService {

    private final ShelveRepository shelveRepository;

    public ShelveService(ShelveRepository shelveRepository) {

        this.shelveRepository = shelveRepository;
    }

    public Shelve save(Shelve shelve) {

//        return shelveRepository.save(shelve);
        return null;
    }
}
