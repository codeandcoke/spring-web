package com.svalero.games.domain;

import jakarta.persistence.*;
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
    private String title;
    @Column
    private String publisher;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column
    private LocalDate registeredDate;
    @Column
    private float price;
    @Column
    private String description;
    @Column
    private int rate;
    @Column
    private int maxUsers;
}
