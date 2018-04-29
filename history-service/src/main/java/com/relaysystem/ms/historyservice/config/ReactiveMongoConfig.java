package com.relaysystem.ms.historyservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableReactiveMongoRepositories("com.relaysystem.ms.historyservice.repositories")
public class ReactiveMongoConfig {

}
