package com.svalero.bikesapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "users")
public class User {

    @Id
    private String id;
    @Field
    @NotNull
    @Pattern(regexp = "[0-9]{8}[A-Z]")
    private String dni;
    @Field
    @NotNull
    private String name;
    @Field
    @NotNull
    private String surname;
    @Field(name = "birth_date")
    private LocalDate birthDate;
    @Field
    @NotEmpty
    @Email
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Route> routes;
}
