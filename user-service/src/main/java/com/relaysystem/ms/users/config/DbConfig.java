package com.relaysystem.ms.users.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableConfigurationProperties({DataSourceProperties.class})
public class DbConfig {


    private final DataSourceProperties dsProperties;
    private final DiscoveryClient      discoveryClient;

    @Value("${sidecar.appName:sidecar_postgres}")
    private String dbServiceName;

    public DbConfig(DataSourceProperties dsProperties, DiscoveryClient discoveryClient) {
        this.dsProperties = dsProperties;
        this.discoveryClient = discoveryClient;
    }

    @Bean
    public DataSource dataSource() {
        // Assumes Postgres is running before this app starts.
        log.info("Attempting to retrieve service metadata for '{}'", this.dbServiceName);
        ServiceInstance instance = this.discoveryClient.getInstances(this.dbServiceName).iterator().next();
        log.info("Found instance=[host={}, port={}, serviceId={}]",
                instance.getHost(), instance.getPort(), instance.getServiceId());
        return this.createDataSource(instance.getHost(), instance.getPort());
    }

    private DataSource createDataSource(String host, int port) {
        String jdbcUrl = String.format(this.dsProperties.getUrl(), host, port);
        log.info("Attempting connection using jdbc url '{}' ", jdbcUrl);

        DataSourceBuilder factory = DataSourceBuilder
                .create()
                .url(jdbcUrl)
                .username(this.dsProperties.getUsername())
                .password(this.dsProperties.getPassword())
                .driverClassName(this.dsProperties.getDriverClassName());
        return factory.build();
    }
}
