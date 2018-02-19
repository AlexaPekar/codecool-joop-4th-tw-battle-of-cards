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
                String[] wizard = line.split(",");
                Card card = new Card(wizard[0],Integer.parseInt(wizard[1]),Integer.parseInt(wizard[2]),Integer.parseInt(wizard[3]));
                deck.add(card);
            }
        } catch (IOException e) {
            System.out.println(" Exception error. File not found.");
            e.printStackTrace();
        }
        return deck;
    }
}
