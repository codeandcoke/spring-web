package com.sanvalero.myshop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Producto
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String category;
    @Column
    private float price;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
