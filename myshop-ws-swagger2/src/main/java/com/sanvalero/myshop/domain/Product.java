package com.sanvalero.myshop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Schema(description = "Fecha de registro del producto", example = "01-03-2021")
    @Column(name = "creation_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime creationDate;
}
