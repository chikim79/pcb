package com.cu5448.pcb.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.cu5448.pcb.entity.SimulationResult;
import com.cu5448.pcb.repository.SimulationResultRepository;
import com.cu5448.pcb.service.AssemblyLine;
import com.cu5448.pcb.service.StatisticsCollector;

import lombok.RequiredArgsConstructor;

/**
 * Main Simulation Controller using Spring Dependency Injection and Lombok @RequiredArgsConstructor
 * generates constructor for final fields. This controller orchestrates PCB simulations and manages
 * results storage in SQLite database via JPA for REST API access.
 *
 * <p>Supports the server-side requirement: run simulations to gather failure results and persist
 * them to database, then wait for client API calls to retrieve the stored simulation results.
 */
@Component
@RequiredArgsConstructor
public class SimulationController {

    private final AssemblyLine assemblyLine;
    private final SimulationResultRepository simulationResultRepository;

    /**
     * Run simulation for a specific PCB type asynchronously and persist results to database.
     *
     * @param pcbType the type of PCB to simulate
     * @param quantity number of PCBs to process
     * @return CompletableFuture with the simulation result entity
     */
    @Async
    public CompletableFuture<SimulationResult> runSimulationAsync(String pcbType, int quantity) {
        StatisticsCollector stats = assemblyLine.runSimulation(pcbType, quantity);
        SimulationResult result = createAndSaveSimulationResult(stats, pcbType, quantity);
        return CompletableFuture.completedFuture(result);
    }

    /**
     * Run simulation for a specific PCB type synchronously and persist results to database.
     *
     * @param pcbType the type of PCB to simulate
     * @param quantity number of PCBs to process
     * @return the simulation result entity
     */
    public SimulationResult runSimulation(String pcbType, int quantity) {
        StatisticsCollector stats = assemblyLine.runSimulation(pcbType, quantity);
        return createAndSaveSimulationResult(stats, pcbType, quantity);
    }

    /**
     * Run simulation for a specific PCB type with default quantity (1000).
     *
     * @param pcbType the type of PCB to simulate
     * @return the simulation result entity
     */
    public SimulationResult runSimulation(String pcbType) {
        return runSimulation(pcbType, 1000);
    }

    /**
     * Helper method to create and save SimulationResult from StatisticsCollector.
     *
     * @param stats the statistics collector with simulation data
     * @param pcbType the PCB type that was simulated
     * @param quantity the quantity of PCBs processed
     * @return the saved simulation result entity
     */
    private SimulationResult createAndSaveSimulationResult(
            StatisticsCollector stats, String pcbType, int quantity) {
        SimulationResult result =
                new SimulationResult(
                        pcbType,
                        stats.getPcbsSubmitted(),
                        stats.getCompletedPCBs(),
                        stats.getStationFailures(),
                        stats.getDefectFailures(),
                        stats.generateReport(pcbType),
                        quantity);
        return simulationResultRepository.save(result);
    }

    /**
     * Run simulations for all three PCB types asynchronously and persist results to database.
     *
     * @return CompletableFuture that completes when all simulations are done
     */
    @Async
    public CompletableFuture<List<SimulationResult>> runAllSimulationsAsync() {
        SimulationResult testResult = runSimulation("test");
        SimulationResult sensorResult = runSimulation("sensor");
        SimulationResult gatewayResult = runSimulation("gateway");
        return CompletableFuture.completedFuture(List.of(testResult, sensorResult, gatewayResult));
    }

    /**
     * Run simulations for all three PCB types synchronously and persist results to database.
     *
     * @return list of all simulation results
     */
    public List<SimulationResult> runAllSimulations() {
        SimulationResult testResult = runSimulation("test");
        SimulationResult sensorResult = runSimulation("sensor");
        SimulationResult gatewayResult = runSimulation("gateway");
        return List.of(testResult, sensorResult, gatewayResult);
    }

    /**
     * Retrieve the most recent simulation result for a specific PCB type.
     *
     * @param pcbType the type of PCB to get results for
     * @return the most recent simulation result, or null if not found
     */
    public SimulationResult getSimulationResults(String pcbType) {
        return simulationResultRepository
                .findFirstByPcbTypeOrderByCreatedAtDesc(pcbType)
                .orElse(null);
    }

    /**
     * Retrieve the most recent simulation result for each PCB type.
     *
     * @return list of the latest simulation results for each PCB type
     */
    public List<SimulationResult> getAllSimulationResults() {
        return simulationResultRepository.findLatestResultForEachPcbType();
    }

    /**
     * Retrieve all simulation results for a specific PCB type (historical data).
     *
     * @param pcbType the type of PCB to get all results for
     * @return list of all simulation results for the given PCB type
     */
    public List<SimulationResult> getAllResultsForPcbType(String pcbType) {
        return simulationResultRepository.findByPcbTypeOrderByCreatedAtDesc(pcbType);
    }

    /**
     * Check if simulation results exist for a specific PCB type.
     *
     * @param pcbType the type of PCB to check
     * @return true if results exist, false otherwise
     */
    public boolean hasSimulationResults(String pcbType) {
        return simulationResultRepository.existsByPcbType(pcbType);
    }

    /** Clear all stored simulation results from database. */
    public void clearAllResults() {
        simulationResultRepository.deleteAll();
    }

    /**
     * Clear all simulation results for a specific PCB type.
     *
     * @param pcbType the type of PCB to clear results for
     * @return number of deleted records
     */
    public long clearResultsForPcbType(String pcbType) {
        return simulationResultRepository.deleteByPcbType(pcbType);
    }

    /**
     * Print results to console (for debugging/testing purposes).
     *
     * @param pcbType the type of PCB to print results for
     */
    public void printResults(String pcbType) {
        SimulationResult result = getSimulationResults(pcbType);
        if (result != null) {
            System.out.println(result.getFormattedReport());
        } else {
            System.out.printf("No results found for PCB type: %s\n", pcbType);
        }
    }
}
