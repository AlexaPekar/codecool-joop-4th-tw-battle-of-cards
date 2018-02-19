package com.codecool.cardgame.api;

import java.util.List;

public interface Player {

    public String getName();

    public List<Card> getDeck();

    public List<Card> getHand();

    public int getHp();

    public void increaseHp(int hp);

    public void decreaseHp(int hp);

    public void pickCard();

    public Card chooseCard(String name);

    public int chooseAttribute(Card card, String attribute);

    public void fillDeck();
}
