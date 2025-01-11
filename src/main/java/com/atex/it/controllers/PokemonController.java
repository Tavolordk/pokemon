package com.atex.it.controllers;

import com.atex.it.adapters.PokemonTypeAdapter;
import com.atex.it.services.implementation.PokemonServiceImpl;
import com.atex.it.models.ElectricPokemon;
import com.atex.it.models.Pokemon;
import com.atex.it.models.WaterPokemon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static spark.Spark.*;

public class PokemonController {
    public static void main(String[] args) {
        PokemonServiceImpl pokemonService = new PokemonServiceImpl();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Pokemon.class, new PokemonTypeAdapter())
                .create();

        // Obtener todos los Pokémon
        get("/pokemon", (req, res) -> {
            res.type("application/json");
            return gson.toJson(pokemonService.getAllPokemon());
        });

        // Crear un nuevo Pokémon
        post("/pokemon", (req, res) -> {
            try {
                Pokemon pokemon = gson.fromJson(req.body(), Pokemon.class);

                if (pokemon instanceof ElectricPokemon) {
                    pokemonService.createPokemon((ElectricPokemon) pokemon);
                } else if (pokemon instanceof WaterPokemon) {
                    pokemonService.createPokemon((WaterPokemon) pokemon);
                } else {
                    res.status(400);
                    return "Invalid Pokémon type";
                }

                res.status(201);
                return "Pokemon created successfully";

            } catch (Exception e) {
                e.printStackTrace();
                res.status(500);
                return "Error creating Pokémon: " + e.getMessage();
            }
        });

        // Actualizar un Pokémon
        put("/pokemon/:id", (req, res) -> {
            Long id = Long.parseLong(req.params("id"));
            Pokemon pokemon = gson.fromJson(req.body(), Pokemon.class);
            pokemon.setId(id);

            if ("Electric".equalsIgnoreCase(pokemon.getType())) {
                ElectricPokemon electricPokemon = gson.fromJson(req.body(), ElectricPokemon.class);
                electricPokemon.setId(id);
                pokemonService.updatePokemon(electricPokemon);
            } else if ("Water".equalsIgnoreCase(pokemon.getType())) {
                WaterPokemon waterPokemon = gson.fromJson(req.body(), WaterPokemon.class);
                waterPokemon.setId(id);
                pokemonService.updatePokemon(waterPokemon);
            }
            res.status(200);
            return "Pokemon updated";
        });

        // Eliminar un Pokémon
        delete("/pokemon/:id", (req, res) -> {
            Long id = Long.parseLong(req.params("id"));
            pokemonService.deletePokemon(id);
            res.status(204);
            return "";
        });
    }
}
