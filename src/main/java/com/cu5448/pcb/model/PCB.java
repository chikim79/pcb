package com.cu5448.pcb.model;

import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

/**
 * Abstract PCB Model using Lombok @Getter generates getters for all fields @ToString generates
 * toString method
 */
@Getter
@ToString
public abstract class PCB {

    private final String id;

    private final String type;

    private boolean failed = false;

    private String failureReason = null;

    public PCB(String type) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
    }

    public void setFailed(String reason) {
        this.failed = true;
        this.failureReason = reason;
    }

    public abstract double getDefectRate(String stationType);

    public abstract DefectRates getDefectRates();
}
