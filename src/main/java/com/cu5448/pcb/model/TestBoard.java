package com.cu5448.pcb.model;

import lombok.EqualsAndHashCode;

/** Test Board PCB Implementation using Lombok */
@EqualsAndHashCode(callSuper = true)
public class TestBoard extends PCB {

    private final DefectRates defectRates;

    public TestBoard(DefectRates defectRates) {
        super("TestBoard");
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
