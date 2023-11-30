package com.svalero.helloapi.repository;

import com.svalero.helloapi.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByNameAndPrice(String name, float price);
}
