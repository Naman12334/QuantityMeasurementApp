package com.app.quantitymeasurement.service;

import java.util.*;
import com.app.quantitymeasurement.DTO.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO q1, QuantityDTO q2, String email);

    QuantityDTO convert(QuantityDTO source, String targetUnit, String email);

    QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String email);

    QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String email);

    double divide(QuantityDTO q1, QuantityDTO q2, String email);
    
    List<QuantityMeasurementEntity> getHistory(String operation, String email);

    long getCount(String operation, String email);
}