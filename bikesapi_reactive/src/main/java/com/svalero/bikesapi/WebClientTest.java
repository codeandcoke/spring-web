package com.svalero.bikesapi;

import com.svalero.bikesapi.domain.Bike;
import com.svalero.bikesapi.domain.User;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

public class WebClientTest {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Flux<User> productsFlux = webClient.get()
            .uri("/users")
            .retrieve()
            .bodyToFlux(User.class);

        productsFlux.doOnError((System.out::println))
            .subscribeOn(Schedulers.fromExecutor(Executors.newCachedThreadPool()))
            .doOnComplete(() -> System.out.println("Terminado"))
            .subscribe((user) -> {
                System.out.println("Haciendo algo con " + user.getName() + " . . .");
            });
    }
}
