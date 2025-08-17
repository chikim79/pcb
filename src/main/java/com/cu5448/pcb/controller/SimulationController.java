package com.cu5448.pcb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cu5448.pcb.service.AssemblyLine;
import com.cu5448.pcb.service.StatisticsCollector;

import lombok.RequiredArgsConstructor;

/**
 * Main Simulation Controller using Spring Dependency Injection and Lombok @RequiredArgsConstructor
 * generates constructor for final fields. This controller orchestrates PCB simulations and manages
 * results storage for REST API access.
 *
 * <p>Supports the server-side requirement: run simulations to gather failure results in memory,
 * then wait for client API calls to retrieve the stored simulation results.
 */
@Component
@RequiredArgsConstructor
public class SimulationController {

    private final AssemblyLine assemblyLine;

    private final Map<String, StatisticsCollector> results = new HashMap<>();

    /**
     * Run simulation for a specific PCB type and store results in memory.
     *
     * @param pcbType the type of PCB to simulate
     * @param quantity number of PCBs to process
     * @return the statistics collector with simulation results
     */
    public StatisticsCollector runSimulation(String pcbType, int quantity) {
        StatisticsCollector stats = assemblyLine.runSimulation(pcbType, quantity);
        results.put(pcbType, stats);
        return stats;
    }

    /**
     * Run simulation for a specific PCB type with default quantity (1000).
     *
     * @param pcbType the type of PCB to simulate
     * @return the statistics collector with simulation results
     */
    public StatisticsCollector runSimulation(String pcbType) {
        return runSimulation(pcbType, 1000);
    }

    /**
     * Run simulations for all three PCB types and store results in memory.
     *
     * @return map of all simulation results by PCB type
     */
    public Map<String, StatisticsCollector> runAllSimulations() {
        runSimulation("Test Board");
        runSimulation("Sensor Board");
        runSimulation("Gateway Board");
        return new HashMap<>(results);
    }

    /**
     * Retrieve stored simulation results for a specific PCB type.
     *
     * @param pcbType the type of PCB to get results for
     * @return the statistics collector with results, or null if not found
     */
    public StatisticsCollector getSimulationResults(String pcbType) {
        return results.get(pcbType);
    }

    /**
     * Retrieve all stored simulation results.
     *
     * @return map of all simulation results by PCB type
     */
    public Map<String, StatisticsCollector> getAllSimulationResults() {
        return new HashMap<>(results);
    }

    /**
     * Check if simulation results exist for a specific PCB type.
     *
     * @param pcbType the type of PCB to check
     * @return true if results exist, false otherwise
     */
    public boolean hasSimulationResults(String pcbType) {
        return results.containsKey(pcbType);
    }

    /** Clear all stored simulation results. */
    public void clearAllResults() {
        results.clear();
    }

    /**
     * Print results to console (for debugging/testing purposes).
     *
     * @param pcbType the type of PCB to print results for
     */
    public void printResults(String pcbType) {
        StatisticsCollector stats = results.get(pcbType);
        if (stats != null) {
            System.out.println(stats.generateReport(pcbType));
        } else {
            System.out.printf("No results found for PCB type: %s\n", pcbType);
        }
    }
}
