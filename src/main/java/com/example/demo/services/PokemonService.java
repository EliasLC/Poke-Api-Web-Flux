package com.example.demo.services;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.models.Pokemon;
import com.example.demo.models.PokemonApiDto;
import com.example.demo.models.PokemonDto;
import com.example.demo.repositories.PokemonApiRepository;
import com.example.demo.repositories.PokemonRepository;

import com.example.demo.requestbodies.AddPokemonBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final PokemonApiRepository pokemonApiRepository;

    public PokemonService(PokemonRepository pokemonRepository, PokemonApiRepository pokemonApiRepository) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonApiRepository = pokemonApiRepository;
    }

    /**
     *
     * @param addPokemonBody
     * @return
     */
    public Pokemon save(AddPokemonBody addPokemonBody) {
        Pokemon newPokemon = new Pokemon();

        Random random = new Random();

        newPokemon.setNickName(addPokemonBody.getNickName());
        newPokemon.setSpecies(addPokemonBody.getSpecies());
        newPokemon.setBaseAttack(random.nextInt(15));
        newPokemon.setBaseDefence(random.nextInt(15));
        newPokemon.setBaseHealth(random.nextInt(15));

        return this.pokemonRepository.save(newPokemon);
    }

    /**
     *
     * @param nickName
     * @return
     */
   public PokemonDto getByNickName(String nickName) {
        Optional<Pokemon> pokemon = this.pokemonRepository.findByNickName(nickName);

        PokemonApiDto apiResult = this.pokemonApiRepository.getPokemonByIdentifier(pokemon.get().getSpecies()).block();

        PokemonDto pokemonDto = new PokemonDto();

        pokemonDto.setNumber((Integer) apiResult.getId());
        pokemonDto.setNickName(pokemon.get().getNickName());
        pokemonDto.setSpecies(pokemon.get().getSpecies());
        pokemonDto.setAttack(pokemon.get().getBaseAttack());
        pokemonDto.setDefence(pokemon.get().getBaseDefence());
        pokemonDto.setHealth(pokemon.get().getBaseHealth());
        pokemonDto.setTypes(apiResult.getTypes());

        return pokemonDto;
    }

    /**
     *
     * @param  species
     * @param page
     * @param size
     * @return
     */
    public Page<Pokemon> getPokemons(String species, Integer page, Integer size){
        return species.equals("") ?
                this.pokemonRepository.findAll(PageRequest.of(page, size)):
                this.pokemonRepository.findBySpecies(species, PageRequest.of(page, size));
    }
}
