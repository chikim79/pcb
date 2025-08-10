package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;

public class CleaningStation extends Station {

    public CleaningStation(double failureRate) {
        super("Cleaning", failureRate);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        return true;
    }
}
