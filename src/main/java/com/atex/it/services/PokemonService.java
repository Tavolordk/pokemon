package com.atex.it.services;

import java.util.List;

import com.atex.it.models.Pokemon;

public interface PokemonService {
    List<Pokemon> getAllPokemon();
    void createPokemon(Pokemon pokemon);
    void updatePokemon(Pokemon pokemon);
    void deletePokemon(Long id);
}
