package com.cu5448.pcb.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** Test class for DefectRates model */
class DefectRatesTest {

    @Test
    void testBuilderPattern() {
        DefectRates rates =
                DefectRates.builder()
                        .placeComponentsDefectRate(0.01)
                        .opticalInspectionDefectRate(0.02)
                        .handSolderingDefectRate(0.03)
                        .testDefectRate(0.04)
                        .build();

        assertEquals(0.01, rates.getPlaceComponentsDefectRate());
        assertEquals(0.02, rates.getOpticalInspectionDefectRate());
        assertEquals(0.03, rates.getHandSolderingDefectRate());
        assertEquals(0.04, rates.getTestDefectRate());
    }

    @Test
    void testGetDefectRateWithValidStations() {
        DefectRates rates =
                DefectRates.builder()
                        .placeComponentsDefectRate(0.05)
                        .opticalInspectionDefectRate(0.10)
                        .handSolderingDefectRate(0.05)
                        .testDefectRate(0.10)
                        .build();

        assertEquals(0.05, rates.getDefectRate("PlaceComponents"));
        assertEquals(0.10, rates.getDefectRate("OpticalInspection"));
        assertEquals(0.05, rates.getDefectRate("HandSoldering"));
        assertEquals(0.10, rates.getDefectRate("Test"));
    }

    @Test
    void testGetDefectRateWithInvalidStation() {
        DefectRates rates =
                DefectRates.builder()
                        .placeComponentsDefectRate(0.05)
                        .opticalInspectionDefectRate(0.10)
                        .handSolderingDefectRate(0.05)
                        .testDefectRate(0.10)
                        .build();

        assertEquals(0.0, rates.getDefectRate("ApplySolderPaste"));
        assertEquals(0.0, rates.getDefectRate("ReflowSolder"));
        assertEquals(0.0, rates.getDefectRate("Cleaning"));
        assertEquals(0.0, rates.getDefectRate("Depanelization"));
        assertEquals(0.0, rates.getDefectRate("NonExistentStation"));
    }

    @Test
    void testNoDefectsFactory() {
        DefectRates rates = DefectRates.noDefects();

        assertEquals(0.0, rates.getDefectRate("PlaceComponents"));
        assertEquals(0.0, rates.getDefectRate("OpticalInspection"));
        assertEquals(0.0, rates.getDefectRate("HandSoldering"));
        assertEquals(0.0, rates.getDefectRate("Test"));
    }

    @Test
    void testPCBIntegration() {
        // Test that PCB implementations can use DefectRates
        DefectRates testRates =
                DefectRates.builder()
                        .placeComponentsDefectRate(0.05)
                        .opticalInspectionDefectRate(0.10)
                        .handSolderingDefectRate(0.05)
                        .testDefectRate(0.10)
                        .build();
        TestBoard testBoard = new TestBoard(testRates);
        DefectRates defectRates = testBoard.getDefectRates();

        assertNotNull(defectRates);
        assertEquals(0.05, testBoard.getDefectRate("PlaceComponents"));
        assertEquals(0.05, defectRates.getDefectRate("PlaceComponents"));

        DefectRates sensorRates =
                DefectRates.builder()
                        .placeComponentsDefectRate(0.002)
                        .opticalInspectionDefectRate(0.002)
                        .handSolderingDefectRate(0.004)
                        .testDefectRate(0.004)
                        .build();
        SensorBoard sensorBoard = new SensorBoard(sensorRates);
        DefectRates actualSensorRates = sensorBoard.getDefectRates();

        assertNotNull(actualSensorRates);
        assertEquals(0.002, sensorBoard.getDefectRate("PlaceComponents"));
        assertEquals(0.002, actualSensorRates.getDefectRate("PlaceComponents"));
    }
}
