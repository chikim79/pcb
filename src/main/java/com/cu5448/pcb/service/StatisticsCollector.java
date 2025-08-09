package com.cu5448.pcb.service;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * Observer Pattern Implementation as Spring Service using Lombok
 * 
 * @Getter generates getter methods for all fields
 * This service observes events from stations during PCB processing.
 * Uses prototype scope to create new instances for each simulation run.
 */
@Service
@Scope("prototype")
@Getter
public class StatisticsCollector {
    private int pcbsSubmitted;
    private final Map<String, Integer> defectFailures;
    private final Map<String, Integer> stationFailures;
    private int completedPCBs;

    public StatisticsCollector() {
        this.pcbsSubmitted = 0;
        this.defectFailures = new HashMap<>();
        this.stationFailures = new HashMap<>();
        this.completedPCBs = 0;
    }

    public void recordSubmission() {
        pcbsSubmitted++;
    }

    public void recordDefectFailure(String station) {
        defectFailures.merge(station, 1, Integer::sum);
    }

    public void recordStationFailure(String station) {
        stationFailures.merge(station, 1, Integer::sum);
    }

    public void recordCompletion() {
        completedPCBs++;
    }

    public String generateReport(String pcbType) {
        StringBuilder report = new StringBuilder();
        
        // Format according to project specification
        report.append(String.format("PCB type: %s\n", pcbType));
        report.append(String.format("PCBs run: %d\n", pcbsSubmitted));
        
        report.append("\nStation Failures\n");
        // Show all stations in assembly order
        String[] stationNames = {
            "Apply Solder Paste", "Place Components", "Reflow Solder", 
            "Optical Inspection", "Hand Soldering/Assembly", "Cleaning", 
            "Depanelization", "Test (ICT or Flying Probe)"
        };
        
        String[] stationKeys = {
            "ApplySolderPaste", "PlaceComponents", "ReflowSolder",
            "OpticalInspection", "HandSoldering", "Cleaning",
            "Depanelization", "Test"
        };
        
        for (int i = 0; i < stationNames.length; i++) {
            int failures = stationFailures.getOrDefault(stationKeys[i], 0);
            report.append(String.format("%s: %d\n", stationNames[i], failures));
        }
        
        report.append("\nPCB Defect Failures\n");
        // Only show defect-detecting stations
        String[] defectStationNames = {
            "Place Components", "Optical Inspection", 
            "Hand Soldering/Assembly", "Test (ICT or Flying Probe)"
        };
        String[] defectStationKeys = {
            "PlaceComponents", "OpticalInspection", 
            "HandSoldering", "Test"
        };
        
        for (int i = 0; i < defectStationNames.length; i++) {
            int failures = defectFailures.getOrDefault(defectStationKeys[i], 0);
            report.append(String.format("%s %d\n", defectStationNames[i], failures));
        }
        
        // Calculate total failures and successful PCBs
        int totalFailed = pcbsSubmitted - completedPCBs;
        
        report.append("\nFinal Results\n");
        report.append(String.format("Total failed PCBs: %d\n", totalFailed));
        report.append(String.format("Total PCBs produced: %d\n", completedPCBs));
        
        return report.toString();
    }
}