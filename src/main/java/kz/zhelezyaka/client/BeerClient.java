package kz.zhelezyaka.client;

import reactor.core.publisher.Flux;

public interface BeerClient {
    Flux<String> listBeer();
}
