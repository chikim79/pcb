package com.cu5448.pcb;

import com.cu5448.pcb.controller.SimulationController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Main Spring Boot Application with Configuration Properties Support
 * 
 * Demonstrates Dependency Injection design pattern implementation:
 * - @EnableConfigurationProperties enables property-driven configuration
 * - CommandLineRunner provides automatic simulation execution
 * - All dependencies managed by Spring IoC container
 */
@SpringBootApplication
@EnableConfigurationProperties
public class PcbApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcbApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(SimulationController controller) {
		return args -> {
			controller.runAllSimulations();
		};
	}
}
