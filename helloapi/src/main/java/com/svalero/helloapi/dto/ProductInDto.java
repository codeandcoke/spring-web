package com.svalero.helloapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInDto {

    @NotNull(message = "El nombre es obligatorio")
    private String name;
    private String description;
    @Min(value = 0, message = "El precio debe ser mayor que cero")
    private float price;
}
