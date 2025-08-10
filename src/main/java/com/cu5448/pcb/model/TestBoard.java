package com.cu5448.pcb.model;

import com.cu5448.pcb.config.PCBProperties;

import lombok.EqualsAndHashCode;

/** Test Board PCB Implementation using Lombok */
@EqualsAndHashCode(callSuper = true)
public class TestBoard extends PCB {

    private final DefectRates defectRates;

    public TestBoard() {
        super("TestBoard");
        // Default rates for backward compatibility
        this.defectRates = DefectRates.testBoardDefaults();
    }

    public TestBoard(PCBProperties.TestBoardDefectRates defectRatesConfig) {
        super("TestBoard");
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
