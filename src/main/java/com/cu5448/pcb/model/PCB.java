package com.cu5448.pcb.model;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 * Abstract PCB Model using Lombok
 * 
 * @Getter generates getters for all fields
 * @ToString generates toString method
 */
@Getter
@ToString
public abstract class PCB {
    private final String id;
    private final String type;
    private boolean failed;
    private String failureReason;

    public PCB(String type) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.failed = false;
        this.failureReason = null;
    }

    public void setFailed(String reason) {
        this.failed = true;
        this.failureReason = reason;
    }

    public abstract double getDefectRate(String stationType);
}