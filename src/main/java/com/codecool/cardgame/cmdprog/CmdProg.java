package com.codecool.cardgame.cmdprog;

import com.codecool.cardgame.api.*;
import com.codecool.cardgame.api.exception.NoManaException;
import com.codecool.cardgame.api.exception.RoundDrawException;
import com.codecool.cardgame.api.exception.WrongInputException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class CmdProg {
    private GameImpl game;
    private Scanner scan = new Scanner(System.in);
    private Player winner = null;
    private Player defendingPlayer = null;

    public CmdProg() {
        game = new GameImpl();
    }

    public void run() {
        clearScreen();
        System.out.println("\n\nWelcome to the Battle of Wizards game!");
        while (true) {
            System.out.println("\n\nMenu");
            System.out.println("(1) Play\n" +
                    "(2) Instructions\n" +
                    "(3) Exit");
            System.out.println("\nChoose an option");
            String input = scan.nextLine();
            switch (input) {
                case "1":
                    clearScreen();
                    try {
                        handlePlayerCreation();
                        game.setCurrentPlayer(game.getPlayer1());
                        defendingPlayer = game.getPlayer2();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    while (game.canPlay()) {
                        getPlayerDecision();
                    }
                    System.out.println("\nCongratulations " + winner.getName() + ", you won the game!");
                    System.out.println("\n\nEnter anything to go back");
                    String goBackAfterGame = scan.nextLine();
                    clearScreen();
                    break;
                case "2":
                    clearScreen();
                    handleInstructions();
                    System.out.println("\n\nEnter anything to go back");
                    String goBack = scan.nextLine();
                    clearScreen();
                    break;
                case "3":
                    System.exit(0);
                default:
                    System.out.println("Wrong input");
                    clearScreen();
            }
        }
    }
    public void getPlayerDecision() {
        Player player = game.getCurrentPlayer();
        System.out.println("\nRound of " + player.getName());
        player.increaseMp(1);
        game.checkHand(player);
        handleStatistics(player);
        listCards(player.getHand());
        if (!defendingPlayer.getName().equals(player.getName())) {
            System.out.println("\nChoose a card,enter its name.");
            String chosenCardAsString = scan.nextLine();
            while (!handContains(chosenCardAsString, player)) {
                System.out.println("There's no such card in your hand!");
                chosenCardAsString = scan.nextLine();
            }
            player.chooseCard(chosenCardAsString);
            System.out.println("Choose an attribute(damage,defense,intelligence).");
            System.out.println(player.getChosenCard().toString());
            String chosenAttributeAsString = scan.nextLine().toUpperCase();
            while (!("DAMAGE,DEFENSE,INTELLIGENCE").contains(chosenAttributeAsString)) {
                System.out.println("There's no such type");
                chosenAttributeAsString = scan.nextLine().toUpperCase();
            }
            player.chooseAttribute(player.getChosenCard(), chosenAttributeAsString);
            game.setChosenAttribute(chosenAttributeAsString);

        }
        else {
            System.out.println("\nChoose a card,enter its name.");
            String chosenCardAsString = scan.nextLine();
            while (!handContains(chosenCardAsString, player)) {
                System.out.println("There's no such card in your hand!");
                chosenCardAsString = scan.nextLine();
            }
            player.chooseCard(chosenCardAsString);

        }

        if(game.canUseSpell()) {
            System.out.println("You can use a spell.But do you want to? Yes/No");
            String choice = scan.nextLine().toLowerCase();
            if (choice.equals("yes")) {
                listSpells(player);
                System.out.println("Enter the name of the spell.");
                String spellName = scan.nextLine();
                while (!handContains(spellName, player)) {
                    System.out.println("There's no such card in your hand!");
                    spellName = scan.nextLine();
                }
                player.chooseCard(spellName);
                player.getHand().remove(player.getChosenSpell());
                try {
                    game.decideSpell(player.getChosenSpell());
                    if(player.getChosenSpell().getEffect().equals("Revive")){
                        System.out.println("You revived: "+game.getGraveyard().get(game.getGraveyard().size()-1).getName());
                    }
                } catch (NoManaException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        if (game.getNumberOfRound() % 2 == 0) {
            switchDefendingPlayer();
            System.out.println("\n" + game.getPlayer1().getChosenCard().getName() + " VS "+game.getPlayer2().getChosenCard().getName());
            printWinner();
        }
        game.switchPlayers();
    }

    public boolean handContains(String name, Player player) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

   public void switchDefendingPlayer() {
       if (defendingPlayer.getName().equals(game.getPlayer1().getName())) {
           defendingPlayer = game.getPlayer2();
       } else if (defendingPlayer.getName().equals(game.getPlayer2().getName())) {
           defendingPlayer = game.getPlayer1();
       }
   }

    public void printWinner() {
        try {
            Player roundWinner = game.getRoundWinner();
            System.out.println("This round's winner is: " + roundWinner.getName());
        } catch (RoundDrawException e) {
            System.out.println(e.getMessage());
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }


    public void handlePlayerCreation() throws IOException {
        System.out.println("Enter name of player 1.");
        String nameP1 = scan.nextLine();
        Player player1 = new PlayerImpl(nameP1,"cards.csv");
        game.setPlayer1(player1);
        System.out.println("Enter name of player 2.");
        String nameP2 = scan.nextLine();
        Player player2 = new PlayerImpl(nameP2,"cards.csv");
        game.setPlayer2(player2);
    }

    public void listCards(List<Card> cards) {
        System.out.println("These cards are in your hand: ");
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


    public void handleStatistics(Player player) {
        System.out.println(player + "\n\n");
    }


    public void handleInstructions() {
        System.out.println("Battle of Wizards\n" +
                "\n" +
                "Rules of the game:\n" +
                "\n" +
                "Fighter Card: You can fight with this card by comparing the chosen attributes. You can choose from Damage, Defense and Intelligence.\n" +
                "\n" +
                "Spell Card: It can modify values during the game to help you. Each Spell Card has different effects: Increase HP (of the player), Increase Damage(of the Fighter Card), Increase Defense(of the Fighter Card), Increase Intelligence(of the Fighter Card), Revive(a card from the Graveyard)\n" +
                "\n" +
                "HP: health points\n" +
                "MP: mana points\n" +
                "\n" +
                "- The game is played by 2 players.\n" +
                "- At the beginning of the game each player has a deck of 30 cards containing 10 Spell Cards and 20 Fighter cards.\n" +
                "- First of all, the game picks 3 cards for each player from their own deck.\n" +
                "- If there is no Fighter Card in between the picked 3 cards, the player must pick until he/she has one Fighter Card.\n" +
                "- All players must always have at least 3 cards during the game.\n" +
                "\n" +
                "- In the first round the first player attacks. \n" +
                "- He/she must choose a Fighter Card from his hand, then an attribute from that card which he/she wants to fight with.\n" +
                "- If he/she has Spell Card(s) and MP over 0, he/she has the opportunity to use it/one of them.\n" +
                "- If the Spell Card has bigger Mana Cost than the current MP of the player, the returns a message \"Not enough mana\".\n" +
                "\n" +
                "- After this, the other player must choose a Fighter Card to enter the battle.\n" +
                "- The one having the bigger value of the chosen attribute is the winner of the round. It is possible that the round is draw.\n" +
                "\n" +
                "- In the second round the other player attack, etc.\n" +
                "\n" +
                "- The game automatically picks a card for the players after each round. \n" +
                "\n" +
                "- The game continues like this until one of the player's HP decreases below 1.\n" +
                "\n" +
                "- Last man standing wins the game.");
    }

    public void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
