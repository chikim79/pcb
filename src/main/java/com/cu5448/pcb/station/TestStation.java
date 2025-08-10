package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;

import lombok.EqualsAndHashCode;

/** Test Station using Lombok */
@EqualsAndHashCode(callSuper = true)
public class TestStation extends Station {

    public TestStation(double failureRate) {
        super("Test", failureRate);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        double defectRate = pcb.getDefectRate("Test");
        return random.nextDouble() >= defectRate;
    }
}
