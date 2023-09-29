package guru.springframework.spring6reactive.services;

import guru.springframework.spring6reactive.model.BeerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BeerService {

  Flux<BeerDto> listBeers();

  Mono<BeerDto> getBeerById(Integer beerId);
}
