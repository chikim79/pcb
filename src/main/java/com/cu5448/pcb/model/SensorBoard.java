package com.cu5448.pcb.model;

import lombok.EqualsAndHashCode;

/** Sensor Board PCB Implementation using Lombok */
@EqualsAndHashCode(callSuper = true)
public class SensorBoard extends PCB {

    private final DefectRates defectRates;

    public SensorBoard(DefectRates defectRates) {
        super("SensorBoard");
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
