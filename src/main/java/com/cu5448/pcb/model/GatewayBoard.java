package com.cu5448.pcb.model;

import lombok.EqualsAndHashCode;

/** Gateway Board PCB Implementation using Lombok */
@EqualsAndHashCode(callSuper = true)
public class GatewayBoard extends PCB {

    private final DefectRates defectRates;

    public GatewayBoard(DefectRates defectRates) {
        super("GatewayBoard");
        this.defectRates = defectRates;
    }

    @Override
    public double getDefectRate(String stationType) {
        return defectRates.getDefectRate(stationType);
    }

    @Override
    public DefectRates getDefectRates() {
        return defectRates;
    }
}
