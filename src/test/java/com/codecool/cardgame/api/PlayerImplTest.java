package com.codecool.cardgame.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerImplTest {

    PlayerImpl playerImpl;
    FighterCard testFighter1;
    FighterCard testFighter2;
    Card testSpell1;
    Card testSpell2;
    List<String> testNamesInDeck;
    List<String> testNamesInHand;

    @BeforeEach
    void setUp() throws IOException {
        playerImpl = new PlayerImpl("Albert", "test.csv");
        testNamesInDeck = new ArrayList<>();
        testNamesInHand = new ArrayList<>();
        testFighter1 = new FighterCard("PistaBátya", "Fighter", 60, 50, 34);
        testFighter2 = new FighterCard("Leslie the black magician", "Fighter", 70, 40, 65);
        testSpell1 = new SpellCard("Magic mushroom", "Spell", "Increase HP", 5);
        testSpell2 = new SpellCard("Medical Sausage", "Spell", "Increase HP", 3);
        playerImpl.fillDeck();

    }

    @Test
    void increaseHp() {
        System.out.println(playerImpl.getHp());
        playerImpl.increaseHp(5000);
        assertEquals(15000, playerImpl.getHp());
    }

    @Test
    void decreaseHp() {
        playerImpl.decreaseHp(5000);
        assertEquals(5000, playerImpl.getHp());

    }

    @Test
    void increaseMp() {
        playerImpl.increaseMp(5);
        assertEquals(10, playerImpl.getMp());
    }

    @Test
    void decreaseMp() {
        playerImpl.decreaseMp(5);
        assertEquals(5, playerImpl.getMp());
    }

    @Test
    void pickCard() throws IOException {
        playerImpl.fillDeck();
        for (int i = 0; i < 4; i++) {
            playerImpl.pickCard();
        }
        for (Card card : playerImpl.getHand()) {
            testNamesInHand.add(card.getName());
        }
        assertEquals(true, testNamesInHand.contains(testFighter1.getName()));
        assertEquals(true, testNamesInHand.contains(testFighter2.getName()));
        assertEquals(true, testNamesInHand.contains(testSpell1.getName()));
        assertEquals(true, testNamesInHand.contains(testSpell2.getName()));

    }

    @Test
    void chooseCard() throws IOException {
        playerImpl.fillDeck();
        for (int i = 0; i < 4; i++) {
            playerImpl.pickCard();
        }
        playerImpl.chooseCard("Leslie the black magician");
        assertEquals("Leslie the black magician", playerImpl.getChosenCard().getName());
        playerImpl.chooseCard("PistaBátya");
        assertEquals("PistaBátya", playerImpl.getChosenCard().getName());
        playerImpl.chooseCard("Medical Sausage");
        assertEquals("Medical Sausage", playerImpl.getChosenSpell().getName());
        playerImpl.chooseCard("Magic mushroom");
        assertEquals("Magic mushroom", playerImpl.getChosenSpell().getName());
    }

    @Test
    void chooseAttribute() {
        assertEquals(34, playerImpl.chooseAttribute(testFighter1,"Intelligence"));
        assertEquals(60, playerImpl.chooseAttribute(testFighter1,"Damage"));
        assertEquals(40, playerImpl.chooseAttribute(testFighter2,"Defense"));
        assertEquals(65, playerImpl.chooseAttribute(testFighter2,"Intelligence"));
    }

    @Test
    void fillDeck() throws IOException {
        playerImpl.fillDeck();
        for (Card card : playerImpl.getDeck()) {
            testNamesInDeck.add(card.getName());
        }
        assertEquals(true, testNamesInDeck.contains(testFighter1.getName()));
        assertEquals(true, testNamesInDeck.contains(testFighter2.getName()));
        assertEquals(true, testNamesInDeck.contains(testSpell1.getName()));
        assertEquals(true, testNamesInDeck.contains(testSpell2.getName()));
    }
}