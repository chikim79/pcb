package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;

import lombok.EqualsAndHashCode;

/**
 * Place Components Station using Lombok @EqualsAndHashCode(callSuper = true) includes parent class
 * fields in equals/hashCode
 */
@EqualsAndHashCode(callSuper = true)
public class PlaceComponentsStation extends Station {

    public PlaceComponentsStation(double failureRate) {
        super("PlaceComponents", failureRate);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        double defectRate = pcb.getDefectRate("PlaceComponents");
        return random.nextDouble() >= defectRate;
    }
}
