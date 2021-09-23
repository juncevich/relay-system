package com.relaysystem.ms.historyservice.db.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.relaysystem.ms.historyservice.db.enums.Event;

public interface ChangesEventReactiveRepository
        extends ReactiveMongoRepository<Event, String> {

}
