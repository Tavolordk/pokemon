package com.atex.it.adapters;

import com.atex.it.models.ElectricPokemon;
import com.atex.it.models.Pokemon;
import com.atex.it.models.WaterPokemon;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PokemonTypeAdapter implements JsonDeserializer<Pokemon> {
    @Override
    public Pokemon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("Electric".equalsIgnoreCase(type)) {
            return context.deserialize(jsonObject, ElectricPokemon.class);
        } else if ("Water".equalsIgnoreCase(type)) {
            return context.deserialize(jsonObject, WaterPokemon.class);
        }

        throw new JsonParseException("Unknown Pokémon type: " + type);
    }
}

