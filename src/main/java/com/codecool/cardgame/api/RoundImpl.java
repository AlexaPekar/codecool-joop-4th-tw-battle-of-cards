package com.codecool.cardgame.api;

public class RoundImpl implements Round {

    private Card player1Card;
    private Card player2Card;
    private String chosenAttribute;

    public RoundImpl() {
    }

    @Override
    public Card getPlayer1Card() {
        return player1Card;
    }

    @Override
    public Card getPlayer2Card() {
        return player2Card;
    }

    @Override
    public String getChosenAttribute() {
        return chosenAttribute;
    }

    @Override
    public void setPlayer1Card(Card card) {
        player1Card = card;
    }

    @Override
    public void setPlayer2Card(Card card) {
        player2Card = card;
    }

    @Override
    public void setChosenAttribute(String attribute) {
        chosenAttribute = attribute;
    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public void decreaseHP(Player player , int damage) {
        player.decreaseHp(damage);
    }

}
