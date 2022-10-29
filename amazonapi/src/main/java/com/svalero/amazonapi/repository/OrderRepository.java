package com.svalero.amazonapi.repository;

import com.svalero.amazonapi.domain.Order;
import com.svalero.amazonapi.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();
    List<Order> findByUser(User user);
}
