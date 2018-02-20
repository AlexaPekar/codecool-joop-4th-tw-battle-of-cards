package com.codecool.cardgame.api;

import java.util.List;

public class SpellCard extends Card {
    private String effect;
    private int manaCost;

    public SpellCard(String name, String type, String effect,int manaCost) {
        super(name, type);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void increaseHp(Player player) {
        player.increaseHp(1000);
    }

    public void increaseDmg(Player player) {
        player.getChosenCard().increaseDamage(100);
    }

    public void increaseIntelligence(Player player) {
        player.getChosenCard().increaseIntelligence(10);
    }

    public void increaseDefense(Player player) {
        player.getChosenCard().increaseDefense(10);
    }

    public void returnFromGraveyard(Player player, List<Card> graveYard) {
        player.getHand().add(graveYard.get(0));
        graveYard.remove(graveYard.get(0));

    }
}
