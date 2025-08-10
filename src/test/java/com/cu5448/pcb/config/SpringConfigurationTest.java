package com.cu5448.pcb.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cu5448.pcb.controller.SimulationController;
import com.cu5448.pcb.factory.PCBFactory;
import com.cu5448.pcb.service.AssemblyLine;

@SpringBootTest
class SpringConfigurationTest {

    @Autowired private SimulationController simulationController;

    @Autowired private AssemblyLine assemblyLine;

    @Autowired private PCBFactory pcbFactory;

    @Autowired private SimulationProperties simulationProperties;

    @Autowired private StationProperties stationProperties;

    @Autowired private PCBProperties pcbProperties;

    @Test
    void testSpringDependencyInjection() {
        // Verify that all Spring beans are properly injected
        assertNotNull(simulationController);
        assertNotNull(assemblyLine);
        assertNotNull(pcbFactory);
    }

    @Test
    void testConfigurationProperties() {
        // Verify that configuration properties are loaded correctly
        assertEquals(1000, simulationProperties.getPcbQuantity());
        assertEquals(0.002, stationProperties.getFailureRate(), 0.0001);

        // Test PCB defect rates from properties (using Lombok-generated getters)
        assertEquals(0.05, pcbProperties.getTestboard().getPlaceComponentsDefectRate(), 0.0001);
        assertEquals(0.002, pcbProperties.getSensorboard().getPlaceComponentsDefectRate(), 0.0001);
        assertEquals(0.004, pcbProperties.getGatewayboard().getPlaceComponentsDefectRate(), 0.0001);
    }

    @Test
    void testPCBFactoryWithConfiguration() {
        // Test that PCB factory creates boards with configuration-driven defect rates
        var testBoard = pcbFactory.createPCB("Test Board");
        assertEquals("TestBoard", testBoard.getType());
        assertEquals(0.05, testBoard.getDefectRate("PlaceComponents"), 0.0001);

        var sensorBoard = pcbFactory.createPCB("Sensor Board");
        assertEquals("SensorBoard", sensorBoard.getType());
        assertEquals(0.002, sensorBoard.getDefectRate("PlaceComponents"), 0.0001);
    }
}
