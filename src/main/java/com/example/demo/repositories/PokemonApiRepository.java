package com.example.demo.repositories;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.models.PokemonApiDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PokemonApiRepository {

    private final WebClient webClient;

    public PokemonApiRepository (WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://pokeapi.co/api/v2/").build();
    }

    public Mono<LinkedHashMap> getSpeciesByGeneration(Integer generationNumber) {
        return this.webClient.get()
                .uri("generation/{generation}", generationNumber)
                .retrieve()
                .bodyToMono(LinkedHashMap.class);
    }

    public Mono<PokemonApiDto> getPokemonByIdentifier(String identifier) {
        return this.webClient.get()
                .uri("pokemon/{species}", identifier)
                .retrieve()
                .bodyToMono(LinkedHashMap.class)
                .flatMap(
                        linkedHashMap -> {
                            List<HashMap<String, Object>> typesResult = (ArrayList<HashMap<String, Object>>) linkedHashMap.get("types");

                            List<String> types = typesResult.stream()
                                    .map(
                                            hashMap -> {
                                                HashMap<String, String> typeAux = (HashMap<String, String>) hashMap.get("type");
                                                return (String) typeAux.get("name");
                                            }
                                    ).collect(Collectors.toList());

                            HashMap<String, String> speciesInfo = (HashMap<String, String>) linkedHashMap.get("species");

                            PokemonApiDto pokemonApiDto = new PokemonApiDto();

                            pokemonApiDto.setId((Integer) linkedHashMap.get("id"));
                            pokemonApiDto.setName(speciesInfo.get("name"));
                            pokemonApiDto.setTypes(types);

                           return Mono.just(pokemonApiDto);
                        }
                );
    }

}
