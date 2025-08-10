package com.cu5448.pcb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.cu5448.pcb.config.PCBSimulationConfig;
import com.cu5448.pcb.config.SimulationProperties;
import com.cu5448.pcb.factory.PCBFactory;
import com.cu5448.pcb.model.PCB;
import com.cu5448.pcb.station.*;

import lombok.RequiredArgsConstructor;

/**
 * Assembly Line Service using Spring Dependency Injection and Lombok @RequiredArgsConstructor
 * generates constructor for all final fields This service coordinates the PCB manufacturing process
 * through all stations.
 */
@Service
@RequiredArgsConstructor
public class AssemblyLine {

    private final List<Station> stations = new ArrayList<>();

    private final PCBFactory factory;

    private final SimulationProperties simulationProperties;

    private final ApplicationContext applicationContext;

    private final PCBSimulationConfig config;

    private void initializeStations(StatisticsCollector stats) {
        stations.clear();
        // Create stations with configuration-driven settings and specific
        // StatisticsCollector
        stations.add(config.createApplySolderPasteStation(stats));
        stations.add(config.createPlaceComponentsStation(stats));
        stations.add(config.createReflowSolderStation(stats));
        stations.add(config.createOpticalInspectionStation(stats));
        stations.add(config.createHandSolderingStation(stats));
        stations.add(config.createCleaningStation(stats));
        stations.add(config.createDepanelizationStation(stats));
        stations.add(config.createTestStation(stats));
    }

    public void processPCB(PCB pcb) {
        for (Station station : stations) {
            station.process(pcb);
            if (pcb.isFailed()) {
                break;
            }
        }
    }

    public StatisticsCollector runSimulation(String pcbType, int quantity) {
        // Get a new prototype instance of StatisticsCollector for this simulation
        StatisticsCollector stats = applicationContext.getBean(StatisticsCollector.class);
        initializeStations(stats);

        for (int i = 0; i < quantity; i++) {
            PCB pcb = factory.createPCB(pcbType);
            stats.recordSubmission();

            processPCB(pcb);

            if (!pcb.isFailed()) {
                stats.recordCompletion();
            }
        }

        return stats;
    }

    public StatisticsCollector runSimulation(String pcbType) {
        return runSimulation(pcbType, simulationProperties.getPcbQuantity());
    }

    public List<Station> getStations() {
        return new ArrayList<>(stations);
    }
}
