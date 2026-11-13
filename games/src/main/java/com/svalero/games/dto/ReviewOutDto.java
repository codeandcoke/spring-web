package com.svalero.games.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewOutDto {

    private long id;
    private LocalDate date;
    private int rate;
    private String comment;
    private long gameId;
    private long userId;
}
