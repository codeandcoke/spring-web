package com.svalero.service;

import com.svalero.domain.Product;
import com.svalero.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Product> getProduct(String id) {
        return productRepository.findById(id);
    }

    public Mono<Product> save(Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        return productRepository.save(newProduct);
    }

    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }

    public Mono<Product> update(Mono<Product> product) {
        return product
                .flatMap((p) -> productRepository.findById(p.getId())
                        .flatMap(product1 -> {
                            product1.setName(p.getName());
                            return productRepository.save(product1);
                        }));
    }
}


