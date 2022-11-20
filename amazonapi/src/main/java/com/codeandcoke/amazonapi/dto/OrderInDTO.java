package com.codeandcoke.amazonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInDTO {

    private int quantity;
    private long productId;
}
