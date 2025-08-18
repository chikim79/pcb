package com.cu5448.pcb.entity;

import java.time.LocalDateTime;
import java.util.Map;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA Entity for persisting simulation results to SQLite database. Stores all statistical data from
 * StatisticsCollector along with metadata.
 */
@Entity
@Table(name = "simulation_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pcb_type", nullable = false, length = 50)
    private String pcbType;

    @Column(name = "pcbs_submitted", nullable = false)
    private int pcbsSubmitted;

    @Column(name = "pcbs_completed", nullable = false)
    private int pcbsCompleted;

    @Column(name = "total_failed_pcbs", nullable = false)
    private int totalFailedPcbs;

    @Column(name = "station_failures", columnDefinition = "TEXT")
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Integer> stationFailures;

    @Column(name = "defect_failures", columnDefinition = "TEXT")
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Integer> defectFailures;

    @Column(name = "formatted_report", columnDefinition = "TEXT")
    private String formattedReport;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "simulation_quantity", nullable = false)
    private int simulationQuantity;

    /** Constructor for creating SimulationResult from StatisticsCollector data. */
    public SimulationResult(
            String pcbType,
            int pcbsSubmitted,
            int pcbsCompleted,
            Map<String, Integer> stationFailures,
            Map<String, Integer> defectFailures,
            String formattedReport,
            int simulationQuantity) {
        this.pcbType = pcbType;
        this.pcbsSubmitted = pcbsSubmitted;
        this.pcbsCompleted = pcbsCompleted;
        this.totalFailedPcbs = pcbsSubmitted - pcbsCompleted;
        this.stationFailures = stationFailures;
        this.defectFailures = defectFailures;
        this.formattedReport = formattedReport;
        this.simulationQuantity = simulationQuantity;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        // Calculate total failed PCBs if not set
        if (totalFailedPcbs == 0 && pcbsSubmitted > 0) {
            totalFailedPcbs = pcbsSubmitted - pcbsCompleted;
        }
    }
}
