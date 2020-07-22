package com.relaysystem.ms.sidecar.postgres.healthcheck.postgres;

import com.relaysystem.ms.sidecar.postgres.healthcheck.SidecarHealthIndicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class PostgresHealthCheck implements SidecarHealthIndicator {


    // pg_isready -U <user> -h localhost -p <sidecarPort>
    private static final String COMMAND_PATTERN      = "pg_isready -U %s -h localhost -p %s";
    private static final String TEMP_COMMAND_PATTERN = "docker exec -i 272 pg_isready -U %s -h localhost -p %s";

    @Value("${sidecar.port}")
    private int sidecarPort;

    @Value("${postgres.user-name}")
    private String sidecarUser;

    @Override
    public Health health() {
        Health.Builder result;
        try {
            String output = this.runCommand();
            log.info(output);
            if (output.contains("accepting connections")) {
                result = Health.up();
            } else if (output.contains("rejecting connections") || output.contains("no response")) {
                result = Health.down().withDetail("reason", output);
            } else {
                result = Health.down().withDetail("reason", output);
            }
        } catch (Exception e) {
            log.warn("Failed to execute command.", e);
            result = Health.down().withException(e);
        }
        return result.build();
    }

    private String runCommand() throws IOException {
        Process process = Runtime.getRuntime().exec(String.format(COMMAND_PATTERN, sidecarUser, sidecarPort));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            var    output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
                output.append(System.getProperty("line.separator"));
            }
            return output.toString();
        }
    }
}
