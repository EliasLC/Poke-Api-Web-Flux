package com.example.demo.assemblers;

import com.example.demo.controllers.PokemonController;
import com.example.demo.models.Pokemon;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PokemonAssembler implements RepresentationModelAssembler<Pokemon, EntityModel<Pokemon>> {

    @Override
    public EntityModel<Pokemon> toModel(Pokemon entity) {
        return EntityModel.of(entity,
               linkTo(methodOn(PokemonController.class).getByNickName(entity.getNickName())).withSelfRel()
        );
    }
}
