package com.codecool.cardgame.cmdprog;

import com.codecool.cardgame.api.Card;
import com.codecool.cardgame.api.FighterCard;
import com.codecool.cardgame.api.GameImpl;
import com.codecool.cardgame.api.Player;

import javax.swing.text.StyledEditorKit;
import java.util.List;
import java.util.Scanner;

public class CmdProg {
    private GameImpl game;
    private Scanner scan = new Scanner(System.in);
    int numberOfRound = 1;
    boolean canPlay = true;
    Player winner = null;

    public CmdProg() {
        game = new GameImpl();
    }

    public void run() {
        handlePlayerCreation();
        while (canPlay) {
            canPlay();
            getPlayerDecision();
        }
    }

    public void getPlayerDecision() {
        Player player = game.getCurrentPlayer();
        listCards(player, player.getHand());
        String chosenCardAsString = scan.nextLine();
        player.chooseCard(chosenCardAsString);
        String chosenAttributeAsString = scan.nextLine().toUpperCase();
        player.chooseAttribute(player.getChosenCard(), chosenAttributeAsString);
        switchPlayers();
    }

    public void canPlay() {
        if (game.getPlayer1().getHp() < 1 || game.getPlayer2().getHp() < 1) {
            canPlay = false;
            if (game.getPlayer1().getHp() > 1) {
                winner = game.getPlayer1();
            } else {
                winner = game.getPlayer2();
            }
        }
    }

    public void handlePlayerCreation() {
        System.out.println("Enter name of player 1.");
        String nameP1 = scan.nextLine();
        game.getPlayer1().setName(nameP1);
        System.out.println("Enter name of player 2.");
        String nameP2 = scan.nextLine();
        game.getPlayer2().setName(nameP2);
    }

    public void listCards(Player player,List<Card> cards) {
        for (Card card:cards) {
            System.out.println(card);
        }
    }

    public void checkHand(Player player) {
        List<Card> hand = player.getHand();
        Boolean fighter = false;
        while (fighter.equals(false) && player.getDeck().size() != 0) {
            player.pickCard();
            for (Card card:hand) {
                if (card instanceof FighterCard) {
                    fighter = true;
                }
            }
        }
    }

    public void handleStatistics(Player player) {
        System.out.println(player);
    }

    public void switchPlayers() {
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        if (numberOfRound % 2 == 0) {
            game.setCurrentPlayer(player2);
            numberOfRound++;
            System.out.println("Round of " + player1.getName());
        } else {
            game.setCurrentPlayer(player1);
            numberOfRound++;
            System.out.println("Round of " + player2.getName());
        }
    }



}
