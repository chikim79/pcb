package com.cu5448.pcb.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import com.cu5448.pcb.service.AssemblyLine;
import com.cu5448.pcb.station.*;

import lombok.RequiredArgsConstructor;

/** Test class to verify Spring bean configuration using Abstract Factory Pattern */
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class SpringBeanConfigurationTest {

    private final AssemblyLine assemblyLine;

    private final List<Station> stations;

    @Test
    void testAssemblyLineInjection() {
        assertNotNull(assemblyLine, "AssemblyLine should be injected");

        List<Station> assemblyStations = assemblyLine.getStations();
        assertEquals(8, assemblyStations.size(), "Assembly line should have 8 stations");
    }

    @Test
    void testStationListOrder() {
        assertNotNull(stations, "Station list should be injected");
        assertEquals(8, stations.size(), "Should have 8 stations");

        // Verify the correct order of stations
        assertEquals("ApplySolderPaste", stations.get(0).getName());
        assertEquals("PlaceComponents", stations.get(1).getName());
        assertEquals("ReflowSolder", stations.get(2).getName());
        assertEquals("OpticalInspection", stations.get(3).getName());
        assertEquals("HandSoldering", stations.get(4).getName());
        assertEquals("Cleaning", stations.get(5).getName());
        assertEquals("Depanelization", stations.get(6).getName());
        assertEquals("Test", stations.get(7).getName());
    }

    @Test
    void testStationFailureRatesAreConfigured() {
        // All stations should have the same configured failure rate
        double expectedFailureRate = 0.002; // From application.properties

        for (Station station : stations) {
            assertEquals(
                    expectedFailureRate,
                    station.getStationFailureRate(),
                    "Station " + station.getName() + " should have configured failure rate");
        }
    }

    @Test
    void testAssemblyLineStationsAreSameAsInjectedList() {
        List<Station> assemblyStations = assemblyLine.getStations();

        // Verify same stations are used (but different list instance due to List.copyOf)
        assertEquals(stations.size(), assemblyStations.size());

        for (int i = 0; i < stations.size(); i++) {
            assertSame(
                    stations.get(i),
                    assemblyStations.get(i),
                    "Station " + i + " should be the same bean instance");
        }
    }
}
