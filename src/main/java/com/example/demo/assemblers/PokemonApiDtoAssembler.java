package com.example.demo.assemblers;


import com.example.demo.models.PokemonApiDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PokemonApiDtoAssembler implements RepresentationModelAssembler<PokemonApiDto, EntityModel<PokemonApiDto>> {

    @Override
    public EntityModel<PokemonApiDto> toModel(PokemonApiDto entity) {
        return EntityModel.of(entity
                //linkTo(methodOn(PokeApiController.class).getByIdentifier())
                );
    }

}
