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
    private int numberOfRound;
    private Player winner;

    private Player defendingPlayer;

    public GameImpl() {
        graveyard = new ArrayList<>();
        numberOfRound = 1;
        winner = null;
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
    public Player getRoundWinner() throws RoundDrawException, WrongInputException {
        FighterCard player1Card = player1.getChosenCard();
        FighterCard player2Card = player2.getChosenCard();
        graveyard.add(player1Card);
        player1.getHand().remove(player1Card);
        graveyard.add(player2Card);
        player2.getHand().remove(player2Card);
        CardAttribute attribute = CardAttribute.valueOf(chosenAttribute);
        switch (attribute) {
            case DEFENSE:
                if (player1Card.getDefense() > player2Card.getDefense()) {
                    decreaseHP(player2, player1Card.getDamage());
                    return player1;
                } else if (player1Card.getDefense() < player2Card.getDefense()) {
                    decreaseHP(player1, player2Card.getDamage());
                    return player2;
                }
                throw new RoundDrawException("It's a draw round");
            case DAMAGE:
                if (player1Card.getDamage() > player2Card.getDamage()) {
                    decreaseHP(player2, player1Card.getDamage());
                    return player1;
                } else if (player1Card.getDamage() < player2Card.getDamage()) {
                    decreaseHP(player1, player2Card.getDamage());
                    return player2;
                }
                throw new RoundDrawException("It's a draw round");
            case INTELLIGENCE:
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
            currentPlayer.decreaseMp(spellCard.getManaCost());
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

    public void switchPlayers() {

        if (getCurrentPlayer().getName().equals(player1.getName())) {
            setCurrentPlayer(player2);
            numberOfRound++;
        }
        else if (getCurrentPlayer().getName().equals(player2.getName())){
            setCurrentPlayer(player1);
            numberOfRound++;
        }
    }
    public void checkHand(Player player) {
        List<Card> hand = player.getHand();
        while (hand.size() < 3) {
            player.pickCard();
        }
        Boolean fighter = false;
        for (Card card:player.getHand()) {
            if (card instanceof FighterCard) {
                fighter = true;
            }
        }
        while (fighter.equals(false) && player.getDeck().size() != 0) {
            player.pickCard();
            for (Card card:hand) {
                if (card instanceof FighterCard) {
                    fighter = true;
                }
            }
        }
    }

    public int getNumberOfRound() {
        return numberOfRound;
    }
    public boolean canPlay() {
        if (player1.getHp() < 1 || player2.getHp() < 1) {
            if (player1.getHp() > 1) {
                winner = player1;
            } else {
                winner = player2;
            }
            return false;
        }
        return true;
    }

    public Player getDefendingPlayer() {
        return defendingPlayer;
    }
    public void setDefendingPlayer(Player defendingPlayer) {
        this.defendingPlayer = defendingPlayer;
    }

    public void switchDefendingPlayer() {
        if (defendingPlayer.getName().equals(player1.getName())) {
            defendingPlayer = player2;
        } else if (defendingPlayer.getName().equals(player2.getName())) {
            defendingPlayer = player1;
        }
    }

    public boolean handContains(String name, Player player) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Card getCardByName(String name) {
        for (Card card:currentPlayer.getHand()) {
            if (name.toLowerCase().equals(card.getName().toLowerCase())) {
                return card;
            }
        }
        return null;
    }
}
