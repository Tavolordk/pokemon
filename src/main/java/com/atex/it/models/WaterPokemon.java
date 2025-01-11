package com.atex.it.models;

public class WaterPokemon extends Pokemon {
    private int swimSpeed;

    public WaterPokemon(Long id, String name, String imageUrl, int swimSpeed) {
        super(id, name, imageUrl);
        this.swimSpeed = swimSpeed;
    }

    public int getSwimSpeed() {
        return swimSpeed;
    }

    public void setSwimSpeed(int swimSpeed) {
        this.swimSpeed = swimSpeed;
    }

    @Override
    public String getType() {
        return "Water";
    }
}

