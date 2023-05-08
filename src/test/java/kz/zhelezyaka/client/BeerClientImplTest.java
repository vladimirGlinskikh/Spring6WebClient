package kz.zhelezyaka.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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