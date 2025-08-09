package com.cu5448.pcb.model;

import com.cu5448.pcb.config.PCBProperties;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * Sensor Board PCB Implementation using Lombok
 */
@EqualsAndHashCode(callSuper = true)
public class SensorBoard extends PCB {
    private final Map<String, Double> defectRates;

    public SensorBoard() {
        super("SensorBoard");
        // Default rates for backward compatibility
        this.defectRates = Map.of(
            "PlaceComponents", 0.002,
            "OpticalInspection", 0.002,
            "HandSoldering", 0.004,
            "Test", 0.004
        );
    }

    public SensorBoard(PCBProperties.SensorBoardDefectRates defectRatesConfig) {
        super("SensorBoard");
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