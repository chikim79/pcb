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
        // Test station creation using abstract factory method
        Station applySolderPaste = stationFactory.createStation("ApplySolderPaste");
        assertNotNull(applySolderPaste);
        assertEquals("ApplySolderPaste", applySolderPaste.getName());

        Station placeComponents = stationFactory.createStation("PlaceComponents");
        assertNotNull(placeComponents);
        assertEquals("PlaceComponents", placeComponents.getName());

        Station reflowSolder = stationFactory.createStation("ReflowSolder");
        assertNotNull(reflowSolder);
        assertEquals("ReflowSolder", reflowSolder.getName());

        Station opticalInspection = stationFactory.createStation("OpticalInspection");
        assertNotNull(opticalInspection);
        assertEquals("OpticalInspection", opticalInspection.getName());

        Station handSoldering = stationFactory.createStation("HandSoldering");
        assertNotNull(handSoldering);
        assertEquals("HandSoldering", handSoldering.getName());

        Station cleaning = stationFactory.createStation("Cleaning");
        assertNotNull(cleaning);
        assertEquals("Cleaning", cleaning.getName());

        Station depanelization = stationFactory.createStation("Depanelization");
        assertNotNull(depanelization);
        assertEquals("Depanelization", depanelization.getName());

        Station test = stationFactory.createStation("Test");
        assertNotNull(test);
        assertEquals("Test", test.getName());
    }

    @Test
    void testCreateStationByType() {
        // Test station creation by type name using abstract factory
        Station applySolder = stationFactory.createStation("ApplySolderPaste");
        assertInstanceOf(ApplySolderPasteStation.class, applySolder);
        assertEquals("ApplySolderPaste", applySolder.getName());

        Station placeComponents = stationFactory.createStation("PlaceComponents");
        assertInstanceOf(PlaceComponentsStation.class, placeComponents);
        assertEquals("PlaceComponents", placeComponents.getName());

        Station test = stationFactory.createStation("Test");
        assertInstanceOf(TestStation.class, test);
        assertEquals("Test", test.getName());
    }

    @Test
    void testCreateStationByTypeInvalid() {
        // Test invalid station type using abstract factory
        assertThrows(
                IllegalArgumentException.class,
                () -> stationFactory.createStation("InvalidStation"),
                "Should throw exception for invalid station type");

        assertThrows(
                IllegalArgumentException.class,
                () -> stationFactory.createStation(""),
                "Should throw exception for empty station type");

        assertThrows(
                IllegalArgumentException.class,
                () -> stationFactory.createStation("SomeRandomName"),
                "Should throw exception for random station type");
    }

    @Test
    void testAllStationTypesSupported() {
        // Test all expected station types are supported by abstract factory
        String[] stationTypes = {
            "ApplySolderPaste",
            "PlaceComponents",
            "ReflowSolder",
            "OpticalInspection",
            "HandSoldering",
            "Cleaning",
            "Depanelization",
            "Test"
        };

        for (String stationType : stationTypes) {
            assertDoesNotThrow(
                    () -> {
                        Station station = stationFactory.createStation(stationType);
                        assertNotNull(
                                station, "Station should be created for type: " + stationType);
                    },
                    "Should be able to create station for type: " + stationType);
        }
    }
}
