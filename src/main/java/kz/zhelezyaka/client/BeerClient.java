package kz.zhelezyaka.client;

import com.fasterxml.jackson.databind.JsonNode;
import kz.zhelezyaka.model.BeerDTO;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.Flow;

public interface BeerClient {
    Flux<String> listBeer();

    Flux<Map> listBeerMap();

    Flux<JsonNode> listBeersJsonNode();

    Flux<BeerDTO> listBeerDTO();

    Mono<BeerDTO> getBeerById(String id);
}
