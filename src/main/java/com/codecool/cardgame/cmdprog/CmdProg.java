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

    public CmdProg() {
        game = new GameImpl();
    }

    public void run() {

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



}
