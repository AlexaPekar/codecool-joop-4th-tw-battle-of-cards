package com.codecool.cardgame.api;

public class Card{
    private String name;
    private int intelligence;
    private int damage;
    private int defense;

    public Card(String name, int intelligence, int damage, int defense) {
        this.name = name;
        this.intelligence = intelligence;
        this.damage = damage;
        this.defense = defense;
    }
    
    public String getName() {
        return name;
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
}
