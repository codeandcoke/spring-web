package com.svalero.amazonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String name;
    private String description;
    private String category;
    private float price;
    private LocalDate creationDate;
    private String observations;
    private int quantity;
}
