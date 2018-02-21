package com.codecool.cardgame.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerImpl implements Player {
    private String name;
    private List<Card> deck;
    private List<Card> hand;
    private FighterCard chosenCard;
    private SpellCard chosenSpell;
    private int hp;
    private int mp;

    public PlayerImpl(String name) throws IOException {
        hand = new ArrayList<>();
        fillDeck();

        pickCard();
        pickCard();
        pickCard();

        this.name = name;
        chosenCard = null;
        this.hp = 10000;
        this.mp = 10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getHand() {
        return hand;
    }

    public FighterCard getChosenCard() {
        return chosenCard;
    }

    public int getHp() {
        return hp;
    }

    public void increaseHp(int hp) {
        this.hp += hp;
    }

    public void decreaseHp(int hp) {
        this.hp -= hp;
    }

    @Override
    public int getMp() {
        return mp;
    }

    @Override
    public void increaseMp(int mp) {
        this.mp += mp;
    }

    @Override
    public void decreaseMp(int mp) {
        this.mp -= mp;
    }

    public void pickCard() {
        hand.add(deck.get(0));
        deck.remove(deck.get(0));
    }

    public void setChosenCard(Card card) {
        chosenCard = (FighterCard) card;
    }

    public void chooseCard(String name) {
        for (Card card : hand) {
            if (card.getName().equals(name)) {
                setChosenCard((FighterCard) card);
            }
        }
    }

    public SpellCard getChosenSpell() {
        return chosenSpell;
    }

    public void chooseSpellCard(String name) {
        for (Card card : hand) {
            if (card.getName().equals(name)) {
                chosenSpell = (SpellCard) card;
                hand.remove(card);
            }
        }
    }

    public int chooseAttribute(FighterCard card,String attribute) {
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

    public void fillDeck() throws IOException {
        CsvParser csvParser = new CsvParser();
        deck = csvParser.getCardsFromCsv("cards.csv");
        Collections.shuffle(deck);


    }

    @Override
    public String toString() {
        return "PlayerImpl{" +
                "name='" + name + '\n' +
                " hp=" + hp +
                " \t mp=" + mp +
                "\n\n hand=" + hand +
                '}';
    }
}
