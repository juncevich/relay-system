package com.relay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories("com.relay.repository")
@Configuration
public class ReactiveMongoConfig
// extends AbstractReactiveMongoConfiguration
{

    // @Value("${spring.data.mongodb.database}")
    // String mongoUri;
    //
    // @Override
    // public MongoClient reactiveMongoClient() {
    // return MongoClients.create(mongoUri);
    // }
    //
    // @Override
    // protected String getDatabaseName() {
    // return "jsa_mongodb";
    // }
}
