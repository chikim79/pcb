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
        DefectRates rates = DefectRates.testBoardDefaults();

        assertEquals(0.05, rates.getDefectRate("PlaceComponents"));
        assertEquals(0.10, rates.getDefectRate("OpticalInspection"));
        assertEquals(0.05, rates.getDefectRate("HandSoldering"));
        assertEquals(0.10, rates.getDefectRate("Test"));
    }

    @Test
    void testGetDefectRateWithInvalidStation() {
        DefectRates rates = DefectRates.testBoardDefaults();

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
    void testTestBoardDefaults() {
        DefectRates rates = DefectRates.testBoardDefaults();

        assertEquals(0.05, rates.getDefectRate("PlaceComponents"));
        assertEquals(0.10, rates.getDefectRate("OpticalInspection"));
        assertEquals(0.05, rates.getDefectRate("HandSoldering"));
        assertEquals(0.10, rates.getDefectRate("Test"));
    }

    @Test
    void testSensorBoardDefaults() {
        DefectRates rates = DefectRates.sensorBoardDefaults();

        assertEquals(0.002, rates.getDefectRate("PlaceComponents"));
        assertEquals(0.002, rates.getDefectRate("OpticalInspection"));
        assertEquals(0.004, rates.getDefectRate("HandSoldering"));
        assertEquals(0.004, rates.getDefectRate("Test"));
    }

    @Test
    void testGatewayBoardDefaults() {
        DefectRates rates = DefectRates.gatewayBoardDefaults();

        assertEquals(0.004, rates.getDefectRate("PlaceComponents"));
        assertEquals(0.004, rates.getDefectRate("OpticalInspection"));
        assertEquals(0.008, rates.getDefectRate("HandSoldering"));
        assertEquals(0.008, rates.getDefectRate("Test"));
    }

    @Test
    void testPCBIntegration() {
        // Test that PCB implementations can use DefectRates
        TestBoard testBoard = new TestBoard();
        DefectRates defectRates = testBoard.getDefectRates();

        assertNotNull(defectRates);
        assertEquals(0.05, testBoard.getDefectRate("PlaceComponents"));
        assertEquals(0.05, defectRates.getDefectRate("PlaceComponents"));

        SensorBoard sensorBoard = new SensorBoard();
        DefectRates sensorRates = sensorBoard.getDefectRates();

        assertNotNull(sensorRates);
        assertEquals(0.002, sensorBoard.getDefectRate("PlaceComponents"));
        assertEquals(0.002, sensorRates.getDefectRate("PlaceComponents"));
    }
}
