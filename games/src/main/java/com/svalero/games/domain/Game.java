package com.svalero.games.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "game")
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @Column
    private String publisher;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column
    private LocalDate registeredDate;
    @Column
    @Positive
    private float price;
    @Column
    private String description;
    @Column
    @Min(1)
    @Max(5)
    private int rate;
    @Column
    @Min(1)
    private int maxUsers;
}
