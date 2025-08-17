package com.cu5448.pcb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Main Spring Boot Application with Configuration Properties Support
 *
 * <p>Demonstrates Dependency Injection design pattern implementation:
 * - @EnableConfigurationProperties enables property-driven configuration - REST API endpoints
 * available for running simulations on demand - All dependencies managed by Spring IoC container
 *
 * <p>Server starts and waits for client API calls to run simulations and retrieve results.
 */
@SpringBootApplication
@EnableConfigurationProperties
public class PcbApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcbApplication.class, args);
    }
}
