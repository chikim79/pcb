package com.cu5448.pcb.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cu5448.pcb.station.*;

import lombok.RequiredArgsConstructor;

/**
 * Spring Configuration Class for PCB Assembly Line Stations. Creates all manufacturing stations as
 * Spring beans for dependency injection into AssemblyLine.
 */
@Configuration
@RequiredArgsConstructor
public class PCBSimulationConfig {

    private final StationProperties stationProperties;

    @Bean
    public ApplySolderPasteStation applySolderPasteStation() {
        return new ApplySolderPasteStation(stationProperties.getFailureRate());
    }

    @Bean
    public PlaceComponentsStation placeComponentsStation() {
        return new PlaceComponentsStation(stationProperties.getFailureRate());
    }

    @Bean
    public ReflowSolderStation reflowSolderStation() {
        return new ReflowSolderStation(stationProperties.getFailureRate());
    }

    @Bean
    public OpticalInspectionStation opticalInspectionStation() {
        return new OpticalInspectionStation(stationProperties.getFailureRate());
    }

    @Bean
    public HandSolderingStation handSolderingStation() {
        return new HandSolderingStation(stationProperties.getFailureRate());
    }

    @Bean
    public CleaningStation cleaningStation() {
        return new CleaningStation(stationProperties.getFailureRate());
    }

    @Bean
    public DepanelizationStation depanelizationStation() {
        return new DepanelizationStation(stationProperties.getFailureRate());
    }

    @Bean
    public TestStation testStation() {
        return new TestStation(stationProperties.getFailureRate());
    }

    /**
     * Creates ordered list of stations for the assembly line. Order matches the manufacturing
     * process flow.
     */
    @Bean
    public List<Station> assemblyLineStations(
            ApplySolderPasteStation applySolderPasteStation,
            PlaceComponentsStation placeComponentsStation,
            ReflowSolderStation reflowSolderStation,
            OpticalInspectionStation opticalInspectionStation,
            HandSolderingStation handSolderingStation,
            CleaningStation cleaningStation,
            DepanelizationStation depanelizationStation,
            TestStation testStation) {

        return List.of(
                applySolderPasteStation,
                placeComponentsStation,
                reflowSolderStation,
                opticalInspectionStation,
                handSolderingStation,
                cleaningStation,
                depanelizationStation,
                testStation);
    }
}
