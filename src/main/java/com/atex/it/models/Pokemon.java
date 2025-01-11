package com.atex.it.models;

import com.atex.it.adapters.PokemonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class Pokemon {
    private Long id;
    private String name;
    private String imageUrl;

    // Constructor
    public Pokemon(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }
    Gson gson = new GsonBuilder()
        .registerTypeAdapter(Pokemon.class, new PokemonTypeAdapter())
        .create();


    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Método abstracto
    public abstract String getType();
}
