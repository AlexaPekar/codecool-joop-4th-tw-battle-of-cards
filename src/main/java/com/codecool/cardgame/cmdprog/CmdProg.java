package com.codecool.cardgame.cmdprog;

import com.codecool.cardgame.api.*;
import com.codecool.cardgame.api.exception.NoManaException;
import com.codecool.cardgame.api.exception.RoundDrawException;
import com.codecool.cardgame.api.exception.WrongInputException;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CmdProg {
    private GameImpl game;
    private Scanner scan = new Scanner(System.in);
    int numberOfRound = 1;
    boolean canPlay = true;
    private Player winner = null;
    private Player defendingPlayer = null;

    public CmdProg() {
        game = new GameImpl();
    }

    public void run() {
        try {
            handlePlayerCreation();
            game.setCurrentPlayer(game.getPlayer1());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (canPlay) {
            canPlay();
            getPlayerDecision();
        }
    }

    public void getPlayerDecision() {
        if (numberOfRound % 2 == 1 && numberOfRound != 1) {
            printWinner();
        }
        Player player = game.getCurrentPlayer();
        if(defendingPlayer == null){
            defendingPlayer = game.getPlayer2();
        }
        if (numberOfRound > 2) {
            checkHand(player);
        }
        handleStatistics(player);
        listCards(player.getHand());
        if (!defendingPlayer.getName().equals(player.getName())) {
            System.out.println("Choose a card,enter its name.");
            String chosenCardAsString = scan.nextLine();
            player.chooseCard(chosenCardAsString);
            System.out.println("Choose an attribute(damage,defense,intelligence).");
            System.out.println(player.getChosenCard().toString());
            String chosenAttributeAsString = scan.nextLine().toUpperCase();
            player.chooseAttribute(player.getChosenCard(), chosenAttributeAsString);
            game.setChosenAttribute(chosenAttributeAsString);
        }
        else {
            System.out.println("Choose a card,enter its name.");
            String chosenCardAsString = scan.nextLine();
            player.chooseCard(chosenCardAsString);
        }

        if(game.canUseSpell()) {
            System.out.println("You can use a spell.");
            listSpells(player);
            System.out.println("Enter the name of the spell.");
            String spellName = scan.nextLine();
            player.chooseCard(spellName);
            try {
                game.decideSpell(player.getChosenSpell());
            } catch (NoManaException ex) {
                System.out.println(ex.getMessage());
            }
        }
        switchPlayers();
    }

    public void switchDefendingPlayer() {
        if (numberOfRound % 3 == 0) {
            if(game.getCurrentPlayer().equals(game.getPlayer1())) {
                defendingPlayer = game.getPlayer2();
            } else {
                defendingPlayer = game.getPlayer1();
            }
        }
    }

    public void printWinner() {
        try {
            Player roundWinner = game.getWinner();
            System.out.println("This round's winner is: " + roundWinner.getName());
        } catch (RoundDrawException e) {
            System.out.println(e.getMessage());
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
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

    public void handlePlayerCreation() throws IOException {
        System.out.println("Enter name of player 1.");
        String nameP1 = scan.nextLine();
        Player player1 = new PlayerImpl(nameP1);
        game.setPlayer1(player1);
        System.out.println("Enter name of player 2.");
        String nameP2 = scan.nextLine();
        Player player2 = new PlayerImpl(nameP2);
        game.setPlayer2(player2);
    }

    public void listCards(List<Card> cards) {
        for (Card card:cards) {
            System.out.println(card.toString());
        }
    }

    public void listSpells(Player player) {
        for (Card card:player.getHand()) {
            if (card instanceof SpellCard) {
                System.out.println(card);
            }
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

        switchDefendingPlayer();
        if (game.getCurrentPlayer().equals(player1)) {
            game.setCurrentPlayer(player2);
            numberOfRound++;
            System.out.println("Round of " + player2.getName());
        } else {
            game.setCurrentPlayer(player1);
            numberOfRound++;
            System.out.println("Round of " + player1.getName());
        }
    }



}
