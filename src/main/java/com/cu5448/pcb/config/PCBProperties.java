package com.cu5448.pcb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * PCB Configuration Properties using Lombok
 * 
 * Demonstrates nested configuration properties with Lombok @Data
 */
@Data
@Component
@ConfigurationProperties(prefix = "pcb")
public class PCBProperties {
    
    private TestBoardDefectRates testboard = new TestBoardDefectRates();
    private SensorBoardDefectRates sensorboard = new SensorBoardDefectRates();
    private GatewayBoardDefectRates gatewayboard = new GatewayBoardDefectRates();

    @Data
    public static class TestBoardDefectRates {
        private double placeComponentsDefectRate = 0.05;
        private double opticalInspectionDefectRate = 0.10;
        private double handSolderingDefectRate = 0.05;
        private double testDefectRate = 0.10;
    }

    @Data
    public static class SensorBoardDefectRates {
        private double placeComponentsDefectRate = 0.002;
        private double opticalInspectionDefectRate = 0.002;
        private double handSolderingDefectRate = 0.004;
        private double testDefectRate = 0.004;
    }

    @Data
    public static class GatewayBoardDefectRates {
        private double placeComponentsDefectRate = 0.004;
        private double opticalInspectionDefectRate = 0.004;
        private double handSolderingDefectRate = 0.008;
        private double testDefectRate = 0.008;
    }
}