package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;

import lombok.EqualsAndHashCode;

/** Hand Soldering Station using Lombok */
@EqualsAndHashCode(callSuper = true)
public class HandSolderingStation extends Station {

    public HandSolderingStation(double failureRate) {
        super("HandSoldering", failureRate);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        double defectRate = pcb.getDefectRate("HandSoldering");
        return random.nextDouble() >= defectRate;
    }
}
