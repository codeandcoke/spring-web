package com.sanvalero.myshop.repository;

import com.sanvalero.myshop.domain.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repositorio de lineas de detalle
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {

    Set<OrderDetail> findAll();
}
