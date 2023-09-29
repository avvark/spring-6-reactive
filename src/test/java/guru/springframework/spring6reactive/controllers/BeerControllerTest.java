package guru.springframework.spring6reactive.controllers;

import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.spring6reactive.model.BeerDto;
import guru.springframework.spring6reactive.repositories.BeerRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
class BeerControllerTest {

  @Autowired
  WebTestClient webTestClient;

  @Test
  void testGetById() {
    webTestClient
        .get()
        .uri(BeerController.BEER_PATH_ID, 1)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().valueEquals("Content-type", "application/json")
        .expectBody(BeerDto.class);
  }

  @Test
  void testListBeers() {
    webTestClient
        .get()
        .uri(BeerController.BEER_PATH)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().valueEquals("Content-type", "application/json")
        .expectBody().jsonPath("$.size()").isEqualTo(3);
  }

  @Test
  void testCreateBeer() {
    webTestClient
        .post()
        .uri(BeerController.BEER_PATH)
        .body(Mono.justOrEmpty(BeerRepositoryTest.getTestBeer()), BeerDto.class)
        .header("Content-type", "application/json")
        .exchange()
        .expectStatus()
        .isCreated()
        .expectHeader()
        .valueEquals("Location", "http://localhost:8080/api/v1/beers/4");
  }

  @Test
  void testUpdateBeer() {
    webTestClient
        .put()
        .uri(BeerController.BEER_PATH_ID, 1)
        .body(Mono.justOrEmpty(BeerRepositoryTest.getTestBeer()), BeerDto.class)
        .header("Content-type", "application/json")
        .exchange()
        .expectStatus()
        .isNoContent();
  }
}
