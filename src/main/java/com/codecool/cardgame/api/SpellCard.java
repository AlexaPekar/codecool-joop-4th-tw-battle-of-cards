package com.codecool.cardgame.api;

import java.util.List;
import java.util.Scanner;

public class SpellCard extends Card{
    private String effect;

    public SpellCard(String name,String type, String effect) {
        super(name,type);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    public void increaseHp(Player player) {
        player.increaseHp(1000);
    }

    public void decreaseHp(Player player) {
        player.decreaseHp(1000);
    }

    public void increaseDmg(Player player) {
        player.getChosenCard().increaseDamage(100);
    }

    public void increaseIntelligence(Player player) {
        player.getChosenCard().increaseIntelligence(10);
    }

    public void increaseDamage(Player player) {
        player.getChosenCard().increaseDefense(10);
    }

    public void returnFromGraveyard(Player player, List<Card> graveYard,String revivedCard) {
        for (Card card:graveYard) {
            if (card.getName().equals(revivedCard)) {
                player.getHand().add(card);
                graveYard.remove(card);
            }
        }

    }
}
