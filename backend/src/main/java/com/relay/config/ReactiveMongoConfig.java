package com.relay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableReactiveMongoRepositories("com.relay.repository")
public class ReactiveMongoConfig {

    // @Override
    // public MongoClient mongoClient() {
    //
    // return new MongoClient("localhost");
    // }
    //
    // @Override
    // protected String getDatabaseName() {
    //
    // return "jsa_mongodb";
    // }
    //
    // @Override
    // protected Collection<String> getMappingBasePackages() {
    //
    // return List.of("com.relay.model");
    // }
}
