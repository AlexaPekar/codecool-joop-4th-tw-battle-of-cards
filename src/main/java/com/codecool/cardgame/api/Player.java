package com.codecool.cardgame.api;

import java.util.List;

public class Player {
    private String name;
    private List<Card> deck;
    private List<Card> hand;
    private int hp;

    public Player(String name, List<Card> deck, List<Card> hand) {
        this.name = name;
        this.deck = deck;
        this.hand = hand;
        this.hp = 10000;
    }

    public String getName() {
        return name;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void pickCard() {
        hand.add(deck.get(0));
        deck.remove(deck.get(0));
    }

    public Card chooseCard(String name) {
        Card chosenCard = null;
        for (Card card:hand) {
            if (card.getName().equals(name)) {
                chosenCard = card;
            }
        }
        return chosenCard;
    }

    public int chooseAttribute(Card card,String attribute) {
        int attributeValue = 0;
        if (attribute.equals("Damage")) {
            attributeValue = card.getDamage();
        }
        else if (attribute.equals("Intelligence")) {
            attributeValue = card.getIntelligence();
        }
        else if (attribute.equals("Defense")) {
            attributeValue = card.getDefense();
        }
        return attributeValue;
    }

    public void fillDeck() {
        System.out.println("under construction");
    }
}
