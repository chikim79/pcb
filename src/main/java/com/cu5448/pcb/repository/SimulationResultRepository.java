package com.cu5448.pcb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cu5448.pcb.entity.SimulationResult;

/**
 * Spring Data JPA repository for SimulationResult entities. Provides CRUD operations and custom
 * queries for simulation result data.
 */
@Repository
public interface SimulationResultRepository extends JpaRepository<SimulationResult, Long> {

    /**
     * Find the most recent simulation result for a specific PCB type.
     *
     * @param pcbType the PCB type to search for
     * @return the most recent SimulationResult for the given PCB type, if any
     */
    Optional<SimulationResult> findFirstByPcbTypeOrderByCreatedAtDesc(String pcbType);

    /**
     * Get the latest simulation result for each PCB type.
     *
     * @return list of the most recent SimulationResult for each PCB type
     */
    @Query(
            "SELECT sr FROM SimulationResult sr WHERE sr.createdAt = "
                    + "(SELECT MAX(sr2.createdAt) FROM SimulationResult sr2 WHERE sr2.pcbType ="
                    + " sr.pcbType)")
    List<SimulationResult> findLatestResultForEachPcbType();
}
