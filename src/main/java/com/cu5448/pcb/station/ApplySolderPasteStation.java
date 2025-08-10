package com.cu5448.pcb.station;

import com.cu5448.pcb.model.PCB;

public class ApplySolderPasteStation extends Station {

    public ApplySolderPasteStation(double failureRate) {
        super("ApplySolderPaste", failureRate);
    }

    @Override
    protected boolean performOperation(PCB pcb) {
        return true;
    }
}
