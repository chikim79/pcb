package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;
import com.cu5448.pcb.service.StatisticsCollector;
import lombok.EqualsAndHashCode;

/**
 * Hand Soldering Station using Lombok
 */
@EqualsAndHashCode(callSuper = true)
public class HandSolderingStation extends Station {
    
    public HandSolderingStation(double failureRate, StatisticsCollector stats) {
        super("HandSoldering", failureRate, stats);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        double defectRate = pcb.getDefectRate("HandSoldering");
        return random.nextDouble() >= defectRate;
    }
}