package kz.zhelezyaka.client;

import reactor.core.publisher.Flux;

import java.util.Map;

public interface BeerClient {
    Flux<String> listBeer();

    Flux<Map> listBeerMap();
}
