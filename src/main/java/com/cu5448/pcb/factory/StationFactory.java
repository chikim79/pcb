package com.cu5448.pcb.factory;

import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.cu5448.pcb.config.StationProperties;
import com.cu5448.pcb.station.*;

import lombok.RequiredArgsConstructor;

/**
 * Abstract Factory for creating PCB manufacturing stations using Spring Dependency Injection. This
 * factory uses a registry pattern to eliminate the need for individual creation methods for each
 * station type, making it more extensible and following the Abstract Factory pattern.
 */
@Component
@RequiredArgsConstructor
public class StationFactory {

    private final StationProperties stationProperties;

    // Registry of station constructors using method references
    private final Map<String, Function<Double, Station>> stationRegistry =
            Map.of(
                    "ApplySolderPaste", ApplySolderPasteStation::new,
                    "PlaceComponents", PlaceComponentsStation::new,
                    "ReflowSolder", ReflowSolderStation::new,
                    "OpticalInspection", OpticalInspectionStation::new,
                    "HandSoldering", HandSolderingStation::new,
                    "Cleaning", CleaningStation::new,
                    "Depanelization", DepanelizationStation::new,
                    "Test", TestStation::new);

    /**
     * Creates a station by type name using the Abstract Factory pattern. This method uses a
     * registry of constructor method references to eliminate the need for individual creation
     * methods.
     *
     * @param stationType the type of station to create (e.g., "ApplySolderPaste", "Test")
     * @return Station instance of the specified type
     * @throws IllegalArgumentException if station type is unknown
     */
    public Station createStation(String stationType) {
        Function<Double, Station> constructor = stationRegistry.get(stationType);
        if (constructor == null) {
            throw new IllegalArgumentException("Unknown station type: " + stationType);
        }
        return constructor.apply(stationProperties.getFailureRate());
    }
}
