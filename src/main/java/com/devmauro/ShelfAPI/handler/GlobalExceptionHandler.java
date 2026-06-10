package com.devmauro.ShelfAPI.handler;

import com.devmauro.ShelfAPI.exception.ApiErrorResponse;
import com.devmauro.ShelfAPI.exception.ResourceAlreadyExistsException;
import com.devmauro.ShelfAPI.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleAlreadyExists(ResourceAlreadyExistsException exception) {
        ApiErrorResponse error = ApiErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException exception) {
        ApiErrorResponse error = ApiErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
