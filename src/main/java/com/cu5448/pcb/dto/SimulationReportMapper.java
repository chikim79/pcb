package com.cu5448.pcb.dto;

import org.springframework.stereotype.Component;

import com.cu5448.pcb.service.StatisticsCollector;

/**
 * Mapper class to convert StatisticsCollector data to SimulationReportDto for API communication.
 */
@Component
public class SimulationReportMapper {

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
