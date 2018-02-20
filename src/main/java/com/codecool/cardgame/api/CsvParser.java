package com.codecool.cardgame.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
        String line = "";
        String csvFile;
        List<Card> deck = new ArrayList<Card>();

    public List<Card> getCardsFromCsv(String filepath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("../data/" + csvFile));
            if (br == null) {
                throw new IOException();
            }
            while ((line = br.readLine()) != null) {
                String[] card = line.split(",");
                if (card[1].equals("Fighter")) {
                    FighterCard fighterCard = new FighterCard(card[0], card[1], Integer.parseInt(card[3]), Integer.parseInt(card[4]), Integer.parseInt(card[5]));
                    deck.add(fighterCard);
                } else {
                    SpellCard spellCard = new SpellCard(card[1], card[2], card[3]);
                    deck.add(spellCard);
                }
            }
        return deck;
    }
}
