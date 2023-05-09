package kz.zhelezyaka.client;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
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