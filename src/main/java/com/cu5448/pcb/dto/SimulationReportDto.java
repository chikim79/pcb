package com.cu5448.pcb.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for simulation report data used in REST API communication between server and
 * client applications.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationReportDto {

    @JsonProperty("pcbType")
    private String pcbType;

    @JsonProperty("pcbsRun")
    private int pcbsRun;

    @JsonProperty("stationFailures")
    private Map<String, Integer> stationFailures;

    @JsonProperty("defectFailures")
    private Map<String, Integer> defectFailures;

    @JsonProperty("totalFailedPcbs")
    private int totalFailedPcbs;

    @JsonProperty("totalPcbsProduced")
    private int totalPcbsProduced;

    @JsonProperty("formattedReport")
    private String formattedReport;
}
