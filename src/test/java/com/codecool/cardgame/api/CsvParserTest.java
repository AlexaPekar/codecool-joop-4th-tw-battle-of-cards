package com.codecool.cardgame.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvParserTest {

    CsvParser csvParser = new CsvParser();
    List<Card> testDeck;

    @BeforeEach
    void setUp() {
        testDeck = new ArrayList<>();
        Card testFighter1 = new FighterCard("PistaBátya","Fighter",60,50,34);
        Card testFighter2 = new FighterCard("Leslie the black magician","Fighter",70,40,65);
        Card testSpell1 = new SpellCard("Magic mushroom", "Spell", "Increase HP",5);
        Card testSpell2 = new SpellCard("Medical Sausage", "Spell", "Increase HP",3);
        testDeck.add(testFighter1);
        testDeck.add(testFighter2);
        testDeck.add(testSpell1);
        testDeck.add(testSpell2);
    }

    @Test
    void getCardsFromCsv() throws IOException {
        assertEquals(testDeck.get(0).getName(), csvParser.getCardsFromCsv("test.csv").get(0).getName());
        assertEquals(testDeck.get(1).getName(), csvParser.getCardsFromCsv("test.csv").get(1).getName());
        assertEquals(testDeck.get(2).getName(), csvParser.getCardsFromCsv("test.csv").get(2).getName());
        assertEquals(testDeck.get(3).getName(), csvParser.getCardsFromCsv("test.csv").get(3).getName());

        assertEquals(testDeck.get(0).getType(), csvParser.getCardsFromCsv("test.csv").get(0).getType());
        assertEquals(testDeck.get(1).getType(), csvParser.getCardsFromCsv("test.csv").get(1).getType());
        assertEquals(testDeck.get(2).getType(), csvParser.getCardsFromCsv("test.csv").get(2).getType());
        assertEquals(testDeck.get(3).getType(), csvParser.getCardsFromCsv("test.csv").get(3).getType());

        assertEquals(((FighterCard)testDeck.get(0)).getDamage(), ((FighterCard)csvParser.getCardsFromCsv("test.csv").get(0)).getDamage());
        assertEquals(((FighterCard)testDeck.get(1)).getDamage(), ((FighterCard)csvParser.getCardsFromCsv("test.csv").get(1)).getDamage());

        assertEquals(((FighterCard)testDeck.get(0)).getDefense(), ((FighterCard)csvParser.getCardsFromCsv("test.csv").get(0)).getDefense());
        assertEquals(((FighterCard)testDeck.get(1)).getDefense(), ((FighterCard)csvParser.getCardsFromCsv("test.csv").get(1)).getDefense());

        assertEquals(((FighterCard)testDeck.get(0)).getIntelligence(), ((FighterCard)csvParser.getCardsFromCsv("test.csv").get(0)).getIntelligence());
        assertEquals(((FighterCard)testDeck.get(1)).getIntelligence(), ((FighterCard)csvParser.getCardsFromCsv("test.csv").get(1)).getIntelligence());

        assertEquals(((SpellCard)testDeck.get(2)).getEffect(), ((SpellCard)csvParser.getCardsFromCsv("test.csv").get(2)).getEffect());
        assertEquals(((SpellCard)testDeck.get(3)).getEffect(), ((SpellCard)csvParser.getCardsFromCsv("test.csv").get(3)).getEffect());

        assertEquals(((SpellCard)testDeck.get(2)).getManaCost(), ((SpellCard)csvParser.getCardsFromCsv("test.csv").get(2)).getManaCost());
        assertEquals(((SpellCard)testDeck.get(3)).getManaCost(), ((SpellCard)csvParser.getCardsFromCsv("test.csv").get(3)).getManaCost());




    }
}