package com.cu5448.pcb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Station Configuration Properties using Lombok
 * 
 * @Data generates all necessary boilerplate code
 */
@Data
@Component
@ConfigurationProperties(prefix = "station")
public class StationProperties {
    private double failureRate = 0.002;
}