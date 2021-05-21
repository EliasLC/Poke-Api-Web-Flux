package com.example.demo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Pokemon implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nickName;

    private String species;

    private int baseAttack;

    private int baseDefence;

    private int baseHealth;

    public Pokemon() { }

    public Pokemon(Long id, String nickName, String species, int baseAttack, int baseDefence, int baseHealth) {
        this.id = id;
        this.nickName = nickName;
        this.species = species;
        this.baseAttack = baseAttack;
        this.baseDefence = baseDefence;
        this.baseHealth = baseHealth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseDefence() {
        return baseDefence;
    }

    public void setBaseDefence(int baseDefence) {
        this.baseDefence = baseDefence;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return baseAttack == pokemon.baseAttack && baseDefence == pokemon.baseDefence && baseHealth == pokemon.baseHealth && Objects.equals(id, pokemon.id) && Objects.equals(nickName, pokemon.nickName) && Objects.equals(species, pokemon.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, species, baseAttack, baseDefence, baseHealth);
    }
}
