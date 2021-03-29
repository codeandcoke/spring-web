package com.sanvalero.myshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String number;
    @Column
    private LocalDate date;
    @Column
    private float price;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> details;

    public Order() {
        details = new ArrayList<>();
    }

    public void addDetail(OrderDetail detail) {
        details.add(detail);
    }
}
