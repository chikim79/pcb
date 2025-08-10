package com.cu5448.pcb.station;

import java.util.Random;

import com.cu5448.pcb.model.PCB;
import com.cu5448.pcb.service.StatisticsCollector;

import lombok.Getter;

/**
 * Abstract Station class that can be used as a Spring bean. StatisticsCollector is injected per
 * simulation run rather than at construction time.
 */
@Getter
public abstract class Station {

    protected final String name;

    protected final double stationFailureRate;

    protected final Random random = new Random();

    public Station(String name, double failureRate) {
        this.name = name;
        this.stationFailureRate = failureRate;
    }

    public void process(PCB pcb, StatisticsCollector stats) {
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
}
