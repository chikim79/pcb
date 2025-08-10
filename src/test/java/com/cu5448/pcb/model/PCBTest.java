package com.cu5448.pcb.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PCBTest {

    @Test
    void testTestBoardDefectRates() {
        TestBoard board = new TestBoard();
        assertEquals("TestBoard", board.getType());
        assertEquals(0.05, board.getDefectRate("PlaceComponents"));
        assertEquals(0.10, board.getDefectRate("OpticalInspection"));
        assertEquals(0.05, board.getDefectRate("HandSoldering"));
        assertEquals(0.10, board.getDefectRate("Test"));
        assertEquals(0.0, board.getDefectRate("NonExistentStation"));
    }

    @Test
    void testSensorBoardDefectRates() {
        SensorBoard board = new SensorBoard();
        assertEquals("SensorBoard", board.getType());
        assertEquals(0.002, board.getDefectRate("PlaceComponents"));
        assertEquals(0.002, board.getDefectRate("OpticalInspection"));
        assertEquals(0.004, board.getDefectRate("HandSoldering"));
        assertEquals(0.004, board.getDefectRate("Test"));
    }

    @Test
    void testGatewayBoardDefectRates() {
        GatewayBoard board = new GatewayBoard();
        assertEquals("GatewayBoard", board.getType());
        assertEquals(0.004, board.getDefectRate("PlaceComponents"));
        assertEquals(0.004, board.getDefectRate("OpticalInspection"));
        assertEquals(0.008, board.getDefectRate("HandSoldering"));
        assertEquals(0.008, board.getDefectRate("Test"));
    }

    @Test
    void testPCBFailureMechanism() {
        TestBoard board = new TestBoard();
        assertFalse(board.isFailed());
        assertNull(board.getFailureReason());

        board.setFailed("Test failure");
        assertTrue(board.isFailed());
        assertEquals("Test failure", board.getFailureReason());
    }

    @Test
    void testPCBUniqueIds() {
        TestBoard board1 = new TestBoard();
        TestBoard board2 = new TestBoard();
        assertNotEquals(board1.getId(), board2.getId());
    }
}
