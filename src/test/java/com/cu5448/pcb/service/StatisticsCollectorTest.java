package com.cu5448.pcb.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class StatisticsCollectorTest {

    private StatisticsCollector stats;

    @BeforeEach
    void setUp() {
        stats = new StatisticsCollector();
    }

    @Test
    void testInitialState() {
        assertEquals(0, stats.getPcbsSubmitted());
        assertEquals(0, stats.getCompletedPCBs());
        assertTrue(stats.getDefectFailures().isEmpty());
        assertTrue(stats.getStationFailures().isEmpty());
    }

    @Test
    void testRecordSubmission() {
        stats.recordSubmission();
        stats.recordSubmission();
        assertEquals(2, stats.getPcbsSubmitted());
    }

    @Test
    void testRecordCompletion() {
        stats.recordCompletion();
        stats.recordCompletion();
        assertEquals(2, stats.getCompletedPCBs());
    }

    @Test
    void testRecordDefectFailure() {
        stats.recordDefectFailure("PlaceComponents");
        stats.recordDefectFailure("PlaceComponents");
        stats.recordDefectFailure("Test");
        
        assertEquals(2, stats.getDefectFailures().get("PlaceComponents"));
        assertEquals(1, stats.getDefectFailures().get("Test"));
    }

    @Test
    void testRecordStationFailure() {
        stats.recordStationFailure("ApplySolderPaste");
        stats.recordStationFailure("Cleaning");
        
        assertEquals(1, stats.getStationFailures().get("ApplySolderPaste"));
        assertEquals(1, stats.getStationFailures().get("Cleaning"));
    }

    @Test
    void testGenerateReport() {
        stats.recordSubmission();
        stats.recordSubmission();
        stats.recordCompletion();
        stats.recordDefectFailure("Test");
        stats.recordStationFailure("Cleaning");
        
        String report = stats.generateReport("Test Board");
        
        assertTrue(report.contains("PCB type: Test Board"));
        assertTrue(report.contains("PCBs run: 2"));
        assertTrue(report.contains("Total failed PCBs: 1"));
        assertTrue(report.contains("Total PCBs produced: 1"));
        assertTrue(report.contains("Test (ICT or Flying Probe) 1"));
        assertTrue(report.contains("Cleaning: 1"));
    }
}