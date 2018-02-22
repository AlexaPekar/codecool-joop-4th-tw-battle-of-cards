package com.codecool.cardgame.api;

import com.codecool.cardgame.api.exception.RoundDrawException;
import com.codecool.cardgame.api.exception.WrongInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameImplTest {

    GameImpl gameImpl = new GameImpl();
    PlayerImpl player1;
    PlayerImpl player2;
    String chosenAttribute;
    PlayerImpl currentPlayer;
    List<Card> graveyard;

    @BeforeEach
    void setUp() throws IOException {
        player1 = new PlayerImpl("John", "test.csv");
        player2 = new PlayerImpl("Lisa", "test.csv");
        gameImpl.setPlayer1(player1);
        gameImpl.setPlayer2(player2);

        chosenAttribute = "DAMAGE";
        gameImpl.setChosenAttribute(chosenAttribute);

        currentPlayer = player1;
        graveyard = new ArrayList<>();

        Card testCard = new FighterCard("Merlin","Fighter", 50, 40, 60);
        Card testCard2 = new FighterCard("Albert", "Fighter", 60,50,20);
        player1.setChosenCard(testCard);
        player2.setChosenCard(testCard2);
    }

    @Test
    void getPlayer1() {
        assertEquals(player1, gameImpl.getPlayer1());
    }

    @Test
    void getPlayer2() {
        assertEquals(player2, gameImpl.getPlayer2());
    }

    @Test
    void getChosenAttribute() {
        assertEquals(chosenAttribute, gameImpl.getChosenAttribute());
    }

    @Test
    void setPlayer1() throws IOException {
        gameImpl.setPlayer1(new PlayerImpl("Gideon", "test.csv"));
        assertEquals("Gideon", gameImpl.getPlayer1().getName());
    }

    @Test
    void setPlayer2() throws IOException {
        System.out.println(currentPlayer.getDeck().get(0).getName());
        System.out.println(currentPlayer.getHand().get(1).getName());

        gameImpl.setPlayer2(new PlayerImpl("Zsiga","test.csv"));
        assertEquals("Zsiga", gameImpl.getPlayer2().getName());
    }

    @Test
    void setChosenAttribute() {
        gameImpl.setChosenAttribute("Intelligence");
        assertEquals("Intelligence", gameImpl.getChosenAttribute());
    }

    @Test
    void getWinner() throws RoundDrawException, WrongInputException {
        assertEquals(player2, gameImpl.getWinner());
    }

    @Test
    void decreaseHP() {
        gameImpl.decreaseHP(player1,4000);
        assertEquals(6000, player1.getHp());
    }
}