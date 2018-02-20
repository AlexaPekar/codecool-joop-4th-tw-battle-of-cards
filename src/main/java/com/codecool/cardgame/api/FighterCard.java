package com.codecool.cardgame.api;

public class FighterCard extends Card {
    int intelligence;
    int damage;
    int defense;

    public FighterCard(String name,String type, int intelligence, int damage, int defense) {
        super(name,type);
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

    public void setDamage(int damage) { this.damage = damage; }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void increaseDamage(int damage) { this.damage += damage; }

    public void increaseIntelligence(int intelligence) { this.intelligence += intelligence; }

    public void increaseDefense(int defense) { this.defense += defense; }
}


