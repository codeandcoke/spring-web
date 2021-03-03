package com.sanvalero.myshop.domain;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Un producto del catálogo
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {

    @Schema(description = "Identificador del producto", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del producto", example = "Donuts", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Descripción del producto", example = "El mejor producto")
    @Column
    private String description;

    @Schema(description = "Nombre del producto", example = "Alimentación", required = true)
    @NotBlank
    @Column
    private String category;

    @Schema(description = "Precio del producto", example = "3.50", defaultValue = "0.00")
    @Column
    @Min(value = 0)
    private float price;

    @Schema(description = "Fecha de registro del producto", example = "2021-03-01")
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
