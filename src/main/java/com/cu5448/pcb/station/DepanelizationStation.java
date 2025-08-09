package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;
import com.cu5448.pcb.service.StatisticsCollector;

public class DepanelizationStation extends Station {
    
    public DepanelizationStation(double failureRate, StatisticsCollector stats) {
        super("Depanelization", failureRate, stats);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        return true;
    }
}