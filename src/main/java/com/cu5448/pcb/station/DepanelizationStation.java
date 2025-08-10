package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;

public class DepanelizationStation extends Station {

    public DepanelizationStation(double failureRate) {
        super("Depanelization", failureRate);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        return true;
    }
}
