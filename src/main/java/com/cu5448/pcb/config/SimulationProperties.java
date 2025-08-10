package com.cu5448.pcb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Simulation Configuration Properties using Lombok @Data generates getters, setters, toString,
 * equals, and hashCode
 */
@Data
@Component
@ConfigurationProperties(prefix = "simulation")
public class SimulationProperties {

    private int pcbQuantity = 1000;
}
