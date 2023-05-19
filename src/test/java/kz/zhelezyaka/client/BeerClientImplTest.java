package kz.zhelezyaka.client;

import kz.zhelezyaka.model.BeerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;


@SpringBootTest
class BeerClientImplTest {

    AtomicBoolean atomicBoolean;

    @Autowired
    BeerClient client;

    @BeforeEach
    void setUp() {
        atomicBoolean = new AtomicBoolean(false);
    }

    @Test
    void testDeleteBeer() {
        client.listBeerDTO()
                .next()
                .flatMap(dto -> client.deleteBeer(dto))
                .doOnSuccess(mt -> atomicBoolean.set(true))
                .subscribe();
        await().untilTrue(atomicBoolean);
    }

    @Test
    void testPatchBeer() {
        final String NAME = "New Name Beer";

        client.listBeerDTO()
                .next()
                .map(beerDTO ->
                        BeerDTO.builder().beerName(NAME).id(beerDTO.getId()).build())
                .flatMap(dto -> client.patchBeer(dto))
                .subscribe(byIdDTO -> {
                    System.out.println(byIdDTO.toString());
                    atomicBoolean.set(true);
                });
        await().untilTrue(atomicBoolean);
    }


    @Test
    void testUpdateBeer() {
        final String NAME = "New Name Beer";
        client.listBeerDTO()
                .next()
                .doOnNext(beerDTO -> beerDTO.setBeerName(NAME))
                .flatMap(dto -> client.updateBeer(dto))
                .subscribe(byIdDTO -> {
                    System.out.println(byIdDTO.toString());
                    atomicBoolean.set(true);
                });
        await().untilTrue(atomicBoolean);
    }

    @Test
    void testCreateBeer() {
        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("12.90"))
                .beerName("Derbes")
                .beerStyle("ALE")
                .quantityOnHand(456)
                .upc("12389")
                .build();
        client.createBeer(newDTO)
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });
        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerByBeerStyle() {
        client.getBeerByBeerStyle("ALE")
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });
        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerById() {
        client.listBeerDTO()
                .flatMap(dto -> client.getBeerById(dto.getId()))
                .subscribe(byIdDto -> {
                    System.out.println(byIdDto.getBeerName());
                    atomicBoolean.set(true);
                });
        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerDTO() {
        client.listBeerDTO().subscribe(dto -> {
            System.out.println(dto.getBeerName());
            atomicBoolean.set(true);
        });
        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerJson() {
        client.listBeersJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });
        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetMap() {
        client.listBeerMap().subscribe(x -> {
            System.out.println(x);
            atomicBoolean.set(true);
        });
        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeer() {
        client.listBeer().subscribe(x -> {
            System.out.println(x);
            atomicBoolean.set(true);
        });
        await().untilTrue(atomicBoolean);
    }
}