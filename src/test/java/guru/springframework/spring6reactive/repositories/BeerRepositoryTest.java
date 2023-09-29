package guru.springframework.spring6reactive.repositories;

import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.spring6reactive.config.DatabaseConfig;
import guru.springframework.spring6reactive.domain.Beer;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryTest {

  @Autowired BeerRepository beerRepository;

  @Test
  void saveNewBeer() {
    beerRepository.save(getTestBeer())
        .subscribe(beer -> {
          System.out.println(beer);
        });
  }

  Beer getTestBeer() {
    return Beer.builder()
        .beerName("Space Dust")
        .beerStyle("IPA")
        .price(BigDecimal.TEN)
        .quantityOnHand(12)
        .upc("123231")
        .build();
  }
}
