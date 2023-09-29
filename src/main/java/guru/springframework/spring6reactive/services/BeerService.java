package guru.springframework.spring6reactive.services;

import guru.springframework.spring6reactive.model.BeerDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


public interface BeerService {

  Flux<BeerDto> listBeers();
}
