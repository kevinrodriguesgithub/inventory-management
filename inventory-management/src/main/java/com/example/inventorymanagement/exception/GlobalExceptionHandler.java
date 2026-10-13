package com.example.inventorymanagement.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Map<String, Object>>
            handleItemNotFound(ItemNotFoundException ex) {

        return buildError(
                HttpStatus.NOT_FOUND,
                ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>>
            handleInvalidInput(IllegalArgumentException ex) {

        return buildError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>>
            handleInvalidJson(HttpMessageNotReadableException ex) {

        return buildError(
                HttpStatus.BAD_REQUEST,
                "Invalid or malformed JSON request body");
    }

    // Handle invalid URL parameter types
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>>
            handleInvalidParameter(
                MethodArgumentTypeMismatchException ex) {

        return buildError(
                HttpStatus.BAD_REQUEST,
                "Invalid value for parameter: " + ex.getName());
    }

    private ResponseEntity<Map<String, Object>> buildError(
            HttpStatus status, String message) {

        Map<String, Object> error = new LinkedHashMap<>();

        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", status.value());
        error.put("error", status.getReasonPhrase());
        error.put("message", message);

        return ResponseEntity.status(status).body(error);
    }
}
