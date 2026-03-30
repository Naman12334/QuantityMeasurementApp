package com.app.quantitymeasurement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operand1;
    private String operand2;
    private String operation;
    private String result;

    // 🔥 NEW FIELD (VERY IMPORTANT)
    private String userEmail;

    private LocalDateTime createdAt;

    // 🔹 Default constructor
    public QuantityMeasurementEntity() {}

    // 🔹 OLD constructor (keep for safety)
    public QuantityMeasurementEntity(String operand1, String operand2,
                                     String operation, String result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;
    }

    // 🔥 NEW constructor (USE THIS NOW)
    public QuantityMeasurementEntity(String operand1, String operand2,
                                     String operation, String result,
                                     String userEmail) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;
        this.userEmail = userEmail;
    }

    // 🔹 Auto timestamp
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 🔹 Getters & Setters

    public Long getId() {
        return id;
    }

    public String getOperand1() {
        return operand1;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}