package com.codecool.cardgame.api;

import java.io.IOException;
import java.util.List;

public interface Player {

    public void setName(String name);

    public String getName();

    public List<Card> getDeck();

    public List<Card> getHand();

    public int getHp();

    public void increaseHp(int hp);

    public void decreaseHp(int hp);

    public int getMp();

    public FighterCard getChosenCard();

    public SpellCard getChosenSpell();

    public void increaseMp(int mp);

    public void decreaseMp(int mp);

    public void pickCard();

    public void chooseCard(String name);

    public void setChosenSpell(Card card);

    public int chooseAttribute(FighterCard card, String attribute);

    public void fillDeck() throws IOException;

    public String toString();
}
