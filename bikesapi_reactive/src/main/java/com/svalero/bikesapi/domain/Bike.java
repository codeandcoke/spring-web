package com.svalero.bikesapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "bikes")
public class Bike {

    @Id
    private String id;
    @Field
    private boolean available;
    @Field
    @NotNull
    @PositiveOrZero
    private int kilometers;
    @Field
    @NotNull
    private int battery;
    @Field(name = "baby_chair")
    private boolean babyChair;
    @Field(name = "station_id")
    @Positive
    private int stationId;

    @OneToMany(mappedBy = "bike")
    private List<Route> routes;
}
