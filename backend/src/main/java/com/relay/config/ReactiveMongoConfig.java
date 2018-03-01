package com.relay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories("com.relay.repository")
@Configuration
public class ReactiveMongoConfig
{

}
