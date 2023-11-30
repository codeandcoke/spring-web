package com.svalero.helloapi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOutDto {

    private long id;
    private String name;
    private String description;
    private float price;
    private LocalDate registrationDate;
}
