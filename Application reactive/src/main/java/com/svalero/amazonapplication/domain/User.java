package com.svalero.amazonapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String dni;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
}
