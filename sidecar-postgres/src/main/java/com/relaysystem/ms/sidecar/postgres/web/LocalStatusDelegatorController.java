package com.relaysystem.ms.sidecar.postgres.web;

import com.relaysystem.ms.sidecar.postgres.healthcheck.SidecarHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalStatusDelegatorController {

    private final SidecarHealthIndicator healthIndicator;

    public LocalStatusDelegatorController(SidecarHealthIndicator healthIndicator) {
        this.healthIndicator = healthIndicator;
    }

    @RequestMapping("/delegating-status")
    public Health sidecarHealthStatus() {
        return this.healthIndicator.health();
    }
}
