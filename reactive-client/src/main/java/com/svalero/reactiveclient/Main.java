package com.svalero.reactiveclient;

import com.svalero.reactiveclient.domain.Bus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://localhost:8080");

        Flux<Bus> busFlux = webClient.get()
                .uri("/buses")
                .retrieve()
                .bodyToFlux(Bus.class);

        busFlux
                .subscribeOn(Schedulers.fromExecutor(Executors.newCachedThreadPool()))
                .doOnComplete(() -> System.out.println("Fin"))
                .doOnError(throwable -> System.out.println("Se ha producido un error: " + throwable.getMessage()))
                .subscribe(bus -> {
                    System.out.println(bus.getCode());
                    // Añadir aqui lo que se necesite
                });

        System.out.println("Sigo aqui");
    }
}
