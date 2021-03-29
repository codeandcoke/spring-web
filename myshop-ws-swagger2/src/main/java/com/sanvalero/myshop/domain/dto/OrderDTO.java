package com.sanvalero.myshop.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO {

    private List<String> products;
    private float subtotal;
    private float price;
}
