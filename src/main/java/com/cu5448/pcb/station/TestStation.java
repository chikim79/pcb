package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;
import com.cu5448.pcb.service.StatisticsCollector;

import lombok.EqualsAndHashCode;

/** Test Station using Lombok */
@EqualsAndHashCode(callSuper = true)
public class TestStation extends Station {

    public TestStation(double failureRate, StatisticsCollector stats) {
        super("Test", failureRate, stats);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        double defectRate = pcb.getDefectRate("Test");
        return random.nextDouble() >= defectRate;
    }
}
