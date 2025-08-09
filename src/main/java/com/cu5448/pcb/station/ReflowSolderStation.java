package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;
import com.cu5448.pcb.service.StatisticsCollector;

public class ReflowSolderStation extends Station {
    
    public ReflowSolderStation(double failureRate, StatisticsCollector stats) {
        super("ReflowSolder", failureRate, stats);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        return true;
    }
}