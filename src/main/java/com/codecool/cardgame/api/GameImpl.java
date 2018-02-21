package com.codecool.cardgame.api;

import com.codecool.cardgame.api.exception.NoManaException;
import com.codecool.cardgame.api.exception.RoundDrawException;
import com.codecool.cardgame.api.exception.WrongInputException;

import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {

    private Player player1;
    private Player player2;
    private String chosenAttribute;
    private Player currentPlayer;
    private List<Card> graveyard;

    public GameImpl() {
        graveyard = new ArrayList<>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }

    @Override
    public String getChosenAttribute() {
        return chosenAttribute;
    }

    @Override
    public void setPlayer1(Player player) {
        player1 = player;
    }

    @Override
    public void setPlayer2(Player player) {
        player2 = player;
    }

    @Override
    public void setChosenAttribute(String attribute) {
        chosenAttribute = attribute;
    }

    @Override
    public Player getWinner() throws RoundDrawException, WrongInputException {
        FighterCard player1Card = player1.getChosenCard();
        FighterCard player2Card = player2.getChosenCard();
        graveyard.add(player1Card);
        graveyard.add(player2Card);
        switch (chosenAttribute) {
            case "DEFENSE":
                if (player1Card.getDefense() > player2Card.getDefense()) {
                    decreaseHP(player2, player1Card.getDamage());
                    return player1;
                } else if (player1Card.getDefense() < player2Card.getDefense()) {
                    decreaseHP(player1, player2Card.getDamage());
                    return player2;
                }
                throw new RoundDrawException("It's a draw round");
            case "DAMAGE":
                if (player1Card.getDamage() > player2Card.getDamage()) {
                    decreaseHP(player2, player1Card.getDamage());
                    return player1;
                } else if (player1Card.getDamage() < player2Card.getDamage()) {
                    decreaseHP(player1, player2Card.getDamage());
                    return player2;
                }
                throw new RoundDrawException("It's a draw round");
            case "INTELLIGENCE":
                if (player1Card.getIntelligence() > player2Card.getIntelligence()) {
                    decreaseHP(player2, player1Card.getDamage());
                    return player1;
                } else if (player1Card.getIntelligence() < player2Card.getIntelligence()) {
                    decreaseHP(player1, player2Card.getDamage());
                    return player2;
                }
                throw new RoundDrawException("It's a draw round");
            default:
                throw new WrongInputException("Wrong input");
        }
    }

    public boolean canUseSpell() {
        for (Card card : currentPlayer.getHand()) {
            if (card instanceof SpellCard) {
                if (currentPlayer.getMp() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void decideSpell(SpellCard spellCard) throws NoManaException {
        if (spellCard.getManaCost() < currentPlayer.getMp()) {
            if (spellCard.getEffect().equals("Increase HP")) {
                spellCard.increaseHp(currentPlayer);
            } else if (spellCard.getEffect().equals("Increase Damage")) {
                spellCard.increaseDmg(currentPlayer);
            } else if (spellCard.getEffect().equals("Increase Intelligence")) {
                spellCard.increaseIntelligence(currentPlayer);
            } else if (spellCard.getEffect().equals("Increase Defense")) {
                spellCard.increaseDefense(currentPlayer);
            } else if (spellCard.getEffect().equals("Revive")) {
                spellCard.returnFromGraveyard(currentPlayer, graveyard);
            }
        } else {
            throw new NoManaException("Not enough mana!");
        }
    }

    @Override
    public void decreaseHP(Player player, int damage) {
        player.decreaseHp(damage);
    }

    @Override
    public List<Card> getGraveyard() {
        return graveyard;
    }


}
