package com.cu5448.pcb.config;

import org.springframework.context.annotation.Configuration;

import com.cu5448.pcb.service.StatisticsCollector;
import com.cu5448.pcb.station.*;

import lombok.RequiredArgsConstructor;

/**
 * Spring Configuration Class implementing Dependency Injection Design Pattern using
 * Lombok @RequiredArgsConstructor generates constructor for final fields, enabling constructor
 * injection This configuration class demonstrates the Dependency Injection pattern by: 1.
 * Centralizing bean creation and wiring 2. Managing dependencies between components 3. Enabling
 * Spring IoC container to manage object lifecycle
 */
@Configuration
@RequiredArgsConstructor
public class PCBSimulationConfig {

    private final StationProperties stationProperties;

    /**
     * Factory Pattern Implementation - Creates PCB instances PCBFactory is already annotated
     * with @Component, so this is optional
     */

    /**
     * Station Factory Methods - Create stations with configured failure rates These are not @Bean
     * methods since stations need different StatisticsCollector instances per simulation
     */
    public ApplySolderPasteStation createApplySolderPasteStation(StatisticsCollector stats) {
        return new ApplySolderPasteStation(stationProperties.getFailureRate(), stats);
    }

    public PlaceComponentsStation createPlaceComponentsStation(StatisticsCollector stats) {
        return new PlaceComponentsStation(stationProperties.getFailureRate(), stats);
    }

    public ReflowSolderStation createReflowSolderStation(StatisticsCollector stats) {
        return new ReflowSolderStation(stationProperties.getFailureRate(), stats);
    }

    public OpticalInspectionStation createOpticalInspectionStation(StatisticsCollector stats) {
        return new OpticalInspectionStation(stationProperties.getFailureRate(), stats);
    }

    public HandSolderingStation createHandSolderingStation(StatisticsCollector stats) {
        return new HandSolderingStation(stationProperties.getFailureRate(), stats);
    }

    public CleaningStation createCleaningStation(StatisticsCollector stats) {
        return new CleaningStation(stationProperties.getFailureRate(), stats);
    }

    public DepanelizationStation createDepanelizationStation(StatisticsCollector stats) {
        return new DepanelizationStation(stationProperties.getFailureRate(), stats);
    }

    public TestStation createTestStation(StatisticsCollector stats) {
        return new TestStation(stationProperties.getFailureRate(), stats);
    }
}
