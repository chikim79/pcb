package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;
import com.cu5448.pcb.service.StatisticsCollector;

public class ApplySolderPasteStation extends Station {

    public ApplySolderPasteStation(double failureRate, StatisticsCollector stats) {
        super("ApplySolderPaste", failureRate, stats);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        return true;
    }
}
