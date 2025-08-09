package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;
import com.cu5448.pcb.service.StatisticsCollector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Random;

/**
 * Abstract Station class using Lombok
 * 
 * @Getter generates getter methods for all fields
 * @RequiredArgsConstructor would generate constructor, but we need custom logic
 */
@Getter
public abstract class Station {
    protected final String name;
    protected final double stationFailureRate;
    protected final StatisticsCollector stats;
    protected final Random random;

    public Station(String name, double failureRate, StatisticsCollector stats) {
        this.name = name;
        this.stationFailureRate = failureRate;
        this.stats = stats;
        this.random = new Random();
    }

    public void process(PCB pcb) {
        if (pcb.isFailed()) {
            return;
        }

        if (checkStationFailure()) {
            stats.recordStationFailure(name);
            pcb.setFailed("Station failure at " + name);
            return;
        }

        boolean operationSuccessful = performOperation(pcb);
        if (!operationSuccessful) {
            stats.recordDefectFailure(name);
            pcb.setFailed("Defect detected at " + name);
        }
    }

    protected boolean checkStationFailure() {
        return random.nextDouble() < stationFailureRate;
    }

    protected abstract boolean performOperation(PCB pcb);

    // Lombok @Getter annotation generates getName() and getStationFailureRate() methods
}