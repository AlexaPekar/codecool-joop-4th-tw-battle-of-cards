package com.codecool.cardgame.api;

public interface Round {

    Player getPlayer1();
    Player getPlayer2();
    String getChosenAttribute();
    void setPlayer1(Player player);
    void setPlayer2(Player player);
    void setChosenAttribute(String attribute);
    Player getWinner();
    void decreaseHP(Player player, int damage);

}
