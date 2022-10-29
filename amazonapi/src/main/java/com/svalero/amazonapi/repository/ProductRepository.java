package com.svalero.amazonapi.repository;

import com.svalero.amazonapi.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();
    Product findByName(String name);
    List<Product> findByPrice(float price);
    List<Product> findByCategory(String category);
    List<Product> findByPriceAndCategory(float price, String category);
}
