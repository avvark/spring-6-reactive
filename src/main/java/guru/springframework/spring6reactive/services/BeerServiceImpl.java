package guru.springframework.spring6reactive.services;

import guru.springframework.spring6reactive.mappers.BeerMapper;
import guru.springframework.spring6reactive.model.BeerDto;
import guru.springframework.spring6reactive.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

  private final BeerRepository beerRepository;

  private final BeerMapper beerMapper;

  @Override
  public Flux<BeerDto> listBeers() {
    return beerRepository.findAll().map(beerMapper::beerToBeerDto);
  }

  @Override
  public Mono<BeerDto> getBeerById(Integer beerId) {
    return beerRepository.findById(beerId).map(beerMapper::beerToBeerDto);
  }

  @Override
  public Mono<BeerDto> saveBeerDto(BeerDto beerDto) {
    return beerRepository.save(beerMapper.beerDtoToBeer(beerDto)).map(beerMapper::beerToBeerDto);
  }

  @Override
  public Mono<BeerDto> updateBeer(Integer beerId, BeerDto beerDto) {
    return beerRepository
        .findById(beerId)
        .map(
            foundBeer -> {
              foundBeer.setBeerName(beerDto.getBeerName());
              foundBeer.setBeerStyle(beerDto.getBeerStyle());
              foundBeer.setPrice(beerDto.getPrice());
              foundBeer.setUpc(beerDto.getUpc());
              foundBeer.setQuantityOnHand(beerDto.getQuantityOnHand());
              return foundBeer;
            }).flatMap(beerRepository::save)
        .map(beerMapper::beerToBeerDto);
  }
}
