package com.svalero.helloapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String number;
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @JsonBackReference("order_user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference("order_product")
    @ManyToMany
    @JoinTable(name = "products_orders",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Product> products;
}
