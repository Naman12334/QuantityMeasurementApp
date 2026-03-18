package com.QuantityMeasurementApp;

import com.QuantityMeasurementApp.controller.QuantityMeasurementController;
import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.repository.IQuantityMeasurementRepository;
import com.QuantityMeasurementApp.repository.QuantityMeasurementDatabaseRepository;
import com.QuantityMeasurementApp.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // ✅ STEP 1: initialize database repository
        IQuantityMeasurementRepository repository =
                new QuantityMeasurementDatabaseRepository();

        // ✅ STEP 2: initialize service
        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        // ✅ STEP 3: initialize controller
        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        // ✅ STEP 4: example inputs
        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(6, "INCHES", "Length");

        // ✅ STEP 5: perform operations
        QuantityDTO addResult = controller.performAddition(q1, q2);
        QuantityDTO subResult = controller.performSubtraction(q1, q2);
        double divResult = controller.performDivision(q1, q2);

        // ✅ STEP 6: print results
        System.out.println("Addition Result: " + addResult);
        System.out.println("Subtraction Result: " + subResult);
        System.out.println("Division Result: " + divResult);
    }
}