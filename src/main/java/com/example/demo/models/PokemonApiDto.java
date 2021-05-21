package com.example.demo.models;

import java.util.List;

import java.io.Serializable;

public class PokemonApiDto implements Serializable {

    private Integer id;

    private String name;

    private List<String> types;

    public PokemonApiDto() {}

    public PokemonApiDto(Integer id, String name, List<String> types) {
        this.id = id;
        this.name = name;
        this.types = types;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
