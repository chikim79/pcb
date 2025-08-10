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

    private DefectRates testboard = new DefectRates();

    private DefectRates sensorboard = new DefectRates();

    private DefectRates gatewayboard = new DefectRates();
}
