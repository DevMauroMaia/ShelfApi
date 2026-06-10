package com.devmauro.ShelfAPI.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiErrorResponse {
    private String message;
    private int status;
}
