package com.cu5448.pcb.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cu5448.pcb.factory.StationFactory;
import com.cu5448.pcb.station.*;

import lombok.RequiredArgsConstructor;

/**
 * Spring Configuration Class using Abstract Factory Pattern for PCB Assembly Line Stations. The
 * configuration delegates station creation to a StationFactory, maintaining the factory pattern
 * while leveraging Spring's dependency injection.
 */
@Configuration
@RequiredArgsConstructor
public class PCBSimulationConfig {

    private final StationFactory stationFactory;

    /**
     * Creates ordered list of stations for the assembly line using the factory. This ensures
     * consistent station creation and proper manufacturing process flow.
     */
    @Bean
    public List<Station> createAssemblyLineStations() {
        return List.of(
                stationFactory.createApplySolderPasteStation(),
                stationFactory.createPlaceComponentsStation(),
                stationFactory.createReflowSolderStation(),
                stationFactory.createOpticalInspectionStation(),
                stationFactory.createHandSolderingStation(),
                stationFactory.createCleaningStation(),
                stationFactory.createDepanelizationStation(),
                stationFactory.createTestStation());
    }
}
