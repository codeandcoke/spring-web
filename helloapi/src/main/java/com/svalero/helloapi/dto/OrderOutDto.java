package com.svalero.helloapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOutDto {

    private long id;
    private String number;
    private LocalDate creationDate;
    private List<ProductOutDto> productIds;
}
