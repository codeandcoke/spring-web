package com.svalero.bikesapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "routes")
public class Route {

    @Id
    private String id;
    @Field(name = "start_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate startDate;
    @Field(name = "end_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate endDate;
    @Field
    @PositiveOrZero
    private int kilometers;

    @ManyToOne
    @JoinColumn(name = "bike_id")
    @JsonBackReference(value = "bike-route")
    private Bike bike;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-route")
    private User user;
}
