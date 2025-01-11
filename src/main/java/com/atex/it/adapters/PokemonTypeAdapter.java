package com.atex.it.adapters;

import com.atex.it.models.ElectricPokemon;
import com.atex.it.models.Pokemon;
import com.atex.it.models.WaterPokemon;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PokemonTypeAdapter implements JsonSerializer<Pokemon>, JsonDeserializer<Pokemon> {
    @Override
    public Pokemon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String pokemonType = jsonObject.get("type").getAsString();

        if ("Electric".equalsIgnoreCase(pokemonType)) {
            return context.deserialize(json, ElectricPokemon.class);
        } else if ("Water".equalsIgnoreCase(pokemonType)) {
            return context.deserialize(json, WaterPokemon.class);
        } else {
            throw new JsonParseException("Unknown Pokémon type: " + pokemonType);
        }
    }

    @Override
    public JsonElement serialize(Pokemon src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof ElectricPokemon) {
            jsonObject.addProperty("type", "Electric");
        } else if (src instanceof WaterPokemon) {
            jsonObject.addProperty("type", "Water");
        }
        return jsonObject;
    }
}

