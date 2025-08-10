package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;

public class ReflowSolderStation extends Station {

    public ReflowSolderStation(double failureRate) {
        super("ReflowSolder", failureRate);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        return true;
    }
}
