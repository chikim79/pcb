package com.cu5448.pcb.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cu5448.pcb.station.*;

/** Test class for StationFactory implementation */
@SpringBootTest
class StationFactoryTest {

    @Autowired private StationFactory stationFactory;

    @Test
    void testFactoryInjection() {
        assertNotNull(stationFactory, "StationFactory should be injected");
    }

    @Test
    void testCreateIndividualStations() {
        // Test each station creation method
        Station applySolderPaste = stationFactory.createApplySolderPasteStation();
        assertNotNull(applySolderPaste);
        assertEquals("ApplySolderPaste", applySolderPaste.getName());

        Station placeComponents = stationFactory.createPlaceComponentsStation();
        assertNotNull(placeComponents);
        assertEquals("PlaceComponents", placeComponents.getName());

        Station reflowSolder = stationFactory.createReflowSolderStation();
        assertNotNull(reflowSolder);
        assertEquals("ReflowSolder", reflowSolder.getName());

        Station opticalInspection = stationFactory.createOpticalInspectionStation();
        assertNotNull(opticalInspection);
        assertEquals("OpticalInspection", opticalInspection.getName());

        Station handSoldering = stationFactory.createHandSolderingStation();
        assertNotNull(handSoldering);
        assertEquals("HandSoldering", handSoldering.getName());

        Station cleaning = stationFactory.createCleaningStation();
        assertNotNull(cleaning);
        assertEquals("Cleaning", cleaning.getName());

        Station depanelization = stationFactory.createDepanelizationStation();
        assertNotNull(depanelization);
        assertEquals("Depanelization", depanelization.getName());

        Station test = stationFactory.createTestStation();
        assertNotNull(test);
        assertEquals("Test", test.getName());
    }

    @Test
    void testCreateStationByType() {
        // Test station creation by type name
        Station applySolder = stationFactory.createStationByType("ApplySolderPaste");
        assertInstanceOf(ApplySolderPasteStation.class, applySolder);
        assertEquals("ApplySolderPaste", applySolder.getName());

        Station placeComponents = stationFactory.createStationByType("PlaceComponents");
        assertInstanceOf(PlaceComponentsStation.class, placeComponents);
        assertEquals("PlaceComponents", placeComponents.getName());

        Station test = stationFactory.createStationByType("Test");
        assertInstanceOf(TestStation.class, test);
        assertEquals("Test", test.getName());

        // Test camelCase variants
        Station camelCaseTest = stationFactory.createStationByType("applySolderPaste");
        assertInstanceOf(ApplySolderPasteStation.class, camelCaseTest);

        Station camelCasePlace = stationFactory.createStationByType("placeComponents");
        assertInstanceOf(PlaceComponentsStation.class, camelCasePlace);
    }

    @Test
    void testCreateStationByTypeInvalid() {
        // Test invalid station type
        assertThrows(
                IllegalArgumentException.class,
                () -> stationFactory.createStationByType("InvalidStation"),
                "Should throw exception for invalid station type");

        assertThrows(
                IllegalArgumentException.class,
                () -> stationFactory.createStationByType(""),
                "Should throw exception for empty station type");

        assertThrows(
                IllegalArgumentException.class,
                () -> stationFactory.createStationByType("SomeRandomName"),
                "Should throw exception for random station type");
    }

    @Test
    void testAllStationTypesSupported() {
        // Test all expected station types are supported by createStationByType
        String[] stationTypes = {
            "ApplySolderPaste", "applySolderPaste",
            "PlaceComponents", "placeComponents",
            "ReflowSolder", "reflowSolder",
            "OpticalInspection", "opticalInspection",
            "HandSoldering", "handSoldering",
            "Cleaning", "cleaning",
            "Depanelization", "depanelization",
            "Test", "test"
        };

        for (String stationType : stationTypes) {
            assertDoesNotThrow(
                    () -> {
                        Station station = stationFactory.createStationByType(stationType);
                        assertNotNull(
                                station, "Station should be created for type: " + stationType);
                    },
                    "Should be able to create station for type: " + stationType);
        }
    }
}
