package com.codecool.cardgame.api;

public class FighterCard extends Card {
    private int intelligence;
    private int damage;
    private int defense;

    public FighterCard(String name,String type, int damage, int defense, int intelligence) {
        super(name,type);
        this.intelligence = intelligence;
        this.damage = damage;
        this.defense = defense;
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

    @Override
    public String toString() {
        return "Name: "+getName()+" (type: "+ "\u001B[31m" + getType()+ "\u001B[0m" + " damage: "+getDamage() +" defense: "+getDefense() +" intelligence: "+getIntelligence() + ")";
    }
}


