package com.atex.it.services.implementation;

import java.util.List;

import com.atex.it.daos.PokemonDAO;
import com.atex.it.models.Pokemon;
import com.atex.it.services.PokemonService;


public class PokemonServiceImpl implements PokemonService {
    private final PokemonDAO pokemonDAO;

    public PokemonServiceImpl() {
        this.pokemonDAO = new PokemonDAO();
    }

    @Override
    public List<Pokemon> getAllPokemon() {
        try {
            return pokemonDAO.getAllPokemon();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching Pokémon", e);
        }
    }

    @Override
    public void createPokemon(Pokemon pokemon) {
        try {
            pokemonDAO.createPokemon(pokemon);
        } catch (Exception e) {
            throw new RuntimeException("Error creating Pokémon", e);
        }
    }

    @Override
    public void updatePokemon(Pokemon pokemon) {
        try {
            pokemonDAO.updatePokemon(pokemon);
        } catch (Exception e) {
            throw new RuntimeException("Error updating Pokémon", e);
        }
    }

    @Override
    public void deletePokemon(Long id) {
        try {
            pokemonDAO.deletePokemon(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting Pokémon", e);
        }
    }
}
