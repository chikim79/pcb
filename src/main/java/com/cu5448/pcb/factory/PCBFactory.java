package com.cu5448.pcb.factory;

import org.springframework.stereotype.Component;

import com.cu5448.pcb.config.PCBProperties;
import com.cu5448.pcb.model.GatewayBoard;
import com.cu5448.pcb.model.PCB;
import com.cu5448.pcb.model.SensorBoard;
import com.cu5448.pcb.model.TestBoard;

import lombok.RequiredArgsConstructor;

/**
 * Factory Pattern Implementation using Spring Dependency Injection and
 * Lombok @RequiredArgsConstructor generates constructor for final fields This factory creates PCB
 * instances with configuration-driven defect rates.
 */
@Component
@RequiredArgsConstructor
public class PCBFactory {

    private final PCBProperties pcbProperties;

    public PCB createPCB(String type) {
        return switch (type.toLowerCase()) {
            case "testboard", "test", "test board" -> new TestBoard(pcbProperties.getTestboard());
            case "sensorboard", "sensor", "sensor board" ->
                    new SensorBoard(pcbProperties.getSensorboard());
            case "gatewayboard", "gateway", "gateway board" ->
                    new GatewayBoard(pcbProperties.getGatewayboard());
            default -> throw new IllegalArgumentException("Unknown PCB type: " + type);
        };
    }
}
