package com.codecool.cardgame.api;

import java.util.List;

public interface Player {

    public String getName();

    public List<Card> getDeck();

    public List<Card> getHand();

    public int getHp();

    public void increaseHp(int hp);

    public void decreaseHp(int hp);

    public int getMp();

    public void increaseMp(int mp);

    public void decreaseMp(int mp);

    public void pickCard();

    public void chooseCard(String name);

    public int chooseAttribute(FighterCard card, String attribute);

    public void fillDeck();
}
