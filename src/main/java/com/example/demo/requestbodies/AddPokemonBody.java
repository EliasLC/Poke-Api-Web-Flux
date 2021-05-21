package com.example.demo.requestbodies;

import java.io.Serializable;

public class AddPokemonBody implements Serializable {
    private String nickName;

    private String species;

    public AddPokemonBody() {}

    public AddPokemonBody(String nickName, String species) {
        this.nickName = nickName;
        this.species = species;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpicies(String species) {
        this.species = species;
    }
}
