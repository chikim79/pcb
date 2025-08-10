package com.cu5448.pcb.model;

import com.cu5448.pcb.config.PCBProperties;

import lombok.EqualsAndHashCode;

/** Sensor Board PCB Implementation using Lombok */
@EqualsAndHashCode(callSuper = true)
public class SensorBoard extends PCB {

    private final DefectRates defectRates;

    public SensorBoard() {
        super("SensorBoard");
        // Default rates for backward compatibility
        this.defectRates = DefectRates.sensorBoardDefaults();
    }

    public SensorBoard(PCBProperties.SensorBoardDefectRates defectRatesConfig) {
        super("SensorBoard");
        this.defectRates =
                DefectRates.builder()
                        .placeComponentsDefectRate(defectRatesConfig.getPlaceComponentsDefectRate())
                        .opticalInspectionDefectRate(
                                defectRatesConfig.getOpticalInspectionDefectRate())
                        .handSolderingDefectRate(defectRatesConfig.getHandSolderingDefectRate())
                        .testDefectRate(defectRatesConfig.getTestDefectRate())
                        .build();
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
