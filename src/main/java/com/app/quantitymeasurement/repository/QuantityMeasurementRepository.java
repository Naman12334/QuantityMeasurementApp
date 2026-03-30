package com.app.quantitymeasurement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

@Repository
public interface QuantityMeasurementRepository 
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    // 🔥 Get history by operation + user
    List<QuantityMeasurementEntity> findByOperationAndUserEmail(String operation, String userEmail);

    // 🔥 Count by operation + user
    long countByOperationAndUserEmail(String operation, String userEmail);
}