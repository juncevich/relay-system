package com.relaysystem.ms.historyservice.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.relaysystem.ms.historyservice.entities.Change;

public interface ChangesReactiveReposirory extends ReactiveMongoRepository<Change, String> {

}
