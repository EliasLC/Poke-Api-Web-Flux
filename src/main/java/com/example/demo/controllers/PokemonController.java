package com.example.demo.controllers;

import com.example.demo.assemblers.PokemonAssembler;
import com.example.demo.models.Pokemon;
import com.example.demo.models.PokemonDto;
import com.example.demo.requestbodies.AddPokemonBody;
import com.example.demo.services.PokemonService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;
    private final PokemonAssembler pokemonAssembler;
    private final PagedResourcesAssembler<Pokemon> pokemonPagedResourcesAssembler;

    public PokemonController(
            PokemonService pokemonService,
            PokemonAssembler pokemonAssembler,
            PagedResourcesAssembler<Pokemon> pokemonPagedResourcesAssembler
            ){
        this.pokemonService = pokemonService;
        this.pokemonAssembler = pokemonAssembler;
        this.pokemonPagedResourcesAssembler = pokemonPagedResourcesAssembler;
    }

    @PostMapping
    public ResponseEntity<?> savePokemon(
            @RequestBody AddPokemonBody addPokemonBody
            ) {
        EntityModel<Pokemon> result = this.pokemonAssembler.toModel(
                this.pokemonService.save(addPokemonBody)
        );

        return ResponseEntity
                .created(result.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(result);
    }

   @GetMapping("/{nickName}")
    public EntityModel<PokemonDto> getByNickName(
            @PathVariable final String nickName
    ) {
        PokemonDto pokemon = this.pokemonService.getByNickName(nickName);

        return EntityModel.of(
                pokemon,
                linkTo(methodOn(PokemonController.class).getByNickName(pokemon.getNickName())).withSelfRel()
        );
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Pokemon>>> getPokemons(
            @RequestParam(defaultValue = "") final String species,
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "10") final Integer size
    ) {
        Page<Pokemon> pokemonsPage = this.pokemonService.getPokemons(species, page, size);

        PagedModel<EntityModel<Pokemon>> pokemons = this.pokemonPagedResourcesAssembler.toModel(pokemonsPage, this.pokemonAssembler);

        return ResponseEntity.ok(pokemons);
    }



}
