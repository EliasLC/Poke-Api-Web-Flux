package com.example.demo.assemblers;

import com.example.demo.controllers.DeveloperController;
import com.example.demo.controllers.PokeApiController;
import com.example.demo.controllers.PokemonController;
import com.example.demo.models.DeveloperDto;
import com.example.demo.requestbodies.AddPokemonBody;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DeveloperAssembler implements RepresentationModelAssembler<DeveloperDto, EntityModel<DeveloperDto>> {

    @Override
    public EntityModel<DeveloperDto> toModel(DeveloperDto entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(DeveloperController.class).getDeveloperInfo()).withSelfRel(),
                linkTo(methodOn(PokeApiController.class).getByIdentifier(String.valueOf(1))).withRel("pokemonByNumber"),
                linkTo(methodOn(PokeApiController.class).getByIdentifier(String.valueOf("bulbasaur"))).withRel("pokemonByName"),
                linkTo(methodOn(PokeApiController.class).getPokemonsByGeneration(1)).withRel("pokemonsByGeneration"),
                linkTo(methodOn(PokeApiController.class).getByRange(1, 5)).withRel("pokemonsByRange"),
                linkTo(methodOn(PokemonController.class).savePokemon(new AddPokemonBody())).withRel("savePokemon"),
                linkTo(methodOn(PokemonController.class).getByNickName("nickname")).withRel("findByNickName"),
                linkTo(methodOn(PokemonController.class).getPokemons("species", 0, 10)).withRel("pokemonBySpecies")
        );
    }
}
