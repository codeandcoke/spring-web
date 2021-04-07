package com.sanvalero.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String category;
    @Column(name = "page_count")
    private int pageCount;
    @Column
    private double latitude;
    @Column
    private double longitude;
}
