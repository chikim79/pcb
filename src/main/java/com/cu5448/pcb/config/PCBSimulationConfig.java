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
     * Creates ordered list of stations for the assembly line using the abstract factory pattern.
     * This ensures consistent station creation and proper manufacturing process flow.
     */
    @Bean
    public List<Station> createAssemblyLineStations() {
        return List.of(
                stationFactory.createStation("ApplySolderPaste"),
                stationFactory.createStation("PlaceComponents"),
                stationFactory.createStation("ReflowSolder"),
                stationFactory.createStation("OpticalInspection"),
                stationFactory.createStation("HandSoldering"),
                stationFactory.createStation("Cleaning"),
                stationFactory.createStation("Depanelization"),
                stationFactory.createStation("Test"));
    }
}
