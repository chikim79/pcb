package com.cu5448.pcb.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.cu5448.pcb.config.SimulationProperties;
import com.cu5448.pcb.factory.PCBFactory;
import com.cu5448.pcb.model.PCB;
import com.cu5448.pcb.station.Station;

import lombok.RequiredArgsConstructor;

/**
 * Assembly Line Service using Spring Dependency Injection. Station beans are injected as an ordered
 * list, eliminating the need for manual station creation and initialization.
 */
@Service
@RequiredArgsConstructor
public class AssemblyLine {

    private final List<Station> stations;

    private final PCBFactory factory;

    private final SimulationProperties simulationProperties;

    private final ApplicationContext applicationContext;

    public void processPCB(PCB pcb, StatisticsCollector stats) {
        for (Station station : stations) {
            station.process(pcb, stats);
            if (pcb.isFailed()) {
                break;
            }
        }
    }

    public StatisticsCollector runSimulation(String pcbType, int quantity) {
        // Get a new prototype instance of StatisticsCollector for this simulation
        StatisticsCollector stats = applicationContext.getBean(StatisticsCollector.class);

        for (int i = 0; i < quantity; i++) {
            PCB pcb = factory.createPCB(pcbType);
            stats.recordSubmission();

            processPCB(pcb, stats);

            if (!pcb.isFailed()) {
                stats.recordCompletion();
            }
        }

        return stats;
    }

    public StatisticsCollector runSimulation(String pcbType) {
        return runSimulation(pcbType, simulationProperties.getPcbQuantity());
    }

    public List<Station> getStations() {
        return List.copyOf(stations);
    }
}
