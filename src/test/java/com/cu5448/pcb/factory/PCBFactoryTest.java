package com.cu5448.pcb.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cu5448.pcb.config.PCBProperties;
import com.cu5448.pcb.model.*;

class PCBFactoryTest {

    private PCBFactory factory;

    private PCBProperties pcbProperties;

    @BeforeEach
    void setUp() {
        pcbProperties = new PCBProperties();
        factory = new PCBFactory(pcbProperties);
    }

    @Test
    void testCreateTestBoard() {
        PCB pcb = factory.createPCB("testboard");
        assertInstanceOf(TestBoard.class, pcb);
        assertEquals("TestBoard", pcb.getType());
    }

    @Test
    void testCreateSensorBoard() {
        PCB pcb = factory.createPCB("sensorboard");
        assertInstanceOf(SensorBoard.class, pcb);
        assertEquals("SensorBoard", pcb.getType());
    }

    @Test
    void testCreateGatewayBoard() {
        PCB pcb = factory.createPCB("gatewayboard");
        assertInstanceOf(GatewayBoard.class, pcb);
        assertEquals("GatewayBoard", pcb.getType());
    }

    @Test
    void testCreateWithAlternativeNames() {
        assertInstanceOf(TestBoard.class, factory.createPCB("test"));
        assertInstanceOf(SensorBoard.class, factory.createPCB("sensor"));
        assertInstanceOf(GatewayBoard.class, factory.createPCB("gateway"));
    }

    @Test
    void testCreateWithInvalidType() {
        assertThrows(IllegalArgumentException.class, () -> factory.createPCB("invalid"));
    }
}
