package com.app.quantitymeasurement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {

        String message = ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred.";

        // If it's login error → return 401
        if ("Invalid email or password".equals(message)) {
            return new ResponseEntity<>(Map.of("message", message), HttpStatus.UNAUTHORIZED);
        }

        // Handle specific measurement errors
        if (ex instanceof QuantityMeasurementException || 
            ex instanceof UnsupportedOperationException || 
            ex instanceof ArithmeticException ||
            ex instanceof IllegalArgumentException) {
            return new ResponseEntity<>(Map.of("message", message), HttpStatus.BAD_REQUEST);
        }

        // Other errors → 500
        return new ResponseEntity<>(Map.of("message", message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}