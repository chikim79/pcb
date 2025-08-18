package com.cu5448.pcb.dto;

import org.springframework.stereotype.Component;

import com.cu5448.pcb.entity.SimulationResult;
import com.cu5448.pcb.service.StatisticsCollector;

/**
 * Mapper class to convert SimulationResult entities and StatisticsCollector data to
 * SimulationReportDto for API communication.
 */
@Component
public class SimulationReportMapper {

    /**
     * Convert SimulationResult entity to DTO.
     *
     * @param result the simulation result entity
     * @return the DTO for API response
     */
    public SimulationReportDto toDto(SimulationResult result) {
        SimulationReportDto dto = new SimulationReportDto();

        dto.setPcbType(result.getPcbType());
        dto.setPcbsRun(result.getPcbsSubmitted());
        dto.setStationFailures(result.getStationFailures());
        dto.setDefectFailures(result.getDefectFailures());
        dto.setTotalFailedPcbs(result.getTotalFailedPcbs());
        dto.setTotalPcbsProduced(result.getPcbsCompleted());
        dto.setFormattedReport(result.getFormattedReport());

        return dto;
    }

    /**
     * Convert StatisticsCollector data to DTO (for backward compatibility).
     *
     * @param stats the statistics collector
     * @param pcbType the PCB type
     * @return the DTO for API response
     */
    public SimulationReportDto toDto(StatisticsCollector stats, String pcbType) {
        SimulationReportDto dto = new SimulationReportDto();

        dto.setPcbType(pcbType);
        dto.setPcbsRun(stats.getPcbsSubmitted());
        dto.setStationFailures(stats.getStationFailures());
        dto.setDefectFailures(stats.getDefectFailures());
        dto.setTotalFailedPcbs(stats.getPcbsSubmitted() - stats.getCompletedPCBs());
        dto.setTotalPcbsProduced(stats.getCompletedPCBs());
        dto.setFormattedReport(stats.generateReport(pcbType));

        return dto;
    }
}
