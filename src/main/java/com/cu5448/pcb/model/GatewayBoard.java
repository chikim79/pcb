package com.cu5448.pcb.model;

import com.cu5448.pcb.config.PCBProperties;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * Gateway Board PCB Implementation using Lombok
 */
@EqualsAndHashCode(callSuper = true)
public class GatewayBoard extends PCB {
    private final Map<String, Double> defectRates;

    public GatewayBoard() {
        super("GatewayBoard");
        // Default rates for backward compatibility
        this.defectRates = Map.of(
            "PlaceComponents", 0.004,
            "OpticalInspection", 0.004,
            "HandSoldering", 0.008,
            "Test", 0.008
        );
    }

    public GatewayBoard(PCBProperties.GatewayBoardDefectRates defectRatesConfig) {
        super("GatewayBoard");
        this.defectRates = Map.of(
            "PlaceComponents", defectRatesConfig.getPlaceComponentsDefectRate(),
            "OpticalInspection", defectRatesConfig.getOpticalInspectionDefectRate(),
            "HandSoldering", defectRatesConfig.getHandSolderingDefectRate(),
            "Test", defectRatesConfig.getTestDefectRate()
        );
    }

    @Override
    public double getDefectRate(String stationType) {
        return defectRates.getOrDefault(stationType, 0.0);
    }
}