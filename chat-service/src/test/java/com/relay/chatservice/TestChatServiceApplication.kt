package com.relay.chatservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.util.function.ThrowingConsumer
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestChatServiceApplication {
    //    @Bean
    //    @ServiceConnection
    //    PostgreSQLContainer<?> postgresContainer() {
    //        return new PostgreSQLContainer<>("postgres:latest");
    //    }
    //    @Bean
    //    @ServiceConnection(name = "redis")
    //    GenericContainer<?> redisContainer() {
    //        return new GenericContainer<>("redis:latest").withExposedPorts(6379);
    //    }
    //
    //    @Bean
    //    @ServiceConnection(name = "openzipkin/zipkin")
    //    GenericContainer<?> zipkinContainer() {
    //        return new GenericContainer<>("openzipkin/zipkin:latest").withExposedPorts(9411);
    //    }
    @Bean
    @ServiceConnection
    fun mongoDBContainer(): MongoDBContainer? {
        val env = HashMap<String?, String?>()
        //        env.put("MONGO_INITDB_ROOT_USERNAME", "alex");
//        env.put("MONGO_INITDB_ROOT_PASSWORD", "Password12345");
        env.put("MONGO_DB", "test")
        env.put("MONGO_INITDB_DATABASE", "test")
        return MongoDBContainer(DockerImageName.parse("mongo:latest")).withEnv(env).withExposedPorts(27017)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.from(ThrowingConsumer { obj: Array<String?>? -> obj.main() })
                .with(TestChatServiceApplication::class.java).run(*args)
        }
    }
}
