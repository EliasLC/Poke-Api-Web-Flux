package com.example.demo.services;


import com.example.demo.models.PokemonApiDto;
import com.example.demo.repositories.PokemonApiRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PokemonApiService {

    private final PokemonApiRepository pokemonApiRepository;

    public PokemonApiService(PokemonApiRepository pokemonApiRepository) {
        this.pokemonApiRepository = pokemonApiRepository;
    }

    /**
     *
     * @param generation
     * @return
     */
    public List<PokemonApiDto> getByGeneration(Integer generation) {
        LinkedHashMap<String, Object> generationResult = this.pokemonApiRepository.getSpeciesByGeneration(generation).block();

        List<Map<String, Object>> pokemonSpecies = (ArrayList<Map<String, Object>>) generationResult.get("pokemon_species");

        List<Mono<PokemonApiDto>> pokemonRequests = pokemonSpecies.stream()
                .map(
                        species -> {
                            return this.pokemonApiRepository.getPokemonByIdentifier((String) species.get("name"));
                        }
                ).collect(Collectors.toList());

       Mono<List<PokemonApiDto>> request = Flux.fromIterable(pokemonRequests)
               .flatMap(
                        pokemonRequest -> pokemonRequest
               ).collectSortedList(
                       Comparator.comparing(PokemonApiDto::getId)
               );


        return request.block();
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public List<PokemonApiDto> getByRange(Integer start, Integer end) {
        List<Mono<PokemonApiDto>> list = new ArrayList<>();

        for(int i= start; i <= end; i++) {
            list.add(this.pokemonApiRepository.getPokemonByIdentifier(String.valueOf(i)));
        }

        Mono<List<PokemonApiDto>> request = Flux.fromIterable(list)
                .flatMap(
                        pokemonRequest -> pokemonRequest
                ).collectSortedList(
                        Comparator.comparing(PokemonApiDto::getId)
                );

        return request.block();
    }

    /**
     *
     * @param name
     * @return
     */
    public PokemonApiDto getByIdentifier(String name) {
         return this.pokemonApiRepository.getPokemonByIdentifier(name).block();
    }

}
