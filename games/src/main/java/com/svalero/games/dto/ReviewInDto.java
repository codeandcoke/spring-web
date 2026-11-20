package com.svalero.games.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInDto {

    @Min(value = 0)
    private int rate;
    private String comment;
    private long gameId;
}
