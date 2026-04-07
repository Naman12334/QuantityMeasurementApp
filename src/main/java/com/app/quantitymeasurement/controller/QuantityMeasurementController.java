package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.DTO.QuantityDTO;
import java.util.List;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // 🔥 COMPARE
    @PostMapping("/compare")
    public boolean compare(@RequestBody QuantityDTO[] quantities,
                           HttpServletRequest request){

        String email = (String) request.getAttribute("userEmail");

        return service.compare(quantities[0], quantities[1], email);
    }

    // 🔥 CONVERT
    @PostMapping("/convert")
    public QuantityDTO convert(@RequestBody QuantityDTO quantity,
                               @RequestParam String targetUnit,
                               HttpServletRequest request){

        String email = (String) request.getAttribute("userEmail");

        return service.convert(quantity, targetUnit, email);
    }

    // 🔥 ADD
    @PostMapping("/add")
    public QuantityDTO add(@RequestBody QuantityDTO[] quantities,
                           HttpServletRequest request){

        String email = (String) request.getAttribute("userEmail");

        return service.add(quantities[0], quantities[1], email);
    }

    // 🔥 SUBTRACT
    @PostMapping("/subtract")
    public QuantityDTO subtract(@RequestBody QuantityDTO[] quantities,
                                HttpServletRequest request){

        String email = (String) request.getAttribute("userEmail");

        return service.subtract(quantities[0], quantities[1], email);
    }

    // 🔥 DIVIDE
    @PostMapping("/divide")
    public double divide(@RequestBody QuantityDTO[] quantities,
                         HttpServletRequest request){

        String email = (String) request.getAttribute("userEmail");

        return service.divide(quantities[0], quantities[1], email);
    }

    // 🔥 HISTORY (USER-SPECIFIC)
    @GetMapping("/history")
    public List<QuantityMeasurementEntity> getAllHistory(HttpServletRequest request){

        String email = (String) request.getAttribute("userEmail");

        return service.getAllHistory(email);
    }

    @GetMapping("/history/errored")
    public List<QuantityMeasurementEntity> getErroredHistory(HttpServletRequest request){

        String email = (String) request.getAttribute("userEmail");

        return service.getErroredHistory(email);
    }

    @GetMapping("/history/{operation}")
    public List<QuantityMeasurementEntity> getHistory(@PathVariable String operation,
                                                      HttpServletRequest request){

        String email = (String) request.getAttribute("userEmail");

        return service.getHistory(operation, email);
    }

    // 🔥 COUNT (USER-SPECIFIC)
    @GetMapping("/count/{operation}")
    public long getCount(@PathVariable String operation,
                         HttpServletRequest request){

        String email = (String) request.getAttribute("userEmail");

        return service.getCount(operation, email);
    }
}