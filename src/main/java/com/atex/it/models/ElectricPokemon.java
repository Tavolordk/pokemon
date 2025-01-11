package com.atex.it.models;

public class ElectricPokemon extends Pokemon {
    private int voltage;

    public ElectricPokemon(Long id, String name, String imageUrl, int voltage) {
        super(id, name, imageUrl);
        this.voltage = voltage;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public String getType() {
        return "Electric";
    }
}