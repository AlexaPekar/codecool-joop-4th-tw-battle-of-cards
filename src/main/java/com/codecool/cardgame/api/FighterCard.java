package com.codecool.cardgame.api;

public class FighterCard extends Card {
    int intelligence;
    int damage;
    int defense;

    public FighterCard(String name, int intelligence, int damage, int defense) {
        super(name);
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}


