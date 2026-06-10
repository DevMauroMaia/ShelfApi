package com.devmauro.ShelfAPI.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    @NotBlank
    private String name;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;
    @NotNull
    @Min(0)
    private Integer quantity;
    @NotBlank
    private String category;
}
