package com.cu5448.pcb.api;

import java.util.ArrayList;
import java.util.List;


import com.cu5448.pcb.dto.SimulationReportDto;
import com.cu5448.pcb.dto.SimulationReportMapper;
import com.cu5448.pcb.service.AssemblyLine;
import com.cu5448.pcb.service.StatisticsCollector;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST API Controller for PCB simulation operations. Provides endpoints for running simulations and
 * retrieving results in JSON format for client applications.
 */
@RestController
@RequestMapping("/api/simulation")
@RequiredArgsConstructor
public class SimulationApiController {

    private final AssemblyLine assemblyLine;
    private final SimulationReportMapper reportMapper;

    /**
     * Run simulation for a specific PCB type.
     *
     * @param pcbType the type of PCB to simulate (test, sensor, gateway)
     * @param quantity number of PCBs to process (default: 1000)
     * @return simulation report as JSON
     */
    @GetMapping("/run/{pcbType}")
    public ResponseEntity<SimulationReportDto> runSimulation(
            @PathVariable String pcbType, @RequestParam(defaultValue = "1000") int quantity) {

        StatisticsCollector stats = assemblyLine.runSimulation(pcbType, quantity);
        SimulationReportDto report = reportMapper.toDto(stats, pcbType);
        return ResponseEntity.ok(report);
    }

    /**
     * Run simulations for all three PCB types.
     *
     * @param quantity number of PCBs to process for each type (default: 1000)
     * @return list of simulation reports for all PCB types
     */
    @GetMapping("/run/all")
    public ResponseEntity<List<SimulationReportDto>> runAllSimulations(
            @RequestParam(defaultValue = "1000") int quantity) {

        List<SimulationReportDto> reports = new ArrayList<>();
        String[] pcbTypes = {"Test Board", "Sensor Board", "Gateway Board"};

        for (String pcbType : pcbTypes) {
                StatisticsCollector stats = assemblyLine.runSimulation(pcbType, quantity);
                SimulationReportDto report = reportMapper.toDto(stats, pcbType);
                reports.add(report);
        }

        return ResponseEntity.ok(reports);
    }
}
