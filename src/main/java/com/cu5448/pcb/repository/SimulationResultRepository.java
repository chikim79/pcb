package com.cu5448.pcb.repository;

import java.time.LocalDateTime;
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
     * Find all simulation results for a specific PCB type, ordered by creation date (newest first).
     *
     * @param pcbType the PCB type to search for
     * @return list of SimulationResults for the given PCB type
     */
    List<SimulationResult> findByPcbTypeOrderByCreatedAtDesc(String pcbType);

    /**
     * Find all simulation results ordered by creation date (newest first).
     *
     * @return list of all SimulationResults
     */
    List<SimulationResult> findAllByOrderByCreatedAtDesc();

    /**
     * Find simulation results created after a specific date.
     *
     * @param createdAfter the date to filter by
     * @return list of SimulationResults created after the given date
     */
    List<SimulationResult> findByCreatedAtAfterOrderByCreatedAtDesc(LocalDateTime createdAfter);

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

    /**
     * Delete all simulation results for a specific PCB type.
     *
     * @param pcbType the PCB type to delete results for
     * @return number of deleted records
     */
    long deleteByPcbType(String pcbType);

    /**
     * Count simulation results by PCB type.
     *
     * @param pcbType the PCB type to count
     * @return number of simulation results for the given PCB type
     */
    long countByPcbType(String pcbType);

    /**
     * Check if any simulation results exist for a given PCB type.
     *
     * @param pcbType the PCB type to check
     * @return true if results exist, false otherwise
     */
    boolean existsByPcbType(String pcbType);
}
