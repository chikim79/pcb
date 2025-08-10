package com.cu5448.pcb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cu5448.pcb.config.SimulationProperties;
import com.cu5448.pcb.service.AssemblyLine;
import com.cu5448.pcb.service.StatisticsCollector;

import lombok.RequiredArgsConstructor;

/**
 * Main Simulation Controller using Spring Dependency Injection and Lombok @RequiredArgsConstructor
 * generates constructor for final fields This controller orchestrates PCB simulations and manages
 * results.
 */
@Component
@RequiredArgsConstructor
public class SimulationController {

    private final AssemblyLine assemblyLine;

    private final SimulationProperties simulationProperties;

    private final Map<String, StatisticsCollector> results = new HashMap<>();

    public void runSimulation(String pcbType, int quantity) {
        StatisticsCollector stats = assemblyLine.runSimulation(pcbType, quantity);
        results.put(pcbType, stats);
        printResults(pcbType);
    }

    public void runSimulation(String pcbType) {
        runSimulation(pcbType, simulationProperties.getPcbQuantity());
    }

    public void runAllSimulations() {
        // Run simulations for all three PCB types using properties configuration
        runSimulation("Test Board");
        System.out.println();
        runSimulation("Sensor Board");
        System.out.println();
        runSimulation("Gateway Board");
    }

    public void printResults(String pcbType) {
        StatisticsCollector stats = results.get(pcbType);
        if (stats != null) {
            System.out.println(stats.generateReport(pcbType));
        } else {
            System.out.printf("No results found for PCB type: %s\n", pcbType);
        }
    }

    public StatisticsCollector getResults(String pcbType) {
        return results.get(pcbType);
    }

    public Map<String, StatisticsCollector> getAllResults() {
        return new HashMap<>(results);
    }
}
