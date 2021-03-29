package com.sanvalero.myshop.repository;

import com.sanvalero.myshop.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repositorio de pedidos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Set<Order> findAll();
}
