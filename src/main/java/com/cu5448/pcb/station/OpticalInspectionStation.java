package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;

import lombok.EqualsAndHashCode;

/** Optical Inspection Station using Lombok */
@EqualsAndHashCode(callSuper = true)
public class OpticalInspectionStation extends Station {

    public OpticalInspectionStation(double failureRate) {
        super("OpticalInspection", failureRate);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        double defectRate = pcb.getDefectRate("OpticalInspection");
        return random.nextDouble() >= defectRate;
    }
}
