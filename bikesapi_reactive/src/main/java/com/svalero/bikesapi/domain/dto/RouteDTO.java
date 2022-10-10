package com.svalero.bikesapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RouteDTO {

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate endDate;
    @PositiveOrZero
    private int kilometers;
    @NotNull
    private String userId;
    @NotNull
    private String bikeId;
}
