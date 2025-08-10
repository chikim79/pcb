package com.cu5448.pcb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DefectRates encapsulates defect rates for different manufacturing stations. This class replaces
 * the Map<String, Double> approach with a type-safe, immutable object.
 *
 * <p>Only four stations can detect defects: PlaceComponents, OpticalInspection, HandSoldering, and
 * Test.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DefectRates {

    private double placeComponentsDefectRate;
    private double opticalInspectionDefectRate;
    private double handSolderingDefectRate;
    private double testDefectRate;

    /**
     * Gets the defect rate for a specific station type.
     *
     * @param stationType the station type name
     * @return the defect rate for the station, or 0.0 if the station doesn't detect defects
     */
    public double getDefectRate(String stationType) {
        return switch (stationType) {
            case "PlaceComponents" -> placeComponentsDefectRate;
            case "OpticalInspection" -> opticalInspectionDefectRate;
            case "HandSoldering" -> handSolderingDefectRate;
            case "Test" -> testDefectRate;
            default -> 0.0; // Stations that don't detect defects
        };
    }

    /** Creates DefectRates with all rates set to zero. */
    public static DefectRates noDefects() {
        return DefectRates.builder()
                .placeComponentsDefectRate(0.0)
                .opticalInspectionDefectRate(0.0)
                .handSolderingDefectRate(0.0)
                .testDefectRate(0.0)
                .build();
    }
}
