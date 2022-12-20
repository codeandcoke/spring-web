package com.codeandcoke.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotNull
    @NotBlank
    private String title;
    @Column
    private String author;
    @Column
    private String category;
    @Column(name = "page_count")
    @Positive
    private int pageCount;
    @Column
    private double latitude;
    @Column
    private double longitude;
}
