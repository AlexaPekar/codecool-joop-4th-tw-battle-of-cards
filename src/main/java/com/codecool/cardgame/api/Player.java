package com.codecool.cardgame.api;

import java.io.IOException;
import java.util.List;

public interface Player {

    void setName(String name);

    String getName();

    List<Card> getDeck();

    List<Card> getHand();

    int getHp();

    void increaseHp(int hp);

    void decreaseHp(int hp);

    int getMp();

    FighterCard getChosenCard();

    SpellCard getChosenSpell();

    void increaseMp(int mp);

    void decreaseMp(int mp);

    void pickCard();

    void chooseCard(String name);

    void setChosenSpell(Card card);

    int chooseAttribute(FighterCard card, String attribute);

    void fillDeck() throws IOException;

    String toString();
}
