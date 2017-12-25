package com.relaysystem.ms.historyservice.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.relaysystem.ms.historyservice.entities.ChangeEvent;

public interface ChangesEventReactiveRepository
        extends ReactiveMongoRepository<ChangeEvent, String> {

}
