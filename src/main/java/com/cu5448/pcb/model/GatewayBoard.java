package com.cu5448.pcb.model;

import com.cu5448.pcb.config.PCBProperties;

import lombok.EqualsAndHashCode;

/** Gateway Board PCB Implementation using Lombok */
@EqualsAndHashCode(callSuper = true)
public class GatewayBoard extends PCB {

    private final DefectRates defectRates;

    public GatewayBoard() {
        super("GatewayBoard");
        // Default rates for backward compatibility
        this.defectRates = DefectRates.gatewayBoardDefaults();
    }

    public GatewayBoard(PCBProperties.GatewayBoardDefectRates defectRatesConfig) {
        super("GatewayBoard");
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
