package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;
import com.cu5448.pcb.service.StatisticsCollector;

public class CleaningStation extends Station {
    
    public CleaningStation(double failureRate, StatisticsCollector stats) {
        super("Cleaning", failureRate, stats);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        return true;
    }
}