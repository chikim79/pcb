package com.cu5448.pcb.model;

import com.cu5448.pcb.config.PCBProperties;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * Test Board PCB Implementation using Lombok
 */
@EqualsAndHashCode(callSuper = true)
public class TestBoard extends PCB {
    private final Map<String, Double> defectRates;

    public TestBoard() {
        super("TestBoard");
        // Default rates for backward compatibility
        this.defectRates = Map.of(
            "PlaceComponents", 0.05,
            "OpticalInspection", 0.10,
            "HandSoldering", 0.05,
            "Test", 0.10
        );
    }

    public TestBoard(PCBProperties.TestBoardDefectRates defectRatesConfig) {
        super("TestBoard");
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