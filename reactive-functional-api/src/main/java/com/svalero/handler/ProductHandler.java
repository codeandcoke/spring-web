package com.svalero.handler;

import com.svalero.domain.Product;
import com.svalero.service.ProductService;
import com.svalero.util.ErrorResponse;
import com.svalero.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class ProductHandler {

    @Autowired
    private ProductService productService;

    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();
    private final Validator validator = new ProductValidator();

    public Mono<ServerResponse> getAllProducts(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.getAllProducts(), Product.class);
    }

    public Mono<ServerResponse> getProduct(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return productService.getProduct(id)
                .flatMap(p -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(p), Product.class))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(new ErrorResponse(404, "Product not found")), ErrorResponse.class));
    }

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
        Mono<Product> productToSave = serverRequest.bodyToMono(Product.class)
                .doOnNext(this::validate);

        return productToSave.flatMap(product ->
                ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(productService.save(product), Product.class));
    }

    private void validate(Product product) {
        Errors errors = new BeanPropertyBindingResult(product, "product");
        validator.validate(product, errors);
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getAllErrors().toString());
        }
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return productService.deleteProduct(id)
                .flatMap(product -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {
        return productService.update(serverRequest.bodyToMono(Product.class)).flatMap(product ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromObject(product)))
                .switchIfEmpty(notFound);
    }
}
