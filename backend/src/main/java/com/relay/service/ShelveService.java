package com.relay.service;

import com.relay.model.shelves.Shelve;
import com.relay.repository.ShelveRepository;

public class ShelveService {

    private ShelveRepository shelveRepository;

    public ShelveService(ShelveRepository shelveRepository) {

        this.shelveRepository = shelveRepository;
    }

    public Shelve save(Shelve shelve) {

        return shelveRepository.save(shelve);
    }
}
