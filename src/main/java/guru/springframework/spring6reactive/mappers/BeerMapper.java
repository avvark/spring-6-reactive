package guru.springframework.spring6reactive.mappers;

import guru.springframework.spring6reactive.domain.Beer;
import guru.springframework.spring6reactive.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

  Beer beerDtoToBeer(BeerDto dto);

  BeerDto beerToBeerDto(Beer beer);
}
