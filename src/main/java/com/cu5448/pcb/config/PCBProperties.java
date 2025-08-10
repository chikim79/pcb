package com.cu5448.pcb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.cu5448.pcb.model.DefectRates;

import lombok.Data;

/**
 * PCB Configuration Properties that directly create DefectRates instances. This approach eliminates
 * nested property classes and provides direct access to DefectRates objects for each PCB type.
 */
@Data
@Component
@ConfigurationProperties(prefix = "pcb")
public class PCBProperties {

    /** Creates DefectRates for TestBoard from configuration properties. */
    public DefectRates getTestboardDefectRates() {
        return DefectRates.builder()
                .placeComponentsDefectRate(testboard.placeComponentsDefectRate)
                .opticalInspectionDefectRate(testboard.opticalInspectionDefectRate)
                .handSolderingDefectRate(testboard.handSolderingDefectRate)
                .testDefectRate(testboard.testDefectRate)
                .build();
    }

    /** Creates DefectRates for SensorBoard from configuration properties. */
    public DefectRates getSensorboardDefectRates() {
        return DefectRates.builder()
                .placeComponentsDefectRate(sensorboard.placeComponentsDefectRate)
                .opticalInspectionDefectRate(sensorboard.opticalInspectionDefectRate)
                .handSolderingDefectRate(sensorboard.handSolderingDefectRate)
                .testDefectRate(sensorboard.testDefectRate)
                .build();
    }

    /** Creates DefectRates for GatewayBoard from configuration properties. */
    public DefectRates getGatewayboardDefectRates() {
        return DefectRates.builder()
                .placeComponentsDefectRate(gatewayboard.placeComponentsDefectRate)
                .opticalInspectionDefectRate(gatewayboard.opticalInspectionDefectRate)
                .handSolderingDefectRate(gatewayboard.handSolderingDefectRate)
                .testDefectRate(gatewayboard.testDefectRate)
                .build();
    }

    // Nested configuration classes for property binding
    private BoardConfig testboard = new BoardConfig();

    private BoardConfig sensorboard = new BoardConfig();

    private BoardConfig gatewayboard = new BoardConfig();

    @Data
    public static class BoardConfig {
        private double placeComponentsDefectRate;
        private double opticalInspectionDefectRate;
        private double handSolderingDefectRate;
        private double testDefectRate;
    }
}
