package com.cu5448.pcb.factory;

import org.springframework.stereotype.Component;

import com.cu5448.pcb.config.StationProperties;
import com.cu5448.pcb.station.*;

import lombok.RequiredArgsConstructor;

/**
 * Factory for creating PCB manufacturing stations using Spring Dependency Injection. This factory
 * encapsulates station creation logic and uses injected configuration to set failure rates.
 */
@Component
@RequiredArgsConstructor
public class StationFactory {

    private final StationProperties stationProperties;

    /**
     * Creates an Apply Solder Paste station with configured failure rate.
     *
     * @return ApplySolderPasteStation instance
     */
    public Station createApplySolderPasteStation() {
        return new ApplySolderPasteStation(stationProperties.getFailureRate());
    }

    /**
     * Creates a Place Components station with configured failure rate.
     *
     * @return PlaceComponentsStation instance
     */
    public Station createPlaceComponentsStation() {
        return new PlaceComponentsStation(stationProperties.getFailureRate());
    }

    /**
     * Creates a Reflow Solder station with configured failure rate.
     *
     * @return ReflowSolderStation instance
     */
    public Station createReflowSolderStation() {
        return new ReflowSolderStation(stationProperties.getFailureRate());
    }

    /**
     * Creates an Optical Inspection station with configured failure rate.
     *
     * @return OpticalInspectionStation instance
     */
    public Station createOpticalInspectionStation() {
        return new OpticalInspectionStation(stationProperties.getFailureRate());
    }

    /**
     * Creates a Hand Soldering station with configured failure rate.
     *
     * @return HandSolderingStation instance
     */
    public Station createHandSolderingStation() {
        return new HandSolderingStation(stationProperties.getFailureRate());
    }

    /**
     * Creates a Cleaning station with configured failure rate.
     *
     * @return CleaningStation instance
     */
    public Station createCleaningStation() {
        return new CleaningStation(stationProperties.getFailureRate());
    }

    /**
     * Creates a Depanelization station with configured failure rate.
     *
     * @return DepanelizationStation instance
     */
    public Station createDepanelizationStation() {
        return new DepanelizationStation(stationProperties.getFailureRate());
    }

    /**
     * Creates a Test station with configured failure rate.
     *
     * @return TestStation instance
     */
    public Station createTestStation() {
        return new TestStation(stationProperties.getFailureRate());
    }

    /**
     * Creates a station by type name. Useful for dynamic station creation.
     *
     * @param stationType the type of station to create (e.g., "ApplySolderPaste", "Test")
     * @return Station instance of the specified type
     * @throws IllegalArgumentException if station type is unknown
     */
    public Station createStationByType(String stationType) {
        return switch (stationType) {
            case "ApplySolderPaste", "applySolderPaste" -> createApplySolderPasteStation();
            case "PlaceComponents", "placeComponents" -> createPlaceComponentsStation();
            case "ReflowSolder", "reflowSolder" -> createReflowSolderStation();
            case "OpticalInspection", "opticalInspection" -> createOpticalInspectionStation();
            case "HandSoldering", "handSoldering" -> createHandSolderingStation();
            case "Cleaning", "cleaning" -> createCleaningStation();
            case "Depanelization", "depanelization" -> createDepanelizationStation();
            case "Test", "test" -> createTestStation();
            default -> throw new IllegalArgumentException("Unknown station type: " + stationType);
        };
    }
}
