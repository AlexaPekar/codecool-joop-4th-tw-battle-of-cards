package com.codecool.cardgame.api;

public interface Round {

    Card getPlayer1Card();
    Card getPlayer2Card();
    String getChosenAttribute();
    void setPlayer1Card(Card card);
    void setPlayer2Card(Card card);
    void setChosenAttribute(String attribute);
    Player getWinner();
    void decreaseHP(Player player, int damage);

}
