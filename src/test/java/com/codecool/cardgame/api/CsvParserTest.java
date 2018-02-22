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
        List<Card> cardsFromCsv = csvParser.getCardsFromCsv("test.csv");
        assertEquals(testDeck.get(0).getName(), cardsFromCsv.get(0).getName());
        assertEquals(testDeck.get(1).getName(), cardsFromCsv.get(1).getName());
        assertEquals(testDeck.get(2).getName(), cardsFromCsv.get(2).getName());
        assertEquals(testDeck.get(3).getName(), cardsFromCsv.get(3).getName());

        assertEquals(testDeck.get(0).getType(), cardsFromCsv.get(0).getType());
        assertEquals(testDeck.get(1).getType(), cardsFromCsv.get(1).getType());
        assertEquals(testDeck.get(2).getType(), cardsFromCsv.get(2).getType());
        assertEquals(testDeck.get(3).getType(), cardsFromCsv.get(3).getType());

        FighterCard fighterCard1 = (FighterCard) testDeck.get(0);
        FighterCard fighterCard2 = (FighterCard) testDeck.get(1);

        assertEquals(fighterCard1.getDamage(), ((FighterCard) cardsFromCsv.get(0)).getDamage());
        assertEquals(fighterCard2.getDamage(), ((FighterCard) cardsFromCsv.get(1)).getDamage());

        assertEquals(fighterCard1.getDefense(), ((FighterCard) cardsFromCsv.get(0)).getDefense());
        assertEquals(fighterCard2.getDefense(), ((FighterCard) cardsFromCsv.get(1)).getDefense());

        assertEquals(fighterCard1.getIntelligence(), ((FighterCard) cardsFromCsv.get(0)).getIntelligence());
        assertEquals(fighterCard2.getIntelligence(), ((FighterCard) cardsFromCsv.get(1)).getIntelligence());

        SpellCard spellCard1 = (SpellCard) testDeck.get(2);
        SpellCard spellCard2 = (SpellCard) testDeck.get(3);

        assertEquals(spellCard1.getEffect(), ((SpellCard) cardsFromCsv.get(2)).getEffect());
        assertEquals(spellCard2.getEffect(), ((SpellCard) cardsFromCsv.get(3)).getEffect());

        assertEquals(spellCard1.getManaCost(), ((SpellCard) cardsFromCsv.get(2)).getManaCost());
        assertEquals(spellCard2.getManaCost(), ((SpellCard) cardsFromCsv.get(3)).getManaCost());




    }
}