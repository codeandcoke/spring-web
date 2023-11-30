package com.svalero.helloapi.repository;

import com.svalero.helloapi.domain.Order;
import com.svalero.helloapi.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findByUser(User user);
    Order findByNumber(String number);
}
