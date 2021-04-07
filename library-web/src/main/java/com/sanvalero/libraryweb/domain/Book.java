package com.sanvalero.libraryweb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private long id;
    private String title;
    private String author;
    private String category;
    private int pageCount;
    private double latitude;
    private double longitude;
}
