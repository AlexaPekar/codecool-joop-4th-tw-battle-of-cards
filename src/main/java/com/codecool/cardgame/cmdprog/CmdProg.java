package com.codecool.cardgame.cmdprog;

import com.codecool.cardgame.api.*;
import com.codecool.cardgame.api.exception.NoManaException;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.util.List;
import java.util.Scanner;

public class CmdProg {
    private GameImpl game;
    private Scanner scan = new Scanner(System.in);
    int numberOfRound = 1;
    boolean canPlay = true;
    Player winner = null;
    Player defendingPlayer = game.getPlayer2();

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
        if (numberOfRound > 2) {
            checkHand(player);
        }
        handleStatistics(player);
        listCards(player.getHand());
        if (!defendingPlayer.getName().equals(player.getName())) {
            String chosenCardAsString = scan.nextLine();
            player.chooseCard(chosenCardAsString);
            String chosenAttributeAsString = scan.nextLine().toUpperCase();
            player.chooseAttribute(player.getChosenCard(), chosenAttributeAsString);
        }
        else {
            String chosenCardAsString = scan.nextLine();
            player.chooseCard(chosenCardAsString);
        }

        if(game.canUseSpell()) {
            listSpells(player);
            String spellName = scan.nextLine();
            player.chooseSpellCard(spellName);
            try {
                game.decideSpell(player.getChosenSpell());
            } catch (NoManaException ex) {
                System.out.println(ex.getMessage());
            }
        }
        defendingPlayer = player;
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

    public void listCards(List<Card> cards) {
        for (Card card:cards) {
            System.out.println(card);
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
