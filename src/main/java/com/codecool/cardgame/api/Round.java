package com.codecool.cardgame.api;

public interface Round {

    Card getPlayer1Card();
    Card getPlayer2Card();
    void setPlayer1Card();
    void setPlayer2Card();
    PlayerImpl getWinner();
    void decreaseHp();
    
}
