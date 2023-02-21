package com.retailer.points.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@Endpoint(id = "custom-health")
public class CustomActuatorEndpoint implements HealthIndicator {

    private boolean status = false;
    private Date date;

    @ReadOperation
    public boolean getStatus() {
        return status;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setStatus() {
        status = true;
        date = new Date();
    }

    @Override
    public Health health() {
        Status healthStatus = status ? Status.UP : Status.DOWN;
        return Health.status(healthStatus).withDetail("startTime", date).build();
    }
}
