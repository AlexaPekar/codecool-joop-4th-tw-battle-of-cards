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

    public List<Card> getCardsFromCsv(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader("../data/" + csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] card = line.split(",");
                if (card[0].equals("Fighter")) {
                    FighterCard fighterCard = new FighterCard(card[1],Integer.parseInt(card[2]),Integer.parseInt(card[3]),Integer.parseInt(card[4]));
                    deck.add(fighterCard);
                }
                SpellCard spellCard = new SpellCard(card[1], card[2], card[3]);
                deck.add(spellCard);
            }
        } catch (IOException e) {
            System.out.println(" Exception error. File not found.");
            e.printStackTrace();
        }
        return deck;
    }
}
