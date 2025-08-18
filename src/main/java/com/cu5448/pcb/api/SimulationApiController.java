package com.cu5448.pcb.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cu5448.pcb.controller.SimulationController;
import com.cu5448.pcb.dto.SimulationReportDto;
import com.cu5448.pcb.dto.SimulationReportMapper;
import com.cu5448.pcb.service.StatisticsCollector;

import lombok.RequiredArgsConstructor;

/**
 * REST API Controller for PCB simulation operations. Implements the server-side requirement: run
 * simulations to gather failure results in memory, then wait for client API calls to retrieve the
 * stored simulation results as JSON.
 *
 * <p>Provides separate endpoints for running simulations and retrieving stored results, ensuring
 * results are maintained in memory between API calls.
 */
@RestController
@RequestMapping("/api/simulation")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class SimulationApiController {

    private final SimulationController simulationController;
    private final SimulationReportMapper reportMapper;

    /**
     * Start simulation for a specific PCB type asynchronously and store results in memory.
     *
     * @param pcbType the type of PCB to simulate (test, sensor, gateway)
     * @param quantity number of PCBs to process (default: 1000)
     * @return 201 Created status to indicate simulation started
     */
    @PostMapping("/run/{pcbType}")
    public ResponseEntity<String> runSimulation(
            @PathVariable String pcbType, @RequestParam(defaultValue = "1000") int quantity) {

        // Start simulation asynchronously using @Async method
        simulationController.runSimulationAsync(pcbType, quantity);

        return ResponseEntity.status(201)
                .body(
                        String.format(
                                "Simulation started asynchronously for PCB type: %s with quantity: %d",
                                pcbType, quantity));
    }

    /**
     * Start simulations for all three PCB types asynchronously and store results in memory.
     *
     * @param quantity number of PCBs to process for each type (default: 1000)
     * @return 201 Created status to indicate simulations started
     */
    @PostMapping("/run/all")
    public ResponseEntity<String> runAllSimulations(
            @RequestParam(defaultValue = "1000") int quantity) {

        // Start all simulations asynchronously using @Async method
        simulationController.runAllSimulationsAsync();

        return ResponseEntity.status(201)
                .body(
                        String.format(
                                "Simulations started asynchronously for all PCB types with quantity: %d",
                                quantity));
    }

    /**
     * Retrieve stored simulation results for a specific PCB type.
     *
     * @param pcbType the type of PCB to get results for
     * @return simulation report as JSON, or 404 if not found
     */
    @GetMapping("/results/{pcbType}")
    public ResponseEntity<SimulationReportDto> getSimulationResults(@PathVariable String pcbType) {
        StatisticsCollector stats = simulationController.getSimulationResults(pcbType);

        if (stats == null) {
            return ResponseEntity.notFound().build();
        }

        SimulationReportDto report = reportMapper.toDto(stats, pcbType);
        return ResponseEntity.ok(report);
    }

    /**
     * Retrieve all stored simulation results.
     *
     * @return list of simulation reports for all stored results
     */
    @GetMapping("/results/all")
    public ResponseEntity<List<SimulationReportDto>> getAllSimulationResults() {
        Map<String, StatisticsCollector> allResults =
                simulationController.getAllSimulationResults();

        if (allResults.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<SimulationReportDto> reports = new ArrayList<>();
        for (Map.Entry<String, StatisticsCollector> entry : allResults.entrySet()) {
            SimulationReportDto report = reportMapper.toDto(entry.getValue(), entry.getKey());
            reports.add(report);
        }

        return ResponseEntity.ok(reports);
    }

    /**
     * Get available PCB types.
     *
     * @return list of supported PCB types
     */
    @GetMapping("/types")
    public ResponseEntity<List<String>> getPcbTypes() {
        List<String> types = List.of("Test Board", "Sensor Board", "Gateway Board");
        return ResponseEntity.ok(types);
    }

    /**
     * Clear all stored simulation results.
     *
     * @return success response
     */
    @DeleteMapping("/results")
    public ResponseEntity<String> clearAllResults() {
        simulationController.clearAllResults();
        return ResponseEntity.ok("All simulation results cleared");
    }
}
