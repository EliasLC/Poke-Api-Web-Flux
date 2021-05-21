package com.example.demo.controllers;

import com.example.demo.models.PokemonApiDto;
import com.example.demo.services.PokemonApiService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/pokeapi")
public class PokeApiController {

    private final PokemonApiService pokemonApiService;

    public PokeApiController(PokemonApiService pokemonApiService) {
        this.pokemonApiService = pokemonApiService;
    }

    @GetMapping("/generation/{generation}")
    public ResponseEntity<List<PokemonApiDto>> getPokemonsByGeneration(
            @PathVariable Integer generation
    ) {
        return ResponseEntity.ok(this.pokemonApiService.getByGeneration(generation));
    }

    @GetMapping("/{identifier}")
    public EntityModel<PokemonApiDto> getByIdentifier(
            @PathVariable final String identifier
    ) {
            PokemonApiDto pokemonApiDto = this.pokemonApiService.getByIdentifier(identifier);

            return EntityModel.of(pokemonApiDto,
                    linkTo(methodOn(PokeApiController.class).getByIdentifier(identifier)).withSelfRel()
            );
    }

    @GetMapping
    public ResponseEntity<List<PokemonApiDto>> getByRange(
            @RequestParam(defaultValue = "0") final Integer start,
            @RequestParam(defaultValue = "10") final Integer end
    ){
        List<PokemonApiDto> result = this.pokemonApiService.getByRange(start, end);

        return ResponseEntity.ok(result);
    }

}
