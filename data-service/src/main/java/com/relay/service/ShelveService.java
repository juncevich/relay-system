package com.relay.service;

import com.relay.web.model.shelves.Shelve;
import com.relay.db.repository.ShelveRepository;

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
