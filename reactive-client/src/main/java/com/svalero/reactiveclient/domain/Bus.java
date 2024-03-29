package com.svalero.reactiveclient.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

    private String id;
    private String code;
    private int maxSpeed;
}
